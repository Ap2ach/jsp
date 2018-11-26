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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

//URL 그저 방정리 하는 수단. 클라에게 보여질 url 수납 공간정도로 생각해도 될듯 . 근데 그 url에서 홈을 홈디로 인식 한다. 
@WebServlet("/customer/notice/edit")
@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*10,
		maxRequestSize=1024*1024*10*5
		)
public class NoticeEditController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		int id=Integer.parseInt(request.getParameter("id"));
		//데이터베이스에서 id=?인 레코드를 얻어오는 jdbc코드 작성.

		try {
		//jdbc 코드 
		String sql = "SELECT * FROM NOTICE where id="+id;
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl"; 
	    
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 괄호안은  아이디/ 비밀번호/ 서버 주소 / 
	    Statement st = con.createStatement();  // select * from notices
	    // 한줄은 레코드 라 표현
	    ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		Notice n = new Notice(
				rs.getInt("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getString("writer_id"),
				rs.getDate("regdate"),
				rs.getInt("hit")
			);

	    System.out.println(n);
		rs.close();
		st.close();
		con.close();
		//여기서 홈디는 어노테이션에 url의 홈디로 인식 즉 customer로 인식한다.  list에서 두번 백스텝 밟으면 customer 즉 홈으로 돌아온다. 이것이 상대경로 
		RequestDispatcher dispatcher = request.getRequestDispatcher("../../WEB-INF/views/customer/notice/edit.jsp");
	    //request.setAttribute("list", list);
		request.setAttribute("n", n);
	    dispatcher.forward(request, response);
		}catch (Exception e) {
		}
	    
	    
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");*/
		request.setCharacterEncoding("UTF-8");			//넘어오는 값들을 입력받을때  , UTF-8 형식으로 입력 받는다 . 
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//내용물 넣어야하니까 SQl 구현 
		String sql = "update notice set TITLE=?, CONTENT=? where ID=?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 괄호안은  아이디/ 비밀번호/ 서버 주소 /
		    //실행도구 statement 클래스
		    //? 들어간 SQL 문 . ? 설정하고 실행하기위한 준비 실행문 
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setString(1, title);
		    st.setString(2, content);
		    st.setInt(3, id);
		    
		    //ResultSet rs = st.executeQuery();
		    int affected = st.executeUpdate();
		    
		    response.sendRedirect("edit?id="+id);
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
