package com.books.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

@WebMvcTest(BooksController.class)
@AutoConfigureMockMvc
class BooksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BooksRepository booksRepository;

    @Test
    void testHome() {
        BooksController booksController = new BooksController(null); // Arrange
        String response = booksController.home(); // Act
        assertEquals("Welcome to the Books API!", response); // Assert
    }

    @Test
    void testGetBooks() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByTitle("Młody człowiek i jezioro")).thenReturn(bookMock);

        mockMvc.perform(get("/books/title/Młody człowiek i jezioro"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}"));
    }

    @Test
    void testGetBooksByAuthor() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByAuthor("Jerzy Jurek")).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/author/Jerzy Jurek"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByYear() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByYear(1410)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/year/1410"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByRating() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByRating(5)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/rating/5"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByIspdf() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByIspdf(false)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/ispdf/false"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testInsertNewRating() throws Exception {
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, 5.0f, false);
        when(booksRepository.findByTitle("Młody człowiek i jezioro")).thenReturn(bookMock);

        mockMvc.perform(get("/books/title/Młody człowiek i jezioro"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': 5.0, 'ispdf': false}"));
    }

  @Test
    void testAddBook() throws Exception {
        Books bookMock = new Books("Kapsztad", "Zdroje", 2011, null, true);
        when(booksRepository.save(any(Books.class))).thenReturn(bookMock);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookMock)))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Kapsztad', 'author': 'Zdroje', 'year': 2011, 'rating': null, 'ispdf': true}"));
    }
}
