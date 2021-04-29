package com.highradius.training.struts.model;

public class trn_customer {

	private int pk_customer_map_id;
	private int customer_number;
	private String customer_name;
	private int company_code;
	
	private trn_account account;
	private trn_customer_type customer_type;
	private trn_address address;
	public int getPk_customer_map_id() {
		return pk_customer_map_id;
	}
	public void setPk_customer_map_id(int pk_customer_map_id) {
		this.pk_customer_map_id = pk_customer_map_id;
	}
	public int getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(int customer_number) {
		this.customer_number = customer_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getCompany_code() {
		return company_code;
	}
	public void setCompany_code(int company_code) {
		this.company_code = company_code;
	}
	public trn_account getAccount() {
		return account;
	}
	public void setAccount(trn_account account) {
		this.account = account;
	}
	public trn_customer_type getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(trn_customer_type customer_type) {
		this.customer_type = customer_type;
	}
	public trn_address getAddress() {
		return address;
	}
	public void setAddress(trn_address address) {
		this.address = address;
	}
	
	
}
