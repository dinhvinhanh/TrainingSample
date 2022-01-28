package com.elcom.springelastic.controller;


import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.model.dto.BookDTO;
import com.elcom.springelastic.repository.elastic.BookESRepo;
import com.elcom.springelastic.service.impl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@Slf4j
public class BookController {
    @Autowired
    private BookESRepo bookESRepo;

    @Autowired
    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    //@PutMapping("/book")
    //public BookModel save(BookModel bookModel) {
    //return bookRepository.save(bookModel);
    //}

    @PostMapping("/book")
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(this.bookService.save(bookDTO), HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {

        BookModel bookModel = bookESRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookModel not exist with id: " + id));

        bookESRepo.delete(bookModel);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookModel> findById(@PathVariable String id) {
        BookModel bookModel = bookESRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookModel not exist with id:" + id));
        return ResponseEntity.ok(bookModel);
    }

    @GetMapping("/book")
    public ResponseEntity<List<BookDTO>> findAll() {
        return new ResponseEntity<>(this.bookService.findAll(), HttpStatus.OK);
    }

    //Check book tren ElasticSearch
    @GetMapping("/ES/book")
    public Iterable<BookModel> getAll() {
        return bookESRepo.findAll();
    }


    @GetMapping(path="book/author/{author}")
    public Page<BookModel> findByAuthor(@PathVariable("author")  String author) {

        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<BookModel> books = bookESRepo.findByAuthor(author, pageRequest);

        return books;
    }



    @RequestMapping(path="book/title/{title}")
    public List<BookModel> findByTitle(@PathVariable("title")  String title) {

        return bookESRepo.findByTitle(title);
    }


}
