package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.NoticeInfo;

@WebServlet("/notice/list")
public class List extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	공지사항의 목록을 불러와 전달해주는 서블릿
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();		
		
		out.print("{\"noticeList\":[");
		
		String data = "";
		
		for(NoticeInfo nthNotice : Database.noticeTable) {
			data = data+"{\"title\":\""+nthNotice.getTitle()+"\",\"contents\":\""+nthNotice.getContents()+"\"},";
		}

		data = data.substring(0, data.length()-1);
//		가장마지막 앞에 있는 문자하나만 자르고 싶으니 -1
		
		out.print(data);
		out.print("]}");
		
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
