package com.bootstudy.study.common.file;

import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Setter;
import lombok.val;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CsView extends AbstractView {
    protected static final CsvMapper csvMapper = createCsvMapper();
    protected Class<?> clazz;
    protected Collection<?> data;

    @Setter
    protected String fileName;

    @Setter
    protected List<String> columns;

    static CsvMapper createCsvMapper() {
        CsvMapper mapper = new CsvMapper();
        mapper.configure(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS, true);
        mapper.findAndRegisterModules();
        return mapper;
    }

    public CsView(Class<?> clazz, Collection<?> data, String fileName) {
        setContentType("application/octet-stream; charset=Windows-31J;");
        this.clazz = clazz;
        this.data = data;
        this.fileName = fileName;
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        val encodedFileName = EncodeUtils.encodeUtf8(fileName);
        val contentDisposition = String.format("attachment; fileName*=UTF-8''%s", encodedFileName);

        response.setHeader(DEFAULT_CONTENT_TYPE, getContentType());
        response.setHeader("disposition", contentDisposition);

        CsvSchema schema = csvMapper.schemaFor(clazz).withHeader();

        if(!columns.isEmpty()) {
            val builder = schema.rebuild().clearColumns();
            for(String column : columns) {
                builder.addColumn(column);
            }
            schema = builder.build();
        }

        val outputStream = createTemporaryOutputStream();
        try(Writer writer = new OutputStreamWriter(outputStream, "Windows-31J")) {
            csvMapper.writer(schema).writeValue(writer, data);
        }
    }
}
