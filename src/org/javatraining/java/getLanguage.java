package org.javatraining.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.training.struts.model.languagePOJO;

/**
 * Servlet implementation class getLanguage
 */
//@WebServlet(description = "Servlet for retriving language from DB", urlPatterns = { "/getLanguage" })
public class getLanguage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getLanguage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (sakila is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		
		List<languagePOJO> languages = new ArrayList();
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			

			// Executing a query
			Statement stm = conn.createStatement();
			String sql;
			sql = "SELECT NAME FROM LANGUAGE";
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				languagePOJO language = new languagePOJO();
//				language.setId(rs.getInt("id"));
				language.setName(rs.getString("NAME"));
				
				languages.add(language);
				
				
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	
		finally {
			HashMap result = new HashMap();
//			result.put("success",true);
//			result.put("total",languages.size());
			result.put("language",languages);
			
			Gson gson = new Gson();
			String res = gson.toJson(result);
			
			response.getWriter().append(res);
		}
		}
		
	}


