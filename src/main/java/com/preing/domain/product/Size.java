package com.preing.domain.product;

import java.io.Serializable;
import java.util.List;

public class Size implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6276487541935765434L;
	private String code;
	private String name;
    private Type type;
	public Size() {
		super();
	}

	public Size(String code, String name,Type type) {
		super();
		this.code = code;
		this.name = name;
		this.type=type;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public static Size find(List<Size> all, String code) {
		for (Size s : all) {
			if (code.equalsIgnoreCase(s.getCode())) {
				return s;
			}
		}
		return null;
	}

	public static class  Type {
		public static final Type CLOTHS=new Type("01", "服装");
		public static final Type SHOES=new Type("02", "鞋子");
		private String code;
		private String name;
		public Type() {
			super();
		}

		public Type(String code, String name) {
			super();
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
