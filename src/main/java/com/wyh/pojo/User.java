package com.wyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userNo;
    private String name;
    private String pwd;
    private Date registerDate;
    private String email;
    private Integer level;
}
