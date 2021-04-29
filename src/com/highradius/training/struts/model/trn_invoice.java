package com.highradius.training.struts.model;

import java.util.Date;

public class trn_invoice {

	private int pk_invoice_id;
	private String invoice_number;
	private String company_code;
	private String fiscal_year;
	private String item_number;
	private double invoice_total_amount;
	private double invoice_due_amount;
	private Date invoice_created_date;
	private Date due_date;
	private int discount_1_percentage;
	private int discount_2_percentage;
	private int discount_3_percentage;
	private char debit_credit_indicator;
	
	private trn_account account;
	private trn_invoice_type invoice_type;
	private trn_posting_key posting_key;
	private trn_customer customer;
	public int getPk_invoice_id() {
		return pk_invoice_id;
	}
	public void setPk_invoice_id(int pk_invoice_id) {
		this.pk_invoice_id = pk_invoice_id;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public String getFiscal_year() {
		return fiscal_year;
	}
	public void setFiscal_year(String fiscal_year) {
		this.fiscal_year = fiscal_year;
	}
	public String getItem_number() {
		return item_number;
	}
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	public double getInvoice_total_amount() {
		return invoice_total_amount;
	}
	public void setInvoice_total_amount(double invoice_total_amount) {
		this.invoice_total_amount = invoice_total_amount;
	}
	public double getInvoice_due_amount() {
		return invoice_due_amount;
	}
	public void setInvoice_due_amount(double invoice_due_amount) {
		this.invoice_due_amount = invoice_due_amount;
	}
	public Date getInvoice_created_date() {
		return invoice_created_date;
	}
	public void setInvoice_created_date(Date invoice_created_date) {
		this.invoice_created_date = invoice_created_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	public int getDiscount_1_percentage() {
		return discount_1_percentage;
	}
	public void setDiscount_1_percentage(int discount_1_percentage) {
		this.discount_1_percentage = discount_1_percentage;
	}
	public int getDiscount_2_percentage() {
		return discount_2_percentage;
	}
	public void setDiscount_2_percentage(int discount_2_percentage) {
		this.discount_2_percentage = discount_2_percentage;
	}
	public int getDiscount_3_percentage() {
		return discount_3_percentage;
	}
	public void setDiscount_3_percentage(int discount_3_percentage) {
		this.discount_3_percentage = discount_3_percentage;
	}
	public char getDebit_credit_indicator() {
		return debit_credit_indicator;
	}
	public void setDebit_credit_indicator(char debit_credit_indicator) {
		this.debit_credit_indicator = debit_credit_indicator;
	}
	public trn_account getAccount() {
		return account;
	}
	public void setAccount(trn_account account) {
		this.account = account;
	}
	public trn_invoice_type getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(trn_invoice_type invoice_type) {
		this.invoice_type = invoice_type;
	}
	public trn_posting_key getPosting_key() {
		return posting_key;
	}
	public void setPosting_key(trn_posting_key posting_key) {
		this.posting_key = posting_key;
	}
	public trn_customer getCustomer() {
		return customer;
	}
	public void setCustomer(trn_customer customer) {
		this.customer = customer;
	}
	
	
	
}
