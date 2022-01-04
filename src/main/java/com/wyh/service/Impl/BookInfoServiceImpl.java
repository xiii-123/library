package com.wyh.service.Impl;

import com.wyh.mapper.BookMapper;
import com.wyh.pojo.Book;
import com.wyh.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookService {
    @Autowired
    BookMapper mapper;
    @Override
    public List<Book> getBookList() {
        return mapper.getBookList();
    }

    @Override
    public List<Book> getBookByName(String name) {
        return mapper.getBookByName(name);
    }

    @Override
    public Book getBookById(int id) {
        return mapper.getBookById(id);
    }

    @Override
    public int addBook(Book book) {
        return mapper.addBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return mapper.updateBook(book);
    }

    @Override
    public int deleteBook(int id) {
        return mapper.deleteBook(id);
    }
}
