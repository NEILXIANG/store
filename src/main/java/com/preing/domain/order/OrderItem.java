package com.preing.domain.order;

import java.math.BigDecimal;

public class OrderItem {

	private String productCode;
	private String productName;
	private int quantity;
	private BigDecimal price;
	public String getProductCode() {
		return productCode;
	}
	public String getProductName() {
		return productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	
	
	
}
