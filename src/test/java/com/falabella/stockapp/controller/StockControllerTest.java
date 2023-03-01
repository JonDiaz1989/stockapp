package com.falabella.stockapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.falabella.stockapp.entities.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class StockControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testlistProducts() throws Exception {
		RequestBuilder requestList = MockMvcRequestBuilders.get("/v1/stock/listProducts");
		mvc.perform(requestList).andExpect(status().isOk());
	}

	@Test
	public void testsaveProduct() throws Exception {

		Stock stocksave = new Stock(1, "FAL-8406270", "500 Zapatilla Urbana Mujer", "New Balance", "37", 42990.00,
				"https://falabella.scene7.com/is/image/Falabella/8406270_1",
				Arrays.asList("https://falabella.scene7.com/is/image/Falabella/8406270_2",
						"https://falabella.scene7.com/is/image/Falabella/8406270_3",
						"https://falabella.scene7.com/is/image/Falabella/8406270_4"));
		RequestBuilder requestsave = MockMvcRequestBuilders.post("/v1/stock/saveProduct")
				.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(stocksave));
		mvc.perform(requestsave).andExpect(status().isOk());

	}

	@Test
	public void testeditProduct() throws Exception {

		Stock stocksave = new Stock(1, "FAL-8406270", "500 Zapatilla Urbana Mujer", "New Balance", "37", 42990.00,
				"https://falabella.scene7.com/is/image/Falabella/8406270_1",
				Arrays.asList("https://falabella.scene7.com/is/image/Falabella/8406270_2",
						"https://falabella.scene7.com/is/image/Falabella/8406270_3",
						"https://falabella.scene7.com/is/image/Falabella/8406270_4"));
		RequestBuilder requestsave = MockMvcRequestBuilders.put("/v1/stock/editProduct/1")
				.contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(stocksave));
		mvc.perform(requestsave).andExpect(status().isOk());

	}

	public void testdeleteProduct() throws Exception {
		RequestBuilder requestdelete = MockMvcRequestBuilders.delete("/v1/stock/deleteProduct/1");
		mvc.perform(requestdelete).andExpect(status().isOk());
	}

	public void testgetProductbySKU() throws Exception {
		RequestBuilder requestgetsku = MockMvcRequestBuilders.post("/v1/stock/getProductbySKU/FAL-8406276");
		mvc.perform(requestgetsku).andExpect(status().isOk());
	}
}