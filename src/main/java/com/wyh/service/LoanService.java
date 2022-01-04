package com.wyh.service;

import com.wyh.pojo.Loan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface LoanService {
    List<Loan> getLoanList();

    List<Loan> getLoanByName(@Param("bookName")String bookName,@Param("userName")String userName,@Param("start")Date start,@Param("end")Date end);

    List<Loan> getLoanByDate(@Param("start")Date start,@Param("end")Date end);

    List<Loan> getLoanByUserNo(int userNo);

    Loan getLoanById(int id);

    int addLoan(Loan Loan);

    int updateLoan(Loan Loan);

    int deleteLoan(int id);
}
