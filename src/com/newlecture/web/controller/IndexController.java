package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

//URL 그저 방정리 하는 수단. 클라에게 보여질 url 수납 공간정도로 생각해도 될듯 . 근데 그 url에서 홈을 홈디로 인식 한다. 
/*@WebServlet("/customer/notice/list")*/
@WebServlet("/index")
public class IndexController extends HttpServlet{
	//뷰를 요청하는 것만으로도 forward를 쓴당 안댄당께 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	    dispatcher.forward(request, response);
	}
}
