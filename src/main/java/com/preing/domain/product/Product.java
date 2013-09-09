package com.preing.domain.product;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7423072899870957403L;
	private String code;
	private String name;
	private String title;
	private List<Price> price;
	private Template tpl;
    private int qty;
    private List<Verbalize> verbalizes;
    private ProductCategory category;
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public List<Price> getPrice() {
		return price;
	}

	public Template getTpl() {
		return tpl;
	}


	public List<Verbalize> getVerbalizes() {
		return verbalizes;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public int getQty() {
		return qty;
	}

}
