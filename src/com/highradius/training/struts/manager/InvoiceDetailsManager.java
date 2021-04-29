package com.highradius.training.struts.manager;

import com.highradius.training.struts.model.trn_invoice;

public interface InvoiceDetailsManager {

	public  boolean invoiceTypeExist(String invoice_type, Integer account_id);
	public  boolean postingkeyExist(String key_type, Integer account_id);
	public  boolean customerExist(String customer_id, Integer account_id);
	public void saveAndUpdate(trn_invoice invoice);
}
