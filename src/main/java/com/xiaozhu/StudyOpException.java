package com.xiaozhu;
import com.xiaozhu.bean.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class StudyOpException {
   @Autowired
   private Book book;
   @ExceptionHandler(Exception.class)
   @ResponseBody
   public Book DealWithGlobleException(Exception e) throws Exception{
      //return e.getMessage();t
      System.out.println(e.getMessage());
      System.out.println("hello world");
      return book;
   }
}
