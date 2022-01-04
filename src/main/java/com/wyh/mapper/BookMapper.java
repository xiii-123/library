package com.wyh.mapper;

import com.wyh.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BookMapper {
    List<Book> getBookList();

    List<Book> getBookByName(String name);

    Book getBookById(int id);

    int addBook(Book book);

    int updateBook(Book book);

    int deleteBook(int id);
}
