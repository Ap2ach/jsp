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

//URL ���� ������ �ϴ� ����. Ŭ�󿡰� ������ url ���� ���������� �����ص� �ɵ� . �ٵ� �� url���� Ȩ�� Ȩ��� �ν� �Ѵ�. 
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
		//�����ͺ��̽����� id=?�� ���ڵ带 ������ jdbc�ڵ� �ۼ�.

		try {
		//jdbc �ڵ� 
		String sql = "SELECT * FROM NOTICE where id="+id;
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl"; 
	    
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // ��ȣ����  ���̵�/ ��й�ȣ/ ���� �ּ� / 
	    Statement st = con.createStatement();  // select * from notices
	    // ������ ���ڵ� �� ǥ��
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
		//���⼭ Ȩ��� ������̼ǿ� url�� Ȩ��� �ν� �� customer�� �ν��Ѵ�.  list���� �ι� �齺�� ������ customer �� Ȩ���� ���ƿ´�. �̰��� ����� 
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
		request.setCharacterEncoding("UTF-8");			//�Ѿ���� ������ �Է¹�����  , UTF-8 �������� �Է� �޴´� . 
		
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//���빰 �־���ϴϱ� SQl ���� 
		String sql = "update notice set TITLE=?, CONTENT=? where ID=?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // ��ȣ����  ���̵�/ ��й�ȣ/ ���� �ּ� /
		    //���൵�� statement Ŭ����
		    //? �� SQL �� . ? �����ϰ� �����ϱ����� �غ� ���๮ 
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
