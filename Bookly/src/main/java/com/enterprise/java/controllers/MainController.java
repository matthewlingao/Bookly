package com.enterprise.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enterprise.java.models.Book;
import com.enterprise.java.models.Review;
import com.enterprise.java.services.BookService;
import com.enterprise.java.services.ReviewService;

@Controller
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("book", new Book());
        model.addAttribute("review", new Review());
        return "index";
    }
    
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    
    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping("/book/{id}")
    public String viewBook(@PathVariable int id, Model model) {
        model.addAttribute("selectedBook", bookService.getBook(id).orElse(null));
        model.addAttribute("reviews", reviewService.getReviewsByBookid(id));
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("review", new Review());
        return "index";
    }
    
    @RequestMapping("/bookdata/{id}")
    @ResponseBody
    public Book getBookData(@PathVariable int id) {
        return bookService.getBook(id).orElse(null);
    }

    @RequestMapping("/newBook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "bookform";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/";
    }

    @RequestMapping("/saveReview")
    public String saveReview(@RequestParam int bookid, @RequestParam String review) {
        Review r = new Review();
        r.setBookid(bookid);
        r.setReview(review);
        reviewService.saveReview(r);
        return "redirect:/book/" + bookid;
    }

    @RequestMapping("/reviews/{bookid}")
    @ResponseBody
    public List<Review> getReviewsByBook(@PathVariable int bookid) {
        return reviewService.getReviewsByBookid(bookid);
    }
    
    @PostMapping("/deleteBook/{bookid}")
    public String deleteBook(@PathVariable int bookid) {
    bookService.deleteBook(bookid);
    return "redirect:/";
    }
    
    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable int id, Model model) {
    	Book book = bookService.getBook(id).orElse(null);
    	model.addAttribute("book", book);
    	return "bookform";
    }
    
    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute Book book) {

        bookService.saveBook(book);

        return "redirect:/";
    }
    
   
}