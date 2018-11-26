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


//URL 그저 방정리 하는 수단. 클라에게 보여질 url 수납 공간정도로 생각해도 될듯 . 근데 그 url에서 홈을 홈디로 인식 한다. 
@WebServlet("/customer/notice/reg")
//파일 업로드를 위한 어노테이션 
@MultipartConfig(						
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*10,		//10메가
		maxRequestSize=1024*1024*10*5
		)

public class NoticeRegController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//여기서 홈디는 어노테이션에 url의 홈디로 인식 즉 customer로 인식한다.  list에서 두번 백스텝 밟으면 customer 즉 홈으로 돌아온다. 이것이 상대경로 
		RequestDispatcher dispatcher = request.getRequestDispatcher("../../WEB-INF/views/customer/notice/reg.jsp");
	    //request.setAttribute("list", list);
	    dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");*/
		request.setCharacterEncoding("UTF-8");			//넘어오는 값들을 입력받을때  , UTF-8 형식으로 입력 받는다 . 
		
		String path = request.getServletContext().getRealPath("/upload");
		System.out.println(path);
		System.out.println("난 왜 안나와 ");
		String filePath = path+File.separator+"aa.jpg";
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part part = request.getPart("file");
		InputStream fis = part.getInputStream();
		OutputStream fos = new FileOutputStream(filePath);
		
		byte[] buf = new byte[1024];
		int size;
		while((size=fis.read(buf))>=0) /*읽은 바이트 만큼  size 만큼 저장 되겠지  		//1024 1024 23 -1 -1 -1*/ {
			fos.write(buf, 0, size);
		}
		
		
		
		//파일채널 닫기
		fos.close();
		fis.close();
			
		
		//내용물 넣어야하니까 SQl 구현 
		String sql = "insert into notice(ID,TITLE,WRITER_ID,CONTENT) VALUES(NOTICE_SEQ.NEXTVAL,?,?,?)";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		
		    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 괄호안은  아이디/ 비밀번호/ 서버 주소 /
		    //실행도구 statement 클래스
		    //? 들어간 SQL 문 . ? 설정하고 실행하기위한 준비 실행문 
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
