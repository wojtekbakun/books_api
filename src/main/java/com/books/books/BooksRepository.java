package com.books.books;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BooksRepository extends CrudRepository<Books, Integer> {

    Books findByTitle(String title);

    Iterable<Books> findByAuthor(String author);

    Iterable<Books> findByYear(Integer year);

    Iterable<Books> findByRating(Integer rating);

    Iterable<Books> findByIspdf(boolean ispdf);

    @Modifying
    @Transactional
    @Query("update Books b set b.rating = :rating where b.title = :title")
    Integer updateRating(@Param("title") String title, @Param("rating") Integer rating);
}
