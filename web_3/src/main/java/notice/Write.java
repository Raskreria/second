package notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.NoticeInfo;

@WebServlet("/notice/write")
public class Write extends HttpServlet {

	protected void doPost(HttpServletRequest request	, HttpServletResponse response, Object NoticeInfo ) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String contents = request.getParameter("contents");
		String title = request.getParameter("title");
		
		NoticeInfo notice = new NoticeInfo(title,contents);
		//공지사항 저장
		
		Database.noticeTable.add(notice);
		//공지사항을 데이터베이스에 add
		
		//공지사항 목록 페이지로 이동.
		response.sendRedirect("/web3/notice/list.html");
	}
}
