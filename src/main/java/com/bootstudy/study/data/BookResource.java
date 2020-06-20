package com.bootstudy.study.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BookResource implements Serializable {
    private static final long serialVersionUID = 82498291291020L;
    private String bookId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;
}
