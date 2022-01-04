package com.wyh.mapper;

import com.wyh.pojo.Loan;
import com.wyh.pojo.LoanHist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Mapper
@Repository
public interface LoanHistMapper {
    List<LoanHist> getLoanHistList();

    List<LoanHist> getLoanHistByName(@Param("bookName")String bookName, @Param("userName")String userName, @Param("start") Date start, @Param("end")Date end);

    List<LoanHist> getLoanHistByUserNo(int userNo);

    LoanHist getLoanHistById(int id);

    int addLoanHist(LoanHist loanHist);

    int updateLoanHist(Loan Loan);

    int deleteLoanHist(int id);
}
