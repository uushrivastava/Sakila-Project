package com.highradius.training.struts.model;

public class trn_invoice_type {

	private int pk_invoice_type_id;
	private String invoice_type;
	private String description;
	private trn_account account;
	public int getPk_invoice_type_id() {
		return pk_invoice_type_id;
	}
	public void setPk_invoice_type_id(int pk_invoice_type_id) {
		this.pk_invoice_type_id = pk_invoice_type_id;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
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
