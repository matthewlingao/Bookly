package com.enterprise.java.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.java.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookid(int bookid);

}
