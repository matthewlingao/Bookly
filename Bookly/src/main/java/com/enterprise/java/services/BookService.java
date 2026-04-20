package com.enterprise.java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.java.data.BookRepository;
import com.enterprise.java.models.Book;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}