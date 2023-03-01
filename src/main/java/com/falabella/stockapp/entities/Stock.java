package com.falabella.stockapp.entities;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "stock_details")
public class Stock {

	protected Stock() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_producto;

	@JsonProperty("SKU")
	@Column(name = "sku", nullable = false)
	@NotNull(message = "SKU is required")
	@Pattern(regexp = "^FAL-[1-9][0-9]{6,7}$", message = "SKU must have this format -> Min: FAL-1000000 and Max: FAL-99999999")
	private String sku;

	@JsonProperty("Name")
	@Column(nullable = false)
	@NotNull(message = "Name is required")
	@NotBlank(message = "Name must not be blank")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;

	@JsonProperty("Brand")
	@Column(nullable = false)
	@NotNull(message = "brand is required")
	@NotBlank(message = "Brand must not be blank")
	@Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters")
	private String brand;

	@JsonProperty("Size")
	@Column(nullable = true)
	@NotNull(message = "Size is required")
	@NotBlank(message = "Size must not be blank")
	private String size;

	@JsonProperty("Price")
	@Column(nullable = false)
	@NotNull(message = "Price is required")
	@DecimalMin(value = "1.00", message = "Price must be at least 1.00")
	@DecimalMax(value = "99999999.00", message = "Price must be at most 99999999.00")
	private Double price;

	@JsonProperty("PrincipalUrl")
	@Column(name = "Principal_image", nullable = false)
	@NotNull(message = "PrincipalUrl is required")
	@URL(message = "PrincipalUrl must be a valid URL")
	private String principal_image;

	@ElementCollection
	@CollectionTable(name = "other_urls", joinColumns = @javax.persistence.JoinColumn(name = "sku_id"))
	@Column(name = "aditional_images", nullable = true)
	private List<String> aditional_images;

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPrincipal_image() {
		return principal_image;
	}

	public void setPrincipal_image(String principal_image) {
		this.principal_image = principal_image;
	}

	public List<String> getAditional_images() {
		return aditional_images;
	}

	public void setAditional_images(List<String> aditional_images) {
		this.aditional_images = aditional_images;
	}

	@Override
	public String toString() {
		return "Stock [id_producto=" + id_producto + ", sku=" + sku + ", name=" + name + ", brand=" + brand + ", size="
				+ size + ", price=" + price + ", principal_image=" + principal_image + ", aditional_images="
				+ aditional_images + "]";
	}

	public Stock(Integer id_producto,
			@NotNull(message = "SKU is required") @Pattern(regexp = "^FAL-[1-9][0-9]{6,7}$") String sku,
			@NotNull(message = "Name is required") @NotBlank(message = "Name must not be blank") @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters") String name,
			@NotNull(message = "brand is required") @NotBlank(message = "Brand must not be blank") @Size(min = 3, max = 50, message = "Brand must be between 3 and 50 characters") String brand,
			@NotNull(message = "Size is required") @NotBlank(message = "Size must not be blank") String size,
			@NotNull(message = "Price is required") @DecimalMin(value = "1.00", message = "Price must be at least 1.00") @DecimalMax(value = "99999999.00", message = "Price must be at most 99999999.00") Double price,
			@NotNull(message = "PrincipalUrl is required") @URL(message = "Your property must be a valid URL") String principal_image,
			List<String> aditional_images) {
		super();
		this.id_producto = id_producto;
		this.sku = sku;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.principal_image = principal_image;
		this.aditional_images = aditional_images;
	}

}
