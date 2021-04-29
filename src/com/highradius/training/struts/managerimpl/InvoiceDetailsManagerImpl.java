package com.highradius.training.struts.managerimpl;

import com.highradius.training.struts.dao.InvoiceDetailsDao;
import com.highradius.training.struts.manager.InvoiceDetailsManager;
import com.highradius.training.struts.model.trn_account;
import com.highradius.training.struts.model.trn_customer;
import com.highradius.training.struts.model.trn_invoice;
import com.highradius.training.struts.model.trn_invoice_type;
import com.highradius.training.struts.model.trn_posting_key;



public class InvoiceDetailsManagerImpl implements InvoiceDetailsManager {

	InvoiceDetailsDao  InvoiceDao;
	@Override
	public boolean invoiceTypeExist(String invoice_type, Integer account_id) {
	
		return InvoiceDao.invoiceTypeExist(invoice_type,account_id);
	}
	public InvoiceDetailsDao getInvoiceDao() {
		return InvoiceDao;
	}
	public void setInvoiceDao(InvoiceDetailsDao invoiceDao) {
		InvoiceDao = invoiceDao;
	}
	@Override
	public boolean postingkeyExist(String key_type, Integer account_id) {
		// TODO Auto-generated method stub
		return InvoiceDao.postingkeyExist(key_type,account_id);
	}
	@Override
	public boolean customerExist(String customer_id, Integer account_id) {
		return InvoiceDao.customerExist(customer_id, account_id);
	}
	@Override
	public void saveAndUpdate(trn_invoice invoice) {
		
		trn_invoice_type invoiceType = InvoiceDao.getinvoiceType(invoice.getInvoice_type().getInvoice_type(), invoice.getAccount().getAccount_id());
		trn_posting_key postingKey = InvoiceDao.getpostingkey(invoice.getPosting_key().getKey_type(), invoice.getAccount().getAccount_id());
		trn_customer customer = InvoiceDao.getcustomernow(invoice.getCustomer().getCustomer_number(), invoice.getAccount().getAccount_id());
		trn_account account = InvoiceDao.getaccountnow(invoice.getAccount().getAccount_id());
		
		
		
		invoice.setInvoice_type(invoiceType);
		invoice.setPosting_key(postingKey);
		invoice.setCustomer(customer);
		
		if(invoice.getInvoice_type() != null  && invoice.getPosting_key() != null && invoice.getCustomer()!= null) {
		InvoiceDao.saveAndUpdate(invoice);
		}
		else {
			System.out.println("Cannot update error occured");
		}
		
	}
	

}
