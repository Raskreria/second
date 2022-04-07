package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("isLogin");
		//특정 세션을 삭제하기
//		session.invalidate();
		//모든 세션을 싹다 지우기.
		
//		RequestDispatcher rd = request.getRequestDispatcher("/main/index.html");
//		rd.forward(request, response);
		response.sendRedirect("/web3/main/index.html");
	}


}
