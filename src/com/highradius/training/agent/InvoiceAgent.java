package com.highradius.training.agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.highradius.training.struts.manager.InvoiceDetailsManager;
import com.highradius.training.struts.managerimpl.InvoiceDetailsManagerImpl;
import com.highradius.training.struts.manager.Manager;
import com.highradius.training.struts.model.trn_account;
import com.highradius.training.struts.model.trn_customer;
import com.highradius.training.struts.model.trn_invoice;
import com.highradius.training.struts.model.trn_invoice_type;
import com.highradius.training.struts.model.trn_posting_key;

public class InvoiceAgent {

	static HashMap<Integer, String> errormsgglobal = new HashMap();
	static int account_id = 75;

	public static void main(String[] args) {

		run();

	}

	public static void run() {
		System.out.println("Agent Started!!!");
		try {
			String filepath = "C:\\Users\\utkarsh.shrivastava\\Downloads\\TestDataFiles\\InvoiceTestFile2.txt";
			// account_id = 75;
			readFile(filepath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readFile(String filepath) {
		List<trn_invoice> invoices = new ArrayList();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		InvoiceDetailsManager manager = (InvoiceDetailsManager) context.getBean("InvoiceManager");
		try {
			File myFile = new File(filepath);

			// function to validate file headers and data

			boolean validfile = validatemyfile(myFile);

			if (validfile) {
				System.out.println("file validation successfull");
				invoices = processRecord(myFile);
				for(trn_invoice invoice : invoices) {
					manager.saveAndUpdate(invoice);
				}
			} else {
				System.out.println("File Validation Failed");
			}


			// function to creATE error report!!
			System.out.println("Error Report");
			System.out.println(errormsgglobal);

		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	private static List<trn_invoice> processRecord(File myFile) {
		List<trn_invoice> invoices = new ArrayList();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		InvoiceDetailsManager manager = (InvoiceDetailsManager) context.getBean("InvoiceManager");

		try {
			Scanner filereader = new Scanner(myFile);
			filereader.nextLine(); // Skip the header line;

			int lineCounter = 1;
			while (filereader.hasNext()) {
				if (errormsgglobal.containsKey(lineCounter) && errormsgglobal.get(lineCounter).equals("No Error")) {
					// System.out.println(filereader.nextLine());
					trn_invoice invoicedata = setDataPojo(filereader.nextLine());
					invoices.add(invoicedata);
				} else {
					System.out.println("Line : " + lineCounter + " : " + errormsgglobal.get(lineCounter));
					filereader.nextLine(); // skip the line with error;
				}

				lineCounter++;
			}
			
			for(trn_invoice inv : invoices) {
				System.out.println(inv.getInvoice_number());
				System.out.println(inv.getCompany_code());
				System.out.println(inv.getFiscal_year());
				System.out.println(inv.getItem_number());
				System.out.println(inv.getInvoice_type().getInvoice_type());
				System.out.println(inv.getPosting_key().getKey_type());
				System.out.println(inv.getCustomer().getCustomer_number());
				System.out.println(inv.getInvoice_total_amount());
				System.out.println(inv.getInvoice_due_amount());
				System.out.println(inv.getInvoice_created_date());
				System.out.println(inv.getDue_date());
				System.out.println(inv.getDiscount_1_percentage());
				System.out.println(inv.getDiscount_2_percentage());
				System.out.println(inv.getDiscount_3_percentage());
				System.out.println(inv.getDebit_credit_indicator());
				System.out.println(inv.getAccount().getAccount_id());
				
				
				System.out.println(".");
				System.out.println(".");
				System.out.println(".");
				System.out.println(".");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return invoices;
	}
	
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	private static trn_invoice setDataPojo(String temp) {
		trn_invoice invoice = new trn_invoice();

		String[] data = temp.split(",");

		invoice.setInvoice_number(data[0]);
		invoice.setCompany_code(data[1]);
		invoice.setFiscal_year(data[2]);
		invoice.setItem_number(data[3]);

		trn_invoice_type invc = new trn_invoice_type();
		invc.setInvoice_type(data[4]);
		invoice.setInvoice_type(invc);

		trn_posting_key post = new trn_posting_key();
		post.setKey_type(data[5]);
		invoice.setPosting_key(post);

		trn_customer cust = new trn_customer();
		cust.setCustomer_number(Integer.parseInt(data[6]));
		invoice.setCustomer(cust);

		invoice.setInvoice_total_amount(Double.parseDouble(data[7]));
		invoice.setInvoice_due_amount(Double.parseDouble(data[8]));
		invoice.setInvoice_created_date(datefor(data[9]));
		invoice.setDue_date(datefor(data[10]));
		
		Date d1 = datefor(data[9]);
		Date d2 = datefor(data[10]);
		
		long day = getDifferenceDays(d1, d2);
		System.out.println(day);
		
		int discount;
		
		if(day<=10) {
			discount = 1;
			invoice.setDiscount_1_percentage(discount);
			invoice.setDiscount_2_percentage(0);
			invoice.setDiscount_3_percentage(0);
		}
		else if (day>10 && day <=20)
		{
			discount = 2;
			invoice.setDiscount_1_percentage(0);
			invoice.setDiscount_2_percentage(discount);
			invoice.setDiscount_3_percentage(0);
		}
		else if(day>20 && day <=30) {
			discount = 3;
			invoice.setDiscount_1_percentage(0);
			invoice.setDiscount_2_percentage(0);
			invoice.setDiscount_3_percentage(discount);
		}
		else {
			discount = 0;
			invoice.setDiscount_1_percentage(discount);
			invoice.setDiscount_2_percentage(discount);
			invoice.setDiscount_3_percentage(discount);
		}
		
//		invoice.setDiscount_1_percentage(Integer.parseInt(data[11]));
//		invoice.setDiscount_2_percentage(Integer.parseInt(data[12]));
//		invoice.setDiscount_3_percentage(Integer.parseInt(data[13]));
		char c = data[14].charAt(0);
		invoice.setDebit_credit_indicator(c);
		
		trn_account acc = new trn_account();
		acc.setAccount_id(account_id);
		invoice.setAccount(acc);
		
		return invoice;
	}

	private static Date datefor(String str) {
		Date date1 = null;
		try {
			if (str.contains("-")) {
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
				date1 = dd.parse(str);
//				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			}
			if (str.contains("/")) {
				SimpleDateFormat dd = new SimpleDateFormat("yyyy/MM/dd");
				date1 = dd.parse(str);
//				date1 = new SimpleDateFormat("yyyy/MM/dd").parse(str);
			}
			if (str.contains("")) {
				SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
				date1 = dd.parse(str);
//				date1 = new SimpleDateFormat("yyyyMMdd").parse(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1;
	}

	private static boolean validatemyfile(File myFile) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		InvoiceDetailsManager manager = (InvoiceDetailsManager) context.getBean("InvoiceManager");
		try {
			Scanner filereader = new Scanner(myFile);
			String errorMessage = "";
			String[] headers = filereader.nextLine().split(",");
			if (!headers[0].equals("invoice_number") || !headers[1].equals("company_code")) {
				errorMessage += "Mandatory Header Missing";
			}

			if (!headers[2].equals("fiscal_year") || !headers[3].equals("item_number")
					|| !headers[4].equals("fk_invoice_type") || !headers[5].equals("fk_posting_key")
					|| !headers[6].equals("customer_id") || !headers[7].equals("invoice_open_amount")
					|| !headers[8].equals("invoice_due_amount") || !headers[9].equals("created_date")
					|| !headers[10].equals("due_date") || !headers[11].equals("discount_1")
					|| !headers[12].equals("discount_2") || !headers[13].equals("discount_3")
					|| !headers[14].equals("debit_credit_indicator")) {
				errorMessage += "One or Many Header Missing";
			}

			if (errorMessage.equals("")) {
				errormsgglobal.put(0, "No Error");
			} else {
				errormsgglobal.put(0, "Header Validation Failed");
				return false;
			}

			int lineCounter = 1;

			while (filereader.hasNextLine()) {

				String[] invoiceData = filereader.nextLine().split(",");

				if ("".equals(invoiceData[0]) || "".equals(invoiceData[1])) {
					errorMessage += "Mandatory Fileds Missing";
				}
				if ("".equals(invoiceData[7])) {
					errorMessage += "Invoivce Open Amount Missing";
				}

				if (!"".equals(invoiceData[9])) {
					String regex = "^([12]\\d{3}(-|/|)(0[1-9]|1[0-2])(-|/|)(0[1-9]|[12]\\d|3[01]))$";
					if (!invoiceData[9].matches(regex)) {
						errorMessage += "Wrong Date Format invoice create date";
					}
				}

				if (!"".equals(invoiceData[10])) {
					String regex = "^([12]\\d{3}(-|/|)(0[1-9]|1[0-2])(-|/|)(0[1-9]|[12]\\d|3[01]))$";
					if (!invoiceData[10].matches(regex)) {
						errorMessage += "Wrong Date Format of due date";
					}
				}

				if (manager.invoiceTypeExist(invoiceData[4], account_id) == false) {
					errorMessage += "Wrong Invoice Type";
				}

				if (manager.postingkeyExist(invoiceData[5], account_id) == false) {
					errorMessage += "Wrong key Type";
				}

				if (manager.customerExist(invoiceData[6], account_id) == false) {
					errorMessage += "Wrong customer Type";
				}

				if ("".equals(errorMessage)) {
					errormsgglobal.put(lineCounter, "No Error");
				} else {
					errormsgglobal.put(lineCounter, errorMessage);
					errorMessage = "";
				}

				lineCounter++;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return true;
	}

}
