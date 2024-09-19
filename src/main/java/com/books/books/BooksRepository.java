package com.books.books;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

// Interface for the BooksRepository with standard CRUD operations and custom operations
public interface BooksRepository extends CrudRepository<Books, Integer> {

    // Find book by title
    Books findByTitle(String title);

    // Find books by author
    Iterable<Books> findByAuthor(String author);

    // Find books by year
    Iterable<Books> findByYear(int year);

    // Find books by rating
    Iterable<Books> findByRating(int rating);

    // Find books by ispdf
    Iterable<Books> findByIspdf(boolean ispdf);

    // Update book rating by title
    @Modifying
    @Transactional
    @Query("update Books b set b.rating = :rating where b.title = :title")
    Integer updateRating(@Param("title") String title, @Param("rating") Float rating);
}
