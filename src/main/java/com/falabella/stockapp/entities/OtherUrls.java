package com.falabella.stockapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.URL;

@Entity(name = "other_urls")
public class OtherUrls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sku_id", nullable = false)
	private Stock sku;

	@Column(name = "aditional_images", nullable = true)
	@URL(message = "URL is not valid")
	private String aditional_images;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stock getSku() {
		return sku;
	}

	public void setSku(Stock sku) {
		this.sku = sku;
	}

	public String getAditional_images() {
		return aditional_images;
	}

	public void setAditional_images(String aditional_images) {
		this.aditional_images = aditional_images;
	}

	@Override
	public String toString() {
		return "OtherUrls [id=" + id + ", sku=" + sku + ", aditional_images=" + aditional_images + "]";
	}

	public OtherUrls(Integer id, Stock sku, @URL(message = "debe tener url") String aditional_images) {
		super();
		this.id = id;
		this.sku = sku;
		this.aditional_images = aditional_images;
	}

}
