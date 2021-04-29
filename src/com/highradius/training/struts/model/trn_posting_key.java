package com.highradius.training.struts.model;

public class trn_posting_key {

	private int pk_posting_key_id;
	private String key_type;
	private String description;
	private trn_account account;
	public int getPk_posting_key_id() {
		return pk_posting_key_id;
	}
	public void setPk_posting_key_id(int pk_posting_key_id) {
		this.pk_posting_key_id = pk_posting_key_id;
	}
	public String getKey_type() {
		return key_type;
	}
	public void setKey_type(String key_type) {
		this.key_type = key_type;
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
