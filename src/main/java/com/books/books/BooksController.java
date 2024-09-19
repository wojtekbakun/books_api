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

    // Get all books
    @GetMapping("/books")
    public Iterable<Books> getBooks() {
        return booksRepository.findAll();
    }

    // Upload new book
    @PostMapping("/books")
    public Books addOneBook(@RequestBody Books book) {
        return booksRepository.save(book);
    }

    // Get book by title
    @GetMapping("/books/title/{title}")
    public Books getBookByTitle(@PathVariable String title) {
        return booksRepository.findByTitle(title);
    }

    // Filter books by author
    @GetMapping("/books/author/{author}")
    public Iterable<Books> getBooksByAuthor(@PathVariable String author) {
        return booksRepository.findByAuthor(author);
    }

    // Filter books by year
    @GetMapping("/books/year/{year}")
    public Iterable<Books> getBooksByYear(@PathVariable Integer year) {
        return booksRepository.findByYear(year);
    }

    // Filter books by rating
    @GetMapping("/books/rating/{rating}")
    public Iterable<Books> getBooksByRating(@PathVariable Integer rating) {
        return booksRepository.findByRating(rating);
    }

    // Filter books by ispdf
    @GetMapping("/books/ispdf/{ispdf}")
    public Iterable<Books> getBooksByIspdf(@PathVariable boolean ispdf) {
        return booksRepository.findByIspdf(ispdf);
    }

    // Update book rating
    @PutMapping("/books/{title}/rating")
    public Integer insertNewRating(@PathVariable String title, @RequestParam Integer rating) {
        float newRating;
        Float currentRating = booksRepository.findByTitle(title).getRating();
        if (currentRating != null) {
            newRating = (currentRating + rating) / 2;
        } else {
            newRating = rating;
        }
        return booksRepository.updateRating(title, newRating);
    }
}
