package com.elcom.springelastic.repository;

import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDAO extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
}
