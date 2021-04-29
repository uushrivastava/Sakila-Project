package org.javatraining.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.training.struts.model.SpecialPJOJO;

/**
 * Servlet implementation class GetSpecial
 */
//@WebServlet("/GetSpecial")
public class GetSpecial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSpecial() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (java_training is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		ArrayList<SpecialPJOJO> Sp = new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			
			
			  //if(id==1) {
			  Statement stm3 = conn.createStatement(); 
			  String sql3; 	  
			 
			 sql3="SELECT LEFT(SUBSTRING(COLUMN_TYPE,6),LENGTH(SUBSTRING(COLUMN_TYPE,5))-3) as enumType FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='sakila' AND TABLE_NAME='film' AND COLUMN_NAME='special_features';";
			 ResultSet rs3 = stm3.executeQuery(sql3);
			 
			 rs3.next();
				
					String str=rs3.getString("enumType");
					String[] tokens=str.split("','"); 
					int i=0;
					while(i<tokens.length) {
						SpecialPJOJO spe = new SpecialPJOJO();
						spe.setSpecial(tokens[i]);
						Sp.add(spe);
						i++;
					}
		}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			finally{			
				Gson gson = new Gson();
				String res = gson.toJson(Sp);
				response.getWriter().append(res);
			}

	}

}
