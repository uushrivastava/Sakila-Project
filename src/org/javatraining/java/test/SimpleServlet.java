package org.javatraining.java.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet(description = "A simple servlet for practice", urlPatterns = { "/SimpleServletPath" })
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Hello From GET Method!");
//		
//		PrintWriter out = response.getWriter();
//		out.println("<h1>Hello in HTML from doGet Method</h1>");
		
		int num1 = Integer.parseInt(request.getParameter("t1"));
		int num2 = Integer.parseInt(request.getParameter("t2"));
		
		String ch = request.getParameter("op");
		double result = 0;
		if(ch.equals("+"))
		{
			result = num1+num2;
		}
		else if(ch.equals("-"))
		{
			result = num1-num2;
		}
		else if(ch.equals("*"))
		{
			result = num1*num2;
		}
		else {
			result = num1/num2;
		}
		
		
		
		PrintWriter out = response.getWriter();
		out.println("<h1>The Final Result is: </h1>"+result);
		
	}

}
