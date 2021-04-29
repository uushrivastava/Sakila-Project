package org.javatraining.java.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XmlServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello From GET Method!");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>Hello in HTML from doGet Method</h1>");
		
	}
}
