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
 * Servlet implementation class EditForm
 */
//@WebServlet("/EditForm")
public class EditForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String desc = request.getParameter("desc");
		String rating = request.getParameter("rating");
		String director = request.getParameter("director");
		int releaseyear = Integer.parseInt(request.getParameter("releaseyear"));
		String language = request.getParameter("language");
		String special = request.getParameter("sp");
		System.out.println(id);
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
			String sql = "UPDATE film SET title = '"+title+"',DESCRIPTION = '"+desc+"',release_year = "+releaseyear+",language_id = (SELECT language_id FROM LANGUAGE WHERE NAME LIKE '%"+language+"%'),rating = '"+rating+"',director = '"+director+"',special_features = '"+special+"' WHERE film_id = "+id;			
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
