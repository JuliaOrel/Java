package com.itstep.hello_spring.controllers;

import com.itstep.hello_spring.models.Book;
import com.itstep.hello_spring.repositories.BookRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class ApiBookController {
    private final BookRepository bookRepository;
    public ApiBookController(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }


    @GetMapping
    public Iterable<Book>getAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable UUID id) throws Exception {
        return bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Not Found"));
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        Book bookToDelete = bookRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        bookRepository.delete(bookToDelete);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody Book bookChange){
        Book bookToUpdate = bookRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
        bookToUpdate.setTitle(bookChange.getTitle());
        return bookRepository.save(bookToUpdate);
    }
    @GetMapping("/seeds")
    public void createSomeBooks(){
        Book b1=new Book();
        b1.setTitle("The Queen's Gambit");
        bookRepository.save(b1);
    }
}
