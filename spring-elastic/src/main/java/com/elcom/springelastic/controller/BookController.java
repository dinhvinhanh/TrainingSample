package com.elcom.springelastic.controller;

import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.core.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PutMapping("/book")
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id: " + id));

        bookRepository.delete(book);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> findById(@PathVariable String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not exist with id:" + id));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/book")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }


    @RequestMapping(path="book/author/{author}")
    public Page<Book> findByAuthor(@PathVariable("author")  String author, PageRequest pageRequest) {

        return bookRepository.findByAuthor(author, pageRequest);
    }



    @RequestMapping(path="book/title/{title}")
    public List<Book> findByTitle(@PathVariable("title")  String title) {

        return bookRepository.findByTitle(title);
    }


}
