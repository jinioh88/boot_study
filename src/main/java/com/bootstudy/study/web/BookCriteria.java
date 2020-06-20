package com.bootstudy.study.web;

import java.time.LocalDate;

public class BookCriteria {
    private String name;
    private LocalDate publishedDate;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }
}
