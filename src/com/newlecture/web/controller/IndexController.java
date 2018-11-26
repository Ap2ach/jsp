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

//URL ���� ������ �ϴ� ����. Ŭ�󿡰� ������ url ���� ���������� �����ص� �ɵ� . �ٵ� �� url���� Ȩ�� Ȩ��� �ν� �Ѵ�. 
/*@WebServlet("/customer/notice/list")*/
@WebServlet("/index")
public class IndexController extends HttpServlet{
	//�並 ��û�ϴ� �͸����ε� forward�� ���� �ȴ�粲 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
	    dispatcher.forward(request, response);
	}
}
