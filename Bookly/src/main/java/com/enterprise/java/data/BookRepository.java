package com.enterprise.java.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.java.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
}