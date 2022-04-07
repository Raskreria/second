package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.MemberInfo;

@WebServlet("/member/join")
public class join extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
// ***  요청정보를 받아서도 인코딩을 해줘야함.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		//클라이언트가 전달한 파라미터에서 회원정보를 꺼냄
		
		
		//파라미터 검증
		//1.아이디/비밀번호/이름 공백 여부
		//2.아이디/비밀번호 길이
		//3.미허용 특수문자 포함 여부
		//4.아이디/비밀번호 허용특수문자 1개이상 포함여부
		
		
		//회원 정보 생성
		MemberInfo m = new MemberInfo(id,pw,name);
		
		//아이디 중복 여부체크
		
		
		//회원 정보 table에 저장
		Database.memberInfoTable.add(m);
		
		//회원가입 성공
		response.sendRedirect("/web3/member/joinSuccess.html");
	}

}
