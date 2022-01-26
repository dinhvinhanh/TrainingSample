package com.elcom.springelastic;
;
import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.service.impl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

import java.util.List;

import static org.springframework.data.domain.PageRequest.of;


@SpringBootApplication
@Slf4j
public class SpringElasticApplication implements CommandLineRunner {

    @Autowired
    BookServiceImpl bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Delete All List
        bookService.deleteAll();

        log.info("---------------------------");
        bookService.save(new Book( "1000", "horror love", "Vinh Anh"));
        bookService.save(new Book("1001","romance", "Duc Duong"));
        bookService.save(new Book( "1002", "love", "Duc Duong"));
        bookService.save(new Book( "1003", "adult", "Vinh Anh"));
        bookService.save(new Book( "1004", "adult love", "Vinh Anh"));

        //Iterable<Book> bookList = bookService.findAll();
        //bookList.forEach(x -> System.out.println(x));
        //fuzzey search

        Page<Book> books = bookService.findByAuthor("Duong", PageRequest.of(0, 10));

        List<Book> bookList = bookService.findByTitle("love");

        books.forEach(x -> log.info(x.toString()));

        log.info("---------------------------");

        bookList.forEach(x -> log.info(x.toString()));

    }
}
