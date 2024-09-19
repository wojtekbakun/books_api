package com.books.books;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
public class BooksController {

    private final BooksRepository booksRepository;

    public BooksController(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @GetMapping("/books")
    public Iterable<Books> getBooks() {
        return booksRepository.findAll();
    }
    
    @PostMapping("/books")
    public Books addOneBook(@RequestBody Books book){
        return booksRepository.save(book);
    }
    
    @GetMapping("/books/title/{title}")
    public Books getBookByTitle(@PathVariable String title){
        return booksRepository.findByTitle(title);
    }

    @GetMapping("/books/author/{author}")
    public Iterable<Books> getBooksByAuthor(@PathVariable String author) {
        return booksRepository.findByAuthor(author);
    }
    
    @GetMapping("/books/year/{year}")
    public Iterable<Books> getBooksByYear(@PathVariable Integer year) {
        return booksRepository.findByYear(year);
    }

    @GetMapping("/books/rating/{rating}")
    public Iterable<Books> getBooksByRating(@PathVariable Integer rating) {
        return booksRepository.findByRating(rating);
    }

    @GetMapping("/books/ispdf/{ispdf}")
    public Iterable<Books> getBooksByIspdf(@PathVariable boolean ispdf) {
        return booksRepository.findByIspdf(ispdf);
    }

    @PutMapping("/books/{title}/rating")
    public Integer newRating(@PathVariable String title, @RequestParam Integer rating) {
        return booksRepository.updateRating(title, rating);
    }
}
