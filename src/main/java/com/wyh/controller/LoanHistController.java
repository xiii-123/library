package com.wyh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wyh.mapper.BookInfoMapper;
import com.wyh.mapper.BookMapper;
import com.wyh.mapper.LoanHistMapper;
import com.wyh.mapper.UserMapper;
import com.wyh.pojo.Book;
import com.wyh.pojo.Loan;
import com.wyh.pojo.LoanHist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class LoanHistController {
    @Autowired
    LoanHistMapper mapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Autowired
    LoanHistMapper loanHistMapper;

    @RequestMapping("/loanHist")
    public String loan(Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<LoanHist> loanHists = mapper.getLoanHistByUserNo(Integer.parseInt((String) session.getAttribute("userNo")));
        for (LoanHist loan : loanHists) {
            loan.setUser(userMapper.getUserById(Integer.parseInt((String) session.getAttribute("userNo"))));
            Book book = bookMapper.getBookById(loan.getBook().getBookNo());
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
            loan.setBook(book);
        }
        PageInfo<LoanHist> pageInfo = new PageInfo<>(loanHists);
        model.addAttribute("pageInfo",pageInfo);
        return "loanHist/list";
    }
    @RequestMapping("/searchLoanHist")
    public String searchLoan(String bookName, String userName, String start, String end, Model model, HttpSession session, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) throws ParseException {
        PageHelper.startPage(pageNum,5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2001-1-1");
        Date endDate = new Date();
        if (start != ""){
            startDate = simpleDateFormat.parse(start);
        }
        if (end != ""){
            endDate = simpleDateFormat.parse(end);
        }
        List<LoanHist> loans = mapper.getLoanHistByName(bookName, userName, startDate, endDate);
        for (LoanHist loan : loans) {
            loan.setUser(userMapper.getUserById(Integer.parseInt((String) session.getAttribute("userNo"))));
            Book book = bookMapper.getBookById(loan.getBook().getBookNo());
            book.setInfo(bookInfoMapper.getBookInfoByISBN(book.getInfo().getISBN()));
            loan.setBook(book);
        }
        PageInfo<LoanHist> pageInfo = new PageInfo<>(loans);
        model.addAttribute("pageInfo",pageInfo);
        return "loanHist/list";
    }
}
