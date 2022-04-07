package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import etc.Database;
import vo.MemberInfo;

@WebServlet(name = "member_login", urlPatterns = { "/member/login" })
public class login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");		
//		클라이언트가 보낸 파라미터를 꺼냄

//		로그인 처리
		boolean success = false;
//		- DB에서 아이디와 비밀번호를 사용해서 일치하는 사용자를 찾는다.
		String loginUserName = null;
		for(MemberInfo nthMemberInfo : Database.memberInfoTable) {
			String nthMemberId = nthMemberInfo.getId();
			String nthMemberPw = nthMemberInfo.getPw();
			
			if(nthMemberId.equals(id)&&nthMemberPw.equals(pw)) {
				loginUserName = nthMemberInfo.getName();
				success = true;
				break;
			}
		}
//		- 찾았으면 로그인 성공
//		- 찾지못했으면 로그인 실패
		if(success) {
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