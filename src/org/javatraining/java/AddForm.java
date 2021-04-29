package org.javatraining.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddForm
 */
//@WebServlet(description = "servlet toh add form data to DB", urlPatterns = { "/AddForm" })
public class AddForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String rating = request.getParameter("rating");
		String director = request.getParameter("director");
		int releaseyear = Integer.parseInt(request.getParameter("releaseyear"));
		String language = request.getParameter("language");
		String special = request.getParameter("sp");
		System.out.println(title);
		System.out.println(desc);
		System.out.println(rating);
		System.out.println(director);
		System.out.println(releaseyear);
		System.out.println(language);
		System.out.println(special);
	
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (sakila is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			

			// Executing a query
			Statement stm = conn.createStatement();
			String sql = "INSERT INTO film (title,description,release_year,language_id,rating,director,special_features) VALUES ('"+title+"','"+desc+"',"+releaseyear+","+"(SELECT language_id FROM language WHERE name LIKE '%"+language+"%'),'"+rating+"','"+director+"','"+special+"')";			
//			ResultSet rs = 
					stm.executeUpdate(sql);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		finally {

		}
	
	
	}

}
