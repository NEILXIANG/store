package com.preing.domain.product;

import java.util.List;

public class Clothes extends Product{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133619477447663312L;
    private List<Color> colors;
    private List<Size> sizes;
    
	public List<Color> getColors() {
		return colors;
	}
	public List<Size> getSizes() {
		return sizes;
	}
}
