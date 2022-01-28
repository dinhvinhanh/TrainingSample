package com.elcom.springelastic.service.impl;

import com.elcom.springelastic.SpringElasticApplication;
import com.elcom.springelastic.mapper.BookMapper;
import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.repository.BookDAO;
import com.elcom.springelastic.repository.elastic.BookESRepo;
import com.elcom.springelastic.service.BookService;
import com.elcom.springelastic.service.ElasticSyncService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service

@Slf4j
public class ElasticSyncServiceImpl implements ElasticSyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSyncServiceImpl.class);
    private BookDAO bookDAO;
    private final BookMapper bookMapper;
    private final BookESRepo bookESRepo;

    @Autowired
    public ElasticSyncServiceImpl(BookDAO bookDAO, BookMapper bookMapper, BookESRepo bookESRepo) {
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
        this.bookESRepo = bookESRepo;
    }

    @Scheduled(cron = "0 * * ? * *")
    @Transactional
    public void sync() {
        LOGGER.info("Start Syncing - {}", LocalDateTime.now());
        this.syncBooks();
        LOGGER.info(" End Syncing - {}", LocalDateTime.now());
    }

    public void syncBooks() {

        bookESRepo.deleteAll();
        List<Book> bookList = bookDAO.findAll();
        for(Book book: bookList) {
            LOGGER.info("Syncing Book - {}", book.getId());
            BookModel bookModel = new BookModel(book.getId(), book.getTitle(), book.getAuthor());
            LOGGER.info(bookModel.toString());
            bookESRepo.save(bookModel);
        }
    }


}
