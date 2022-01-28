package com.elcom.springelastic.repository.elastic;

import com.elcom.springelastic.model.BookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookESRepo extends ElasticsearchRepository<BookModel, String> {
    Page<BookModel> findByAuthor(String author, Pageable pageable);

    List<BookModel> findByTitle(String title);
}
