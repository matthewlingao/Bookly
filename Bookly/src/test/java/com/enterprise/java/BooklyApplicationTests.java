package com.enterprise.java;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BooklyApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Autowired 
	private MockMvc mvc;
	
//	@Test
//	public void testHomePage() throws Exception {
//		
//		mvc.perform(get("/"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("index"));
//	} 
//	
	@Test 
	public void testViewBook() throws Exception {
		
		mvc.perform(get("/book/5"))
			.andExpect(status().isOk())
			.andExpect(view().name("index"));
	}

}
