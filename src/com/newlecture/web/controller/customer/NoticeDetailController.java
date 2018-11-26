package com.newlecture.web.controller.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

//mvc로 모델링하기 

@WebServlet("/customer/notice/detail")
public class NoticeDetailController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
	    ResultSet rs;
		rs = st.executeQuery(sql);
		
		
	    
	    rs.next();
		/*String title= rs.getString("title");
		String regdate = rs.getString("regdate");
		String writer_id= rs.getString("writer_id");
		String content = rs.getString("content");
		int hit = rs.getInt("hit");*/
		
	    Notice n = new Notice(
				rs.getInt("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getString("writer_id"),
				rs.getDate("regdate"),
				rs.getInt("hit")
			);
	    
		rs.close();
		st.close();
		con.close();
		
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/notice/detail.jsp");
		 
	     request.setAttribute("n", n);
	     dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
