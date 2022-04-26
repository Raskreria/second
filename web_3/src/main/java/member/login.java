package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserInfo;
import etc.Database;
import vo.MemberInfo;

@WebServlet(name = "member_login", urlPatterns = { "/member/login" })
public class login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");		

		MemberInfo memberInfo = new MemberInfo(id,pw);
		UserInfo userInfo = new UserInfo();
		
		memberInfo = userInfo.selectUserInfo(memberInfo);
		
		boolean success = memberInfo.getName() == null ? false : true;
		
		
		if(success) {
			String loginUserName = memberInfo.getName();
			
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", "true");
			
			if(id.equals("admin")) {
				session.setAttribute("userLevel", "admin");
			}else {
				session.setAttribute("userLevel", "user");
			}
			
			session.setAttribute("loginUserName", loginUserName);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(loginUserName);
			out.close();
		}else {
			response.setStatus(400);	
		}
	}
}