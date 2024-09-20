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

// Unit tests for the BooksController class

// Load only the BooksController class 
@WebMvcTest(BooksController.class)

// Automatically configure the MockMvc to test the BooksController class
@AutoConfigureMockMvc
class BooksControllerTests {

    // Inject the MockMvc instance
    @Autowired
    private MockMvc mockMvc;

    // Inject the ObjectMapper instance
    @Autowired
    private ObjectMapper objectMapper;

    // Mock the BooksRepository class
    @MockBean
    private BooksRepository booksRepository;

    // Test the home method
    @Test
    void testHome() {
        // Create a new BooksController instance
        BooksController booksController = new BooksController(null); // Arrange
        // Call the home method
        String response = booksController.home(); // Act
        // Check if the response is correct
        assertEquals("Welcome to the Books API!", response); // Assert
    }

    @Test
    void testGetBooks() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        // Find title of the book in mocked repository and compare it with the bookMock when the findByTitle method is called
        when(booksRepository.findByTitle("Młody człowiek i jezioro")).thenReturn(bookMock);

        // Perform a GET request to test findByTitle method
        mockMvc.perform(get("/books/title/Młody człowiek i jezioro"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}"));
    }

    @Test
    void testGetBooksByAuthor() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        Books bookMock2 = new Books("Młody człowiek i morze", "Jerzy Jurek", 1411, null, false);
        // Find all books of the author and compare them with mocks
        when(booksRepository.findByAuthor("Jerzy Jurek")).thenReturn(java.util.List.of(bookMock, bookMock2));

        // Perform a GET request to test findByAuthor method
        mockMvc.perform(get("/books/author/Jerzy Jurek"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}, {'title': 'Młody człowiek i morze', 'author': 'Jerzy Jurek', 'year': 1411, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByYear() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByYear(1410)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/year/1410"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByRating() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByRating(5)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/rating/5"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testGetBooksByIspdf() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, null, false);
        when(booksRepository.findByIspdf(false)).thenReturn(java.util.List.of(bookMock));

        mockMvc.perform(get("/books/ispdf/false"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': null, 'ispdf': false}]"));
    }

    @Test
    void testInsertNewRating() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Młody człowiek i jezioro", "Jerzy Jurek", 1410, 5.0f, false);
        when(booksRepository.findByTitle("Młody człowiek i jezioro")).thenReturn(bookMock);

        mockMvc.perform(get("/books/title/Młody człowiek i jezioro"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Młody człowiek i jezioro', 'author': 'Jerzy Jurek', 'year': 1410, 'rating': 5.0, 'ispdf': false}"));
    }

    @Test
    void testAddBook() throws Exception {
        // Create a new Books instance
        Books bookMock = new Books("Kapsztad", "Zdroje", 2011, null, true);
        
        // Compare book returned in response with the new instance
        // Method save should return bookMock regardless of book class
        when(booksRepository.save(any(Books.class))).thenReturn(bookMock);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookMock)))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'title': 'Kapsztad', 'author': 'Zdroje', 'year': 2011, 'rating': null, 'ispdf': true}"));
    }
}
