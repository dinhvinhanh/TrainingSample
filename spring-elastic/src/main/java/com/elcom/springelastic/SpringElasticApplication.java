package com.elcom.springelastic;

import com.elcom.springelastic.mapper.BookMapper;
import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.model.dto.BookDTO;
import com.elcom.springelastic.repository.elastic.BookESRepo;
import com.elcom.springelastic.service.BookService;
import com.elcom.springelastic.service.ElasticSyncService;
import com.elcom.springelastic.service.impl.BookServiceImpl;
import com.elcom.springelastic.service.impl.ElasticSyncServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

import static org.springframework.data.domain.PageRequest.of;


@SpringBootApplication
@Slf4j
@EnableElasticsearchRepositories("com.elcom.springelastic.repository.elastic")
@EnableScheduling
public class SpringElasticApplication implements CommandLineRunner {

    @Autowired
    BookService bookService;
    ElasticSyncService esService;
    BookESRepo bookESRepo;
    BookMapper bookMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringElasticApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringElasticApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<BookDTO> bookDTOs = bookService.findAll();
        bookDTOs.forEach(x -> LOGGER.info(x.toString()));


        //esService.syncBooks();

        //Iterable<BookModel> books = bookESRepo.findAll();
        //books.forEach(x -> LOGGER.info(x.toString()));


        //Delete All List
        //bookService.deleteAll();

        //log.info("---------------------------");
        //bookService.save(new BookDTO( "1000", "horror love", "Vinh Anh"));
        //bookService.save(new BookDTO("1001","romance", "Duc Duong"));
        //bookService.save(new BookDTO( "1002", "love", "Duc Duong"));
        //bookService.save(new BookDTO( "1003", "adult", "Vinh Anh"));
        //bookService.save(new BookDTO( "1004", "adult love", "Vinh Anh"));

        //List<BookDTO> bookDTOs = bookService.findAll();
        //bookDTOs.forEach(x -> log.info(x.toString()));
        //Iterable<BookModel> bookModelList = bookService.findAll();
        //bookModelList.forEach(x -> System.out.println(x));
        //fuzzey search

        //Page<BookModel> books = bookService.findByAuthor("Duong", PageRequest.of(0, 10));

        //List<BookModel> bookModelList = bookService.findByTitle("love");

        //books.forEach(x -> log.info(x.toString()));

        //log.info("---------------------------");

        //bookModelList.forEach(x -> log.info(x.toString()));

    }
}
