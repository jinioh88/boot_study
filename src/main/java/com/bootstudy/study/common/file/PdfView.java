package com.bootstudy.study.common.file;

import lombok.val;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.jfree.chart.encoders.EncoderUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class PdfView extends AbstractView {
    protected String report;
    protected Collection<?> data;
    protected String fileName;

    public PdfView(String report, Collection<?> data, String fileName) {
        super();
        this.setContentType("application/pdf");
        this.report = report;
        this.data = data;
        this.fileName = fileName;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        val baos = createTemporaryOutputStream();
        InputStream employeeReportStream
                = getClass().getResourceAsStream("/employeeReport.jrxml");
        val report = loadReport();

        val dataSource = new JRBeanCollectionDataSource(this.data);
        val print = JasperFillManager.fillReport(report, model, dataSource);
        val exproter = new JRPdfExporter();
        exproter.setExporterInput(new SimpleExporterInput(print));
        exproter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
        exproter.exportReport();

        val encodedFilename = EncodeUtils.encodeUtf8(fileName);
        val contentDisposition = String.format("attachment; filename*=UTF-8''%s", encodedFilename);
        response.setHeader("position", contentDisposition);

        writeToResponse(response, baos);
    }

    protected final JasperReport loadReport() {
        val resource = new ClassPathResource(this.report);

        try {
            val fileName = resource.getFilename();
            if(fileName.endsWith(".jasper")) {
                try(val is = resource.getInputStream()) {
                    return (JasperReport) JRLoader.loadObject(is);
                }
            } else if(fileName.endsWith(".jrxml")) {
                try(val is = resource.getInputStream()) {
                    JasperDesign design = JRXmlLoader.load(is);
                    return JasperCompileManager.compileReport(design);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
        }
        return null;
    }
}
