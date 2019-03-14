package com.tianya.learn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(displayName="DispatchServlet", urlPatterns="/*")
public class DispatchServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doBusiness(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doBusiness(req, resp);
	}
	
	public void doBusiness(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println(req.getScheme());
		System.out.println(req.getServerName());
		System.out.println(req.getServerPort());
		System.out.println(req.getRequestURI());
		System.out.println(req.getContextPath());
		System.out.println(req.getRequestURL());
		
		
	}
}
