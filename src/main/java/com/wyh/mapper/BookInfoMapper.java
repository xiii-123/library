package com.wyh.mapper;

import com.wyh.pojo.Book;
import com.wyh.pojo.BookInfo;
import com.wyh.pojo.BookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface BookInfoMapper {
    List<BookInfo> getBookInfoList();

    List<BookInfo> getBookInfoByName(String name);

    BookInfo getBookInfoByISBN(String isbn);

    int addBookInfo(BookInfo BookInfo);

    int updateBookInfo(BookInfo BookInfo);

    int deleteBookInfo(int id);
}
