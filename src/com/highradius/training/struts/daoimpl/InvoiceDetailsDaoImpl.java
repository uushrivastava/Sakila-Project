package com.highradius.training.struts.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.highradius.training.struts.dao.InvoiceDetailsDao;
import com.highradius.training.struts.model.trn_account;
import com.highradius.training.struts.model.trn_customer;
import com.highradius.training.struts.model.trn_invoice;
import com.highradius.training.struts.model.trn_invoice_type;
import com.highradius.training.struts.model.trn_posting_key;

public class InvoiceDetailsDaoImpl implements InvoiceDetailsDao{

	
	private SessionFactory sessionFactory;
//	private static ServiceRegistry serviceRegistry;
	
	public SessionFactory createSessionFactory() {
		try {
			
			Configuration config = new Configuration();
			config.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			sessionFactory = config.buildSessionFactory(serviceRegistry);
			
		}catch(Throwable ex) {
			System.err.println("Failed to create sessionFactory object" + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		return sessionFactory;
	}
	
	public  boolean invoiceTypeExist(String invoice_type, Integer account_id) {
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		boolean found = false;	
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_invoice_type where invoice_type = '"+ invoice_type +"' and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   if(query.list().size() == 0) {
			   found = false;
		   }
		   else {
			   found = true;
		   }
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		
		return found;
		
	}
	public  boolean postingkeyExist(String key_type, Integer account_id) {
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		boolean found = false;	
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_posting_key where key_type = '"+ key_type +"' and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   if(query.list().size() == 0) {
			   found = false;
		   }
		   else {
			   found = true;
		   }
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		
		return found;
	}

	@Override
	public boolean customerExist(String customer_id, Integer account_id) {
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		boolean found = false;	
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_customer where customer_number = '"+ customer_id +"' and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   if(query.list().size() == 0) {
			   found = false;
		   }
		   else {
			   found = true;
		   }
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		
		return found;

	}

	public int getPKInvoiceID(int invoice ,int customer_id, int account ) {
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		int invoiceno = 0;
		boolean flag = false;
		trn_invoice inv = new trn_invoice();
		
			
		try {
		   tx = session.beginTransaction();
		   
		   
		   String hql = "from trn_invoice where invoice_number = " + invoice + "and fk_account_id = "+ account +"and fk_customer_id = " + customer_id ;
		   Query query = session.createQuery(hql);
		   if(query.list().size() != 0) {
		   inv =   (trn_invoice) query.list().get(0) ;
		   invoiceno = inv.getPk_invoice_id();
		   }
//		   System.out.println(invoiceno);
		   
		   if(invoiceno == 0) {
			   flag = false;
		   }
		   else flag = true;
		   
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		
		if(flag == true) {
			return invoiceno;
		}
		else return -1;
	}
	
	@Override
	public void saveAndUpdate(trn_invoice invoice) {
		// TODO Auto-generated method stub
		
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
			
		try {
		   tx = session.beginTransaction();
		   int pk_invoice = getPKInvoiceID(Integer.parseInt(invoice.getInvoice_number()),invoice.getCustomer().getPk_customer_map_id(),invoice.getAccount().getAccount_id());
		   if(pk_invoice != -1) {
		   invoice.setPk_invoice_id(pk_invoice);
		   }
		   session.saveOrUpdate(invoice);
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		

	}

	@Override
	public trn_invoice_type getinvoiceType(String invoice_type, Integer account_id) {
		trn_invoice_type invoiceType = new trn_invoice_type(); 
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		boolean found = false;	
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_invoice_type where invoice_type = '"+ invoice_type +"' and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   invoiceType = (trn_invoice_type) query.list().get(0) ;
			 
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return   invoiceType;
	}

	@Override
	public trn_posting_key getpostingkey(String key_type, Integer account_id) {
		trn_posting_key postingkey = new trn_posting_key(); 
		Session session = createSessionFactory().openSession();
		Transaction tx = null;
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_posting_key where key_type = '"+ key_type +"' and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   postingkey = (trn_posting_key) query.list().get(0) ;
			 
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return   postingkey;
	}


	@Override
	public trn_customer getcustomernow(Integer customer_id, Integer account_id) {
		trn_customer customer = new trn_customer(); 
		Session session = createSessionFactory().openSession();
		Transaction tx = null;	
		try {
		   tx = session.beginTransaction();
		   
		   String hql = "from trn_customer where customer_number = "+ customer_id +" and account.account_id = " + account_id;
		   Query query = session.createQuery(hql);
		   customer = (trn_customer) query.list().get(0) ;
			 
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return   customer;

	}

	@Override
	public trn_account getaccountnow(Integer account_id) {
		
		trn_account account = new trn_account();
		Session session = createSessionFactory().openSession();
		Transaction tx = null;	
		try {
		   tx = session.beginTransaction();
		   
		   
		   account = (trn_account) session.load(trn_account.class,account_id);
			 
		   tx.commit();
		}

		catch (Exception e) {
		   if (tx!=null) tx.rollback();
		   e.printStackTrace(); 
		} finally {
		   session.close();
		}
		
		return  account;

	}

		
	}


