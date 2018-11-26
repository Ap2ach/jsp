package com.newlecture.web.controller.customer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


//URL ���� ������ �ϴ� ����. Ŭ�󿡰� ������ url ���� ���������� �����ص� �ɵ� . �ٵ� �� url���� Ȩ�� Ȩ��� �ν� �Ѵ�. 
@WebServlet("/customer/notice/reg")
//���� ���ε带 ���� ������̼� 
@MultipartConfig(						
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*10,		//10�ް�
		maxRequestSize=1024*1024*10*5
		)

public class NoticeRegController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//���⼭ Ȩ��� ������̼ǿ� url�� Ȩ��� �ν� �� customer�� �ν��Ѵ�.  list���� �ι� �齺�� ������ customer �� Ȩ���� ���ƿ´�. �̰��� ����� 
		RequestDispatcher dispatcher = request.getRequestDispatcher("../../WEB-INF/views/customer/notice/reg.jsp");
	    //request.setAttribute("list", list);
	    dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");*/
		request.setCharacterEncoding("UTF-8");			//�Ѿ���� ������ �Է¹�����  , UTF-8 �������� �Է� �޴´� . 
		
		String path = request.getServletContext().getRealPath("/upload");
		System.out.println(path);
		System.out.println("�� �� �ȳ��� ");
		String filePath = path+File.separator+"aa.jpg";
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part part = request.getPart("file");
		InputStream fis = part.getInputStream();
		OutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size;
		while((size=fis.read(buf))>=0) /*���� ����Ʈ ��ŭ  size ��ŭ ���� �ǰ���  		//1024 1024 23 -1 -1 -1*/ {
			fos.write(buf, 0, size);
		}
		
		
		
		//����ä�� �ݱ�
		fos.close();
		fis.close();
			
		
		//���빰 �־���ϴϱ� SQl ���� 
		String sql = "insert into notice(ID,TITLE,WRITER_ID,CONTENT) VALUES(NOTICE_SEQ.NEXTVAL,?,?,?)";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // ��ȣ����  ���̵�/ ��й�ȣ/ ���� �ּ� /
		    //���൵�� statement Ŭ����
		    //? �� SQL �� . ? �����ϰ� �����ϱ����� �غ� ���๮ 
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setString(1, title);
		    st.setString(2, "newlec");
		    st.setString(3, content);
		    
		    //ResultSet rs = st.executeQuery();
		    int affected = st.executeUpdate();
		    
		    response.sendRedirect("list");
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
