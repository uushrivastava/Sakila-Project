package com.highradius.training.struts.model;

public class trn_address {

	private int pk_address_id;
	private String line1;
	private String line2;
	private String street;
	private String city;
	private String zip;
	private String state;
	private String country;
	public int getPk_address_id() {
		return pk_address_id;
	}
	public void setPk_address_id(int pk_address_id) {
		this.pk_address_id = pk_address_id;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}