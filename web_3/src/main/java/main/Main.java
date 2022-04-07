package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/main")
public class Main extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		로그인  여부 상태를 체크하는 서블릿의 URL을 주소표시줄에 입력한다.
//		로그인 성공 상태라면 ~님 환영합니다가 보이는 메인 페이지로 이동.
//		로그인 실패 상태라면 로그인 화면이 보이는 메인 페이지로 이동.

		HttpSession session = request.getSession();
		
		try {
			String isLogin = (String) session.getAttribute("isLogin");
			String loginUserName = (String)session.getAttribute("loginUserName");
//			로그인 성공 상태라면 ~님 환영합니다가 보이는 메인 페이지로 이동.
//			response.sendRedirect()는 클라이언트의 URL이 바뀜.
//			RequestDispatcher의 forward는 클라이언트의 URL이 안바뀜.
			String userLevel = (String)session.getAttribute("userLevel");
			String writeNoticeBtnTag = "";
			if(userLevel.equals("admin")) {
				writeNoticeBtnTag = "<button type = \"button\" id = \"admin_notice_write\" > 공지사항 쓰기 </button>";
			}
//			~님 환영합니다가 보이는 메인 페이지로 이동
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Servlet Project</title>"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/header.css\">"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/footer.css\">"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/main_index.css\">"
					+ "</head>"
					+ "<body>"
					+ "	<header>"
					+ "		<div id=\"login_area\">"
					+ " "	+ loginUserName + "님 환영합니다~"
					+ "		</div>"
					+ "		<div id=\"join_area\">"
					+ 		writeNoticeBtnTag
					+ "			<button type=\"button\" id = \"logout\">로그아웃</button>"
					+ "		</div>"
					+ "	</header>"
					+ "	"
					+ "	<main>"
					+ "		<h2>공지사항</h2>"
					+ "		<div id=\"notice_list\">공지사항이 없습니다.</div>"
					+ "	</main>"
					+ "	"
					+ "	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>"
					+ "	"
					+ "	<script src = \"/web3/js/jquery-3.6.0.min.js\"></script>"
					+ "	<!-- 다운로드 받아서 jqeury 추가하기. -->"
					+ "	<script type=\"text/javascript\">"
					+ "		$(\"#logout\").on(\"click\", function(){"
					+ "			location.href = \"/web3/member/logout\";"
					+ "		});"
					+ " 	$(\"#admin_notice_write\").on(\"click\", function(){"
					+ "			location.href = \"/web3/notice/form\";"
					+ "		});"
					+ "		"
					
					+ "	</script>"
					+ "</body>"
					+ "</html>");
			out.close();
		}catch(NullPointerException e) {
//			로그인 실패 상태라면 로그인 화면이 보이는 메인 페이지로 이동.	
			RequestDispatcher rd =request.getRequestDispatcher("/main/index.html");
			rd.forward(request,response);
		}
//		isLogin이 있다는 거 자체가 로그인을 했다는 증거임.
//		로그인 안한 사용자는 isLogin 자체가 없음. 그래서 null값 나와서 exception 발생함.
		
	}

}
