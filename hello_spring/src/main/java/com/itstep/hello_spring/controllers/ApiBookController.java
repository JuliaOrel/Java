package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.models.Book;
import com.itstep.hello_spring.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiBookController {
    private BookRepository bookRepository;
    public ApiBookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    @GetMapping("/api/book/seeds")
    public void createSomeBooks(){
        Book b1=new Book();
        b1.setTitle("The Queen's Gambit");
        bookRepository.save(b1);
        return;
    }
}
