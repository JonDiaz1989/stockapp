package com.falabella.stockapp.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.falabella.stockapp.entities.Stock;
import com.falabella.stockapp.repository.StockDataRepository;

@RestController
@RequestMapping("/v1/stock/")
public class StockController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

	private StockDataRepository repository;

	public StockController(StockDataRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/listProducts")
	public List<Stock> listProducts() {

		LOGGER.info("Getting Data for all products");
		return repository.findAll();

	}

	@PostMapping("/saveProduct")
	public ResponseEntity<String> saveProduct(@Valid @RequestBody Stock stock, BindingResult result) {

		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return ResponseEntity.badRequest().body(errors.toString());
		}

		if (repository.findBySku(stock.getSku()).isEmpty() == false) {
			LOGGER.info("Error: SKU already registered");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: SKU already registered");
		}

		Stock saveTarea = repository.save(stock);
		LOGGER.info("New product created with ID: " + saveTarea.getId_producto());
		LOGGER.info("Body : " + stock.toString());
		return ResponseEntity.status(HttpStatus.OK).body("New product with SKU: " + stock.getSku()
				+ " created successfully with ID: " + saveTarea.getId_producto());
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int id) {
		if (repository.findById(id).isEmpty() == true) {
			LOGGER.info("ID doesn't exist");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID doesn't exist");
		}
		Stock stockdel = repository.findById(id).get();
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Product " + stockdel.getSku() + " removed successfully");

	}

	@PutMapping("/editProduct/{id}")
	public ResponseEntity<Object> editProduct(@PathVariable Integer id, @Valid @RequestBody Stock stocked,
			BindingResult result) {

		if (result.hasErrors()) {
			List<String> errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.collect(Collectors.toList());
			return ResponseEntity.badRequest().body(errors.toString());
		}

		Stock stockcompare = repository.findById(id).get();

		if (!stockcompare.getSku().equals(stocked.getSku())) {
			LOGGER.info("SKU Can't be edited! It is the identifier of a product");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("SKU Can't be edited! It is the identifier of a product");
		}

		stocked.setId_producto(id);
		repository.save(stocked);
		return ResponseEntity.status(HttpStatus.OK).body("Product " + stocked.getSku() + " modified successfully");

	}

	@PostMapping("/getProductbySKU/{sku}")
	public List<Stock> getProductbySKU(@PathVariable("sku") String SKU) {

		if (SKU.isBlank()) {
			LOGGER.info("SKU Can't be blank or empty!");
		}
		return repository.findBySku(SKU);

	}

}
