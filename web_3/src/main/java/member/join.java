package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserInfo;
import vo.MemberInfo;

@WebServlet("/member/join")
public class join extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		
		
		
		MemberInfo memberInfo = new MemberInfo(id,pw,name);
		
		UserInfo userInfoDao = new UserInfo();
		
		boolean isJoin = userInfoDao.insertUserInfo(memberInfo);
		
		if(isJoin) {
			//회원 가입 성공 처리
			response.sendRedirect("/web3/member/joinSuccess.html");
		}else {
			//회원 가입 실패 처리
		}
		
	}

}
