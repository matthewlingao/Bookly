package com.enterprise.java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.java.data.ReviewRepository;
import com.enterprise.java.models.Review;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookid(int bookid) {
        return reviewRepository.findByBookid(bookid);
    }

    public Optional<Review> getReview(int id) {
        return reviewRepository.findById(id);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }
}
