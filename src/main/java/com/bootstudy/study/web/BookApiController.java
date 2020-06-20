package com.bootstudy.study.web;

import com.bootstudy.study.api.BookService;
import com.bootstudy.study.data.Book;
import com.bootstudy.study.data.BookResource;
import com.bootstudy.study.data.BookResourceQuery;
import com.bootstudy.study.exception.BookResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookApiController {
    private final BookService bookService;
    private final RestTemplate restTemplate;

    @GetMapping(path = "{bookId}")
    public BookResource getBook(@PathVariable String bookId) {
        Book book = bookService.find(bookId);

        if(book == null) {
            throw new BookResourceNotFoundException(bookId);
        }

        BookResource resource = new BookResource();
        resource.setBookId(book.getBookId());
        resource.setName(book.getName());
        resource.setPublishedDate(book.getPublishedDate());

        return resource;
    }

    @GetMapping
    public List<BookResource> searchBooks(@Validated BookResourceQuery query) {
        BookCriteria criteria = new BookCriteria();
        criteria.setName(query.getName());
        criteria.setPublishedDate(query.getPublishedDate());

        List<Book> books = bookService.findAllByCriteria(criteria);

        BookResource resource = new BookResource();
        resource.setName("스프링");
        resource.setPublishedDate(LocalDate.now());

        URI createdResourceUri = restTemplate.postForLocation("http://8080/bookds", resource);

        return books.stream().map(book -> {
            BookResource resource = new BookResource();
            resource.setBookId(book.getBookId());
            resource.setName(book.getName());
            resource.setPublishedDate(book.getPublishedDate());

            return resource;
        }).collect(Collectors.toList());
    }
}
