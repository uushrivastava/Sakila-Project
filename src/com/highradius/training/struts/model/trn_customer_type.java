package com.highradius.training.struts.model;

public class trn_customer_type {

	
	private int pk_customer_type_id;
	private String customer_type;
	private String description;
	private trn_account account;
	public int getPk_customer_type_id() {
		return pk_customer_type_id;
	}
	public void setPk_customer_type_id(int pk_customer_type_id) {
		this.pk_customer_type_id = pk_customer_type_id;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public trn_account getAccount() {
		return account;
	}
	public void setAccount(trn_account account) {
		this.account = account;
	}
	
}
