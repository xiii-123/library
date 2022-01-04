package com.wyh;

import com.wyh.mapper.BookInfoMapper;
import com.wyh.mapper.BookMapper;
import com.wyh.mapper.LoanMapper;
import com.wyh.mapper.UserMapper;
import com.wyh.pojo.Book;
import com.wyh.pojo.Loan;
import com.wyh.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    BookMapper bookMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookInfoMapper bookInfoMapper;
    @Autowired
    LoanMapper loanMapper;

    @Test
    void contextLoads() {
        bookMapper.getBookById(1).setStatus(0);

    }

}
