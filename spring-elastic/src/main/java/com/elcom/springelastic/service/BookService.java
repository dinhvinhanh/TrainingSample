package com.elcom.springelastic.service;

import com.elcom.springelastic.mapper.BookMapper;
import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.model.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {

    BookDTO save(BookDTO bookDTO);

    BookDTO findById(String id);
    List<BookDTO> findAll();


    //void save(Book book);

    void delete(BookModel bookModel);

    BookModel findOne(String id);

    //Iterable<BookModel> findAll();

    Page<BookModel> findByAuthor(String author, PageRequest pageRequest);

    List<BookModel> findByTitle(String title);

    void deleteAll();



}
