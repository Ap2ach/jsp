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
@WebServlet("/customer/notice/list")
public class NoticeListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jdbc 코드
		//초기 1 
		String page_ = request.getParameter("p");
		int page = 1;
		if(page_ !=null && !page_.equals("")) {
			page=Integer.parseInt(page_);
		}
		
		String sql = "select * from (select rownum num,N.* from (select * from notice order by regdate desc) N) where num between ? and ?";
		String url = "jdbc:oracle:thin:@211.238.142.251:1521:orcl";
		
		try {
			//
			int start = (page-1)*10+1 ;
			int end = page*10 ;
			
	    Class.forName("oracle.jdbc.driver.OracleDriver");
		
	    Connection con = DriverManager.getConnection(url,"c##sist","dclass"); // 괄호안은  아이디/ 비밀번호/ 서버 주소 /
	    //실행도구 statement 클래스
	    //? 들어간 SQL 문 . ? 설정하고 실행하기위한 준비 실행문 
	    PreparedStatement st = con.prepareStatement(sql);
	    st.setInt(1, start);
	    st.setInt(2, end);
	    
	    ResultSet rs = st.executeQuery();		//바로 실행 sql이 빠져야한다 매개변수 
	    
	    /*Statement st = con.createStatement();  // select * from notices  
	    // 한줄은 레코드 라 표현
	    ResultSet rs =st.executeQuery(sql);  */
		
	    //글목록 다 가져와서 담아.
	    
	    List<Notice> list = new ArrayList<>();
	    
	    while(rs.next()) {
	    	Notice n = new Notice(
	    				rs.getInt("id"),
	    				rs.getString("title"),
	    				rs.getString("content"),
	    				rs.getString("writer_id"),
	    				rs.getDate("regdate"),
	    				rs.getInt("hit")
	    			);
	    	//n.setId(rs.getString("id"));
	    	//n.setTitle(rs.getString("title"));
	    	
	    	//...
	    	list.add(n);
	    }
		rs.close();
		st.close();
		con.close();
		//여기서 홈디는 어노테이션에 url의 홈디로 인식 즉 customer로 인식한다.  list에서 두번 백스텝 밟으면 customer 즉 홈으로 돌아온다. 이것이 상대경로 
		RequestDispatcher dispatcher = request.getRequestDispatcher("../../WEB-INF/views/customer/notice/list.jsp");
	    request.setAttribute("list", list);
	    dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
