package org.javatraining.java.test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

public class JavaTrainingJDBC extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (java_training is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		
		response.setContentType("json");
		PrintWriter writer = response.getWriter();
		writer.println("<html><body>");
		//JDBC Connections starts:
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			
			// Executing a query
			Statement stm = conn.createStatement();
			String sql;
			sql = "SELECT title,`description`,`name`,`director`,`release_year` FROM `film` f JOIN `language` l ON l.`language_id`=f.`language_id`";
			ResultSet rs = stm.executeQuery(sql);
			
			
			//Extracting data from the result set
			while(rs.next())
			{
				// Retrieving column by name
				String title = rs.getString("title");
				String description = rs.getString("description");
				String language = rs.getString("name");
				String director = rs.getString("director");
				int release = rs.getInt("release_year");
				
				// Display Values
				writer.println("Title: "+title+" ");
				writer.println("Description: "+description+" ");
				writer.println("Language: "+language+" ");
				writer.println("Director: "+director+" ");
				writer.println("Release: "+release+" ");
				writer.println("\n");
				
				System.out.println("Title: "+title+" ");
				System.out.println("Description: "+description+" ");
				System.out.println("Language: "+language+" ");
				System.out.println("Director: "+director+" ");
				System.out.println("Release: "+release+" ");
				System.out.println("-----------------------------------");
				
			}
			writer.println("</body></html>");
			rs.close();
			stm.close();
			conn.close();
			
			
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}

