package com.books.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
// Automatically configure the MockMvc instance
@AutoConfigureMockMvc
class BooksControllerIntTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BooksRepository booksRepository;

	// Clear the database before each test
	@BeforeEach
    public void cleanDatabase() {
        booksRepository.deleteAll();
    }

	// Add a new book and check if it was added
	@Test
	void testAddAndGetBookByTitle() throws Exception {
		Books book = new Books("Sto latek", "Duflo", 2020, null, true);

		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(book)))
				.andExpect(status().isOk())
				.andExpect(content()
						.json("{'title':'Sto latek','author':'Duflo','year':2020,'rating':null,'ispdf':true}"));

		Books addedBook = booksRepository.findByTitle("Sto latek");
		assertNotNull(addedBook);
		assertEquals("Sto latek", addedBook.getTitle());
	}

	// Add a new book and check if it was added, then update the rating and check if it was updated
	@Test
	void testInsertNewRating() throws Exception {
		Books book = new Books("Woda", "Maciej", 2020, null, false);
		mockMvc.perform(post("/books")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(book)))
				.andExpect(status().isOk())
				.andExpect(content()
						.json("{'title':'Woda','author':'Maciej','year':2020,'rating':null,'ispdf':false}"));

		// Update the rating of the book
		mockMvc.perform(put("/books/Woda/rating").param("rating", "4")).andExpect(status().isOk());

		// Check if the rating was updated
		mockMvc.perform(get("/books/title/Woda")).andExpect(status().isOk())
				.andExpect(content().json("{'title':'Woda','author':'Maciej','year':2020,'rating':4,'ispdf':false}"));
	}
}
