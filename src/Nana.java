import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;


@WebServlet("/hello")
public class Nana extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		System.out.println("hello Servlet");
		System.out.println("꾸꾸벌레");
		
		//그냥 출력 스트림 사용하는 경우 
		/*OutputStream os = response.getOutputStream();
		PrintStream out = new PrintStream(os,true);*/
		//라이터 스트림 사용하는 경우 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("안녕 Servlet");
		
		//해결해야 하는 두가지 문제 . 
		// 이 스트림은 데이터의 기본단위, 버퍼로 한개에 대한 데이터 형식은 무엇일까? 배열이네? 스트림은 바이트 배열이다. 출력은 바이트 단위로 하는데, 어느경우 문제가 생길까? 
		// 문자를 읽어야 하거나 문자를 받아야 하거나   PrintStream 같은 바이트 대신. Writer (문자 전용 스트림)을 사용해 문자 등 전송에 특화시키자 
		
		//서블렛 웹 입/출력 API 
		
		//서블릿.
		//int x = 3/0;
		
		
		/*String cnt_ = request.getParameter("cnt");
		int cnt=0;
		if(cnt_!=null && !cnt_.equals(""))
			cnt=Integer.parseInt(cnt_);
		
		for (int i = 0; i < cnt; i++) 
			out.println((i+1)+": 안녕! Servlet!<br>");*/
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		int x= 0;
		int y=0;
		if(x_!=null && !x_.equals(""))
			x=Integer.parseInt(x_);
		if(y_!=null && !y_.equals(""))
			y=Integer.parseInt(y_);
		out.println("덧셈 결과 : "+(x+y));
		//위 코드의 실행은 크롬과 익스플로러의 결과가 다르다. 이 코드 결과 문서  개행등의 기능을 수행하려면 <br> 을 써야한다. 콘솔에서나 쓰던 캐리지 리턴은 먹지 않는다.
	}
}