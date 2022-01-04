package com.wyh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private String ISBN;
    private String bname;
    private String author;
    private String press;
    private Float price;
    private String language;
    private Integer pages;
}
