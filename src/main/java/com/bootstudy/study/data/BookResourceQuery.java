package com.bootstudy.study.data;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class BookResourceQuery implements Serializable {
    private static final long serialVersionUID = -234902039390290L;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate publishedDate;
}
