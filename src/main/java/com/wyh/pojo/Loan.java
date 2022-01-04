package com.wyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
    private Integer id;
    private Book book;
    private User user;
    private Date borrowDate;
    private Integer keepDays;
}
