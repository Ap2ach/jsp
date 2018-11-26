package com.newlecture.web.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet("/customer/notice/delete")
public class NoticeDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");			//넘어오는 값들을 입력받을때  , UTF-8 형식으로 입력 받는다 . 
		
		int id= Integer.parseInt(request.getParameter("id"));
		
		//내용물 넣어야하니까 SQl 구현 
		String sql = "delete from notice where id=?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 괄호안은  아이디/ 비밀번호/ 서버 주소 /
		    //실행도구 statement 클래스
		    //? 들어간 SQL 문 . ? 설정하고 실행하기위한 준비 실행문 
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setInt(1,  id);
		    //ResultSet rs = st.executeQuery();
		    int affected = st.executeUpdate();
		    
		    response.sendRedirect("list");
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
