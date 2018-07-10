package com.ljw.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class PictureFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String requestURI = request.getRequestURI();
		
		System.out.println(requestURI);
		
		if ("302".equals(request.getParameter("picture"))) {
			response.sendRedirect("/spring-struts2/publish/100.jpg");//
			return;
		}
		
		filterChain.doFilter(request, response);
	}

}
