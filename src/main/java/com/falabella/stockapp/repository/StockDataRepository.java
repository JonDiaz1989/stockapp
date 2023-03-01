package com.falabella.stockapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falabella.stockapp.entities.Stock;


public interface StockDataRepository extends JpaRepository<Stock, Integer> {
	
	List<Stock> findBySku(String sku);

}
