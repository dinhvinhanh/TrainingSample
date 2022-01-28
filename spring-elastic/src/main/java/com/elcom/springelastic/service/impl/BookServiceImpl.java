package com.elcom.springelastic.service.impl;

import com.elcom.springelastic.mapper.BookMapper;
import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.model.dto.BookDTO;
import com.elcom.springelastic.repository.BookDAO;
import com.elcom.springelastic.repository.elastic.BookESRepo;
import com.elcom.springelastic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;
    private BookMapper bookMapper;
    private BookESRepo bookESRepo;
    @Autowired
    BookServiceImpl(BookDAO bookDAO, BookMapper bookMapper){
        this.bookDAO = bookDAO;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO save(BookDTO bookDTO) {
        Book book = this.bookDAO.save(this.bookMapper.toBook(bookDTO));
        return this.bookMapper.toBookDTO(book);
    }

    @Override
    public BookModel findOne(String id) {
        return null;
    }


    @Override
    public BookDTO findById(String id) {
        return this.bookMapper.toBookDTO(this.bookDAO.findById(id).orElse(null));
    }

    @Override
    public List<BookDTO> findAll() {
        return this.bookMapper.toBookDTOs(this.bookDAO.findAll());
    }

    @Override
    public void delete(BookModel bookModel) {

    }

    //@Override
    //public Iterable<BookModel> findAll() {
    //    return bookRepository.findAll();
    //}

    @Override
    public Page<BookModel> findByAuthor(String author, PageRequest pageRequest) {
        return bookESRepo.findByAuthor(author, pageRequest);
    }

    @Override
    public List<BookModel> findByTitle(String title) {
        return bookESRepo.findByTitle(title);
    }

    @Override
    public void deleteAll() {
        bookESRepo.deleteAll();
    }


}
