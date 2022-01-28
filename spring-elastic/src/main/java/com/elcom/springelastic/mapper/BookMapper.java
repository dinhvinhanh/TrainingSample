package com.elcom.springelastic.mapper;

import com.elcom.springelastic.model.Book;
import com.elcom.springelastic.model.BookModel;
import com.elcom.springelastic.model.dto.BookDTO;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toBookDTO(Book book);

    List<BookDTO> toBookDTOs(List<Book> books);

    Book toBook(BookDTO bookDTO);

    List<Book> toBooks(List<BookDTO> bookDTOs);

    BookModel toBookModel(Book book);

    List<BookModel> toBookModels(List<Book> books);

}
