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
		System.out.println("�ٲٹ���");
		
		//�׳� ��� ��Ʈ�� ����ϴ� ��� 
		/*OutputStream os = response.getOutputStream();
		PrintStream out = new PrintStream(os,true);*/
		//������ ��Ʈ�� ����ϴ� ��� 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("�ȳ� Servlet");
		
		//�ذ��ؾ� �ϴ� �ΰ��� ���� . 
		// �� ��Ʈ���� �������� �⺻����, ���۷� �Ѱ��� ���� ������ ������ �����ϱ�? �迭�̳�? ��Ʈ���� ����Ʈ �迭�̴�. ����� ����Ʈ ������ �ϴµ�, ������ ������ �����? 
		// ���ڸ� �о�� �ϰų� ���ڸ� �޾ƾ� �ϰų�   PrintStream ���� ����Ʈ ���. Writer (���� ���� ��Ʈ��)�� ����� ���� �� ���ۿ� Ưȭ��Ű�� 
		
		//���� �� ��/��� API 
		
		//����.
		//int x = 3/0;
		
		
		/*String cnt_ = request.getParameter("cnt");
		int cnt=0;
		if(cnt_!=null && !cnt_.equals(""))
			cnt=Integer.parseInt(cnt_);
		
		for (int i = 0; i < cnt; i++) 
			out.println((i+1)+": �ȳ�! Servlet!<br>");*/
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		int x= 0;
		int y=0;
		if(x_!=null && !x_.equals(""))
			x=Integer.parseInt(x_);
		if(y_!=null && !y_.equals(""))
			y=Integer.parseInt(y_);
		out.println("���� ��� : "+(x+y));
		//�� �ڵ��� ������ ũ�Ұ� �ͽ��÷η��� ����� �ٸ���. �� �ڵ� ��� ����  ������� ����� �����Ϸ��� <br> �� ����Ѵ�. �ֿܼ����� ���� ĳ���� ������ ���� �ʴ´�.
	}
}