package com.preing.domain.product;

import java.util.List;

import com.preing.domain.distributor.Distributor;

public class Product {
	private String code;
	private String name;
	private String title;
	private List<Price> price;
	private Template tpl;
    private Distributor distributor;
    private List<SalesProperty> props;
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

	public Distributor getDistributor() {
		return distributor;
	}

	public List<SalesProperty> getProps() {
		return props;
	}

	public List<Verbalize> getVerbalizes() {
		return verbalizes;
	}

	public ProductCategory getCategory() {
		return category;
	}

}
