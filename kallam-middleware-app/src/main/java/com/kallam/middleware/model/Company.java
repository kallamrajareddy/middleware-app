package com.kallam.middleware.model;

public class Company {
	private String value;
	private String text;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Company(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}

}
