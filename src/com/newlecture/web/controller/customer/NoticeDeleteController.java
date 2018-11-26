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

//URL ���� ������ �ϴ� ����. Ŭ�󿡰� ������ url ���� ���������� �����ص� �ɵ� . �ٵ� �� url���� Ȩ�� Ȩ��� �ν� �Ѵ�. 
@WebServlet("/customer/notice/delete")
public class NoticeDeleteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");			//�Ѿ���� ������ �Է¹�����  , UTF-8 �������� �Է� �޴´� . 
		
		int id= Integer.parseInt(request.getParameter("id"));
		
		//���빰 �־���ϴϱ� SQl ���� 
		String sql = "delete from notice where id=?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // ��ȣ����  ���̵�/ ��й�ȣ/ ���� �ּ� /
		    //���൵�� statement Ŭ����
		    //? �� SQL �� . ? �����ϰ� �����ϱ����� �غ� ���๮ 
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
