package com.wyh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyh.mapper.*;
import com.wyh.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LoanController {
    @Autowired
    private LoanMapper mapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Autowired
    LoanHistMapper loanHistMapper;

    @RequestMapping("/loanBook/{bookNo}")
    public String loanBook(@PathVariable("bookNo")int bookNo, HttpSession session){
        Loan loan = new Loan(null,new Book(bookNo,null,null,null),
                new User(Integer.parseInt((String)session.getAttribute("userNo")),null,null,null,null,null),new Date(),15);
        mapper.addLoan(loan);
        Book book = bookMapper.getBookById(bookNo);
        book.setStatus(1);
        bookMapper.updateBook(book);
        return "redirect:/book";
    }
    @RequestMapping("/loan")
    public String loan(Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Loan> loans = mapper.getLoanByUserNo(Integer.parseInt((String) session.getAttribute("userNo")));
        for (Loan loan : loans) {
            loan.setUser(userMapper.getUserById(Integer.parseInt((String) session.getAttribute("userNo"))));
            Book book = bookMapper.getBookById(loan.getBook().getBookNo());
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
            loan.setBook(book);
        }
        PageInfo<Loan> pageInfo = new PageInfo<>(loans);
        model.addAttribute("pageInfo",pageInfo);
        return "loan/list";
    }
    @RequestMapping("/returnBook/{id}")
    public String returnBook(@PathVariable("id")int id){
        Loan loan = mapper.getLoanById(id);
        loanHistMapper.addLoanHist(new LoanHist(null,loan.getBook(),loan.getUser(),loan.getBorrowDate(),new Date()));
        mapper.deleteLoan(id);
        Book book = bookMapper.getBookById(loan.getBook().getBookNo());
        book.setStatus(2);
        book.setLocation(null);
        bookMapper.updateBook(book);
        return "redirect:/loan";
    }
    @RequestMapping("/searchLoan")
    public String searchLoan(String bookName, String userName, String start, String end, Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) throws ParseException {
        PageHelper.startPage(pageNum,5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2001-1-1");
        Date endDate = new Date();
        if (start != null){
            startDate = simpleDateFormat.parse(start);
        }
        if (end != null){
            endDate = simpleDateFormat.parse(end);
        }
        List<Loan> loans = mapper.getLoanByName(bookName, userName, startDate, endDate);
        for (Loan loan : loans) {
            loan.setUser(userMapper.getUserById(Integer.parseInt((String) session.getAttribute("userNo"))));
            Book book = bookMapper.getBookById(loan.getBook().getBookNo());
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
            loan.setBook(book);
        }
        PageInfo<Loan> pageInfo = new PageInfo<>(loans);
        model.addAttribute("pageInfo",pageInfo);
        return "loan/list";
    }
}
