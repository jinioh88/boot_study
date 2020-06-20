package com.bootstudy.study.api;

import com.bootstudy.study.data.Book;
import com.bootstudy.study.web.BookCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    public Book find(String bookId) {
        return null;
    }

    public List<Book> findAllByCriteria(BookCriteria criteria) {
        return null;
    }
}
