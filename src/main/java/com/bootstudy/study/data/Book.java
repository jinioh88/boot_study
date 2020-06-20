package com.bootstudy.study.data;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private String bookId;
    private String name;
    private LocalDate publishedDate;
}
