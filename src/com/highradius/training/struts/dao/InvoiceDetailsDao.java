package com.highradius.training.struts.dao;

import com.highradius.training.struts.model.trn_account;
import com.highradius.training.struts.model.trn_customer;
import com.highradius.training.struts.model.trn_invoice;
import com.highradius.training.struts.model.trn_invoice_type;
import com.highradius.training.struts.model.trn_posting_key;

public interface InvoiceDetailsDao {

	public  boolean invoiceTypeExist(String invoice_type, Integer account_id);
	public  boolean postingkeyExist(String key_type, Integer account_id);
	public  boolean customerExist(String customer_id, Integer account_id);
	public void saveAndUpdate(trn_invoice invoice);
	
	public  trn_invoice_type getinvoiceType(String invoice_type, Integer account_id);
	public  trn_posting_key getpostingkey(String key_type, Integer account_id);
	public  trn_customer getcustomernow(Integer customer_id, Integer account_id);
	public  trn_account getaccountnow(Integer account_id);
}
