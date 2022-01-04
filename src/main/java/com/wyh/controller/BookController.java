package com.wyh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyh.mapper.BookInfoMapper;
import com.wyh.mapper.BookMapper;
import com.wyh.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookMapper mapper;
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @RequestMapping("/book")
    public String toBook(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Book> bookList = mapper.getBookList();
        for (Book book : bookList) {
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
        }
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        model.addAttribute("pageInfo",pageInfo);
        return "book/list";
    }
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "book/add";
    }
    @RequestMapping("/addBook")
    public String addBook(Book book){
        mapper.addBook(book);
        System.out.println("add:"+book);
        return "redirect:/book";
    }
    @RequestMapping("/searchBook")
    public String searchBook(String bookName, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Book> bookList = mapper.getBookByName(bookName);
        for (Book book : bookList) {
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
        }
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        model.addAttribute("pageInfo",pageInfo);
        return "book/list";
    }
    @RequestMapping("/toUpdateBook/{id}")
    public String toUpdate(@PathVariable("id") int id, Model model){
        Book book = mapper.getBookById(id);
        model.addAttribute("book",book);
        return "book/update";
    }
    @RequestMapping("/updateBook")
    public String update(Book book){
        mapper.updateBook(book);
        return "redirect:/book";
    }
    @RequestMapping("/deleteBook/{id}")
    public String delete(@PathVariable("id")int id){
        mapper.deleteBook(id);
        return "redirect:/book";
    }


}
