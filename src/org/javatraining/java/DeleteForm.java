package org.javatraining.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteForm
 */
//@WebServlet(description = "servlet to delete records from DB", urlPatterns = { "/DeleteForm" })
public class DeleteForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String s = request.getParameter("items");
		System.out.println(s);
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (sakila is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			

			// Executing a query
//			Statement stm = conn.createStatement();
//	        String sql = "DELETE FROM film WHERE film_id IN ("+s+")";
	        
	        Statement stm1 = conn.createStatement();
			Statement stm2 = conn.createStatement();
			Statement stm3 = conn.createStatement();
			Statement stm4 = conn.createStatement();
			Statement stm5 = conn.createStatement();
			String sql1="DELETE FROM `film_actor` WHERE film_id IN ("+s+");";
			String sql2="DELETE FROM `film_category` WHERE film_id IN ("+s+");";
			String sql3="DELETE FROM `rental` WHERE `inventory_id` IN (SELECT `inventory_id` FROM `inventory` WHERE film_id IN ("+s+"));";
			String sql4="DELETE FROM `inventory` WHERE film_id IN ("+s+");";
			String sql5="DELETE FROM film WHERE film_id IN ("+s+");";
			stm1.executeUpdate(sql1);
			stm1.close();
			stm2.executeUpdate(sql2);
			stm2.close();
			stm3.executeUpdate(sql3);
			stm3.close();
			stm4.executeUpdate(sql4);
			stm4.close();
			stm5.executeUpdate(sql5);
			stm5.close();
			
////			ResultSet rs = 
//					stm.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		finally {

		}
	}

}
