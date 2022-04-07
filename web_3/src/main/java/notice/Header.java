package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/notice/header")
public class Header extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		
		try {
			String isLogin = (String)session.getAttribute("isLogin");
			String loginUserName = (String)session.getAttribute("loginUserName");
			String userLevel = (String)session.getAttribute("userLevel");
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();		
			
			out.print("{\"isLogin\":true,"
					+ "\"loginUserName\":\"" +loginUserName
					+"\",\"userLevel\":\""+userLevel+"\"}");
			out.close();
		}catch(NullPointerException e) {
			PrintWriter out = response.getWriter();
			
			out.print("{\"isLogin\":\"false\"}");
		}
		
//		->로그인 여부 알려줘야함
//		->로그인 했다면
//			->로그인한 사용자의 이름
//			->로그인한 사용자의 레벨
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
