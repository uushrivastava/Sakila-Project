package org.javatraining.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.highradius.training.struts.model.Movie;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class ServletJDBC
 */
//@WebServlet(description = "servlet to  extract data from DB using JDBC", urlPatterns = { "/getDataServletJDBC" })
public class ServletJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletJDBC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer start = Integer.parseInt(request.getParameter("start"));
		Integer limit = Integer.parseInt(request.getParameter("limit"));
		String filter = request.getParameter("filter");
		String sort = request.getParameter("sort");
//		
//		String[] arr = filter.split(",");
//		String[] arr2 = null;
//		
//		String jsonString = filter;
//		HashMap<String,String> map = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, String>>(){}.getType());
		
		System.out.println(start+"-"+limit);
		System.out.println(filter);
		System.out.println(sort);
//		System.out.println(arr.length);
		
//		if(arr.length ==2)
//		{
//			String a1 = arr[0].split
////		}
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);
//		System.out.println(arr[2]);
//		System.out.println(arr[3]);
//		System.out.println(arr[4]);
		final String JDBC_Driver = "com.mysql.jdbc.Driver"; // JDBC DRIVER NAME
		final String DB_Url = "jdbc:mysql://localhost/sakila"; //Database URL (sakila is the schema)
		
		//Credentials
		final String username = "root";
		final String password = "root";
		
		List<Movie> movies = new ArrayList();
		int total=0;
//		List<Movie> movies1 = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Register the JDBC Driver
			Connection conn = DriverManager.getConnection(DB_Url,username,password); //Open a connection
			

			// Executing a query
			Statement stm = conn.createStatement();
			Statement stm1 = conn.createStatement();
			String sql;
			sql = "SELECT film_id AS id,IFNULL(`title`,'unknown') title, IFNULL(`description`,'unknown') `desc`,IFNULL(`release_year`,'0') `releaseyear`,IFNULL((SELECT NAME FROM LANGUAGE WHERE language_id = ff.language_id LIMIT 1),'Unknown') `language`,IFNULL(`rating`,'unknown') `rating`,IFNULL(`special_features`,'unknown') `special`,IFNULL(`director`,'Unknown') `director`FROM `film` ff where is_deleted = 0 LIMIT "+start+","+limit;
			String sql1 = "SELECT COUNT(*) as count FROM film where is_deleted = 0";
			//sql = "SELECT IFNULL(`title`,'unknown') title, IFNULL(`description`,'unknown') `desc`,IFNULL(`release_year`,'0') `releaseyear`,IFNULL((SELECT NAME FROM LANGUAGE WHERE language_id = ff.language_id LIMIT 1),'Unknown') `language`,IFNULL(`rating`,'unknown') `rating`,IFNULL(`special_features`,'unknown') `special`,IFNULL(`director`,'Unknown') `director`FROM `film` ff ";
			ResultSet rs = stm.executeQuery(sql);
			ResultSet rs1 = stm1.executeQuery(sql1);
			
			
			if(rs1.next()) {
				total=rs1.getInt("count");
			}
			
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setDesc(rs.getString("desc"));
				movie.setReleaseyear(rs.getInt("releaseyear"));
				//movie.setLanguage(rs.getString("language"));
				movie.setDirector(rs.getString("director"));
				movie.setRating(rs.getString("rating"));
				movie.setSpecial(rs.getString("special"));
				
				movies.add(movie);
//				movies1.add(movie);
				
			}
//		System.out.println(movies.get(0));
//			for(int i=start;i<limit;i++)
//			{
//				//System.out.println(movies1.get(i));
//				movies1.add(movies.get(i));
//			}
//			System.out.println(movies1.get(0));
////			System.out.println(movies1.get(1));
//			System.out.println(movies1.size());
		}
			
			catch(Exception e) {
				e.printStackTrace();
			}
		
			finally{
				HashMap result = new HashMap();
				result.put("success",true);
//				result.put("total",movies.size());
				result.put("total", total);
				result.put("movies",movies);
				
				Gson gson = new Gson();
				String res = gson.toJson(result);
				
				response.getWriter().append(res);
			}
		}
	}

