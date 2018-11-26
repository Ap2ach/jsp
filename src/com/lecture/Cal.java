package com.lecture;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/cal")
public class Cal extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
     
     
      String display = request.getParameter("d");
      String second = request.getParameter("s");
      if(display==null ||display.equals("")) {
    	  System.out.println("널");
         display = "0";
      }							//홈디에 바로 있는 add.jsp
      if(second==null || second.equals("")) {
    	  System.out.println("널");
         second = "0";
      }		
      RequestDispatcher dispatcher = request.getRequestDispatcher("cal.jsp");
	    request.setAttribute("display", display);
	    request.setAttribute("second", second);
	    dispatcher.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      
      request.setCharacterEncoding("UTF-8");
      
      String display="0";
      String second="0";
      String opp="";
      String btn = request.getParameter("btn");
      //System.out.println(btn);
      char boo = btn.charAt(0);
      if(boo-'0'>=0&& boo-'0'<=9){
    	  display = request.getParameter("display");
    	  display=display+ btn;
    	  System.out.println(display);
    	  second = request.getParameter("second");
      }else if(boo!='=') {
    	  opp=request.getParameter("opp");
    	  System.out.println(opp);
    	  if(opp!="") {
    		  display = request.getParameter("display");
    		  second = request.getParameter("second");
    		  long num1=Long.parseLong(display);
    		  long num2=Long.parseLong(second);
    		  long res=0;
    		  System.out.println(num1+","+num2);
        	  switch(opp) {
        	  case "+":
        		  res=num2+num1;
        		  break;
        	  case "-":
        		  res=num2-num1;
        		  break;
        	  case "*":
        		  res=num2*num1;
        		  break;
        	  }
        	  System.out.println(res);
        	  display=res+"";
        	  second="0";
    	  }else {
    	  second = request.getParameter("display");
    	  System.out.println(second);
    	  }
    	  System.out.println(btn);
    	  HttpSession session = request.getSession();
          session.setAttribute("opp", btn);
      }else {
    	  opp=request.getParameter("opp"); 
    	  System.out.println("계산할 차례야 부호는 뭐게> "+opp);
    	  display = request.getParameter("display");
		  second = request.getParameter("second");
		  long num1=Long.parseLong(display);
		  long num2=Long.parseLong(second);
		  long res=0;
		  System.out.println(num1+","+num2);
    	  switch(opp) {
    	  case "+":
    		  res=num2+num1;
    		  break;
    	  case "-":
    		  res=num2-num1;
    		  break;
    	  case "*":
    		  res=num2*num1;
    		  break;
    	  }
    	  System.out.println(res);
    	  display=res+"";
    	  second="0";
      }
    		  
     
      response.sendRedirect("cal?d="+display+"&s="+second);
	     
	   
	}
}
