package etc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import vo.MemberInfo;
import vo.NoticeInfo;

//이 서블릿을 직접 등록하고
//등록할 때 load-on-startup 태그를 사용해서
//서버가 시작되면서 서블릿이 만들어지도록 하세요
//서블릿이 만들어질 때 우리에게 필요한 계정을 생성해서
//DB에 저장하도록 하세요

public class StartupProcessor extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("<< StartupProcessor 동작 >>");
		MemberInfo memberInfo = new MemberInfo("id1","pw1","name1");

		MemberInfo adminInfo = new MemberInfo("admin","admin123","관리자");
		Database.memberInfoTable.add(memberInfo);
		Database.memberInfoTable.add(adminInfo);	
		
		NoticeInfo noticeInfo1 = new NoticeInfo("제목1","내용1");
		Database.noticeTable.add(noticeInfo1);
		NoticeInfo noticeInfo2 = new NoticeInfo("제목2","내용2");
		Database.noticeTable.add(noticeInfo2);
		NoticeInfo noticeInfo3 = new NoticeInfo("제목3","내용3");
		Database.noticeTable.add(noticeInfo3);
		NoticeInfo noticeInfo4 = new NoticeInfo("제목4","내용4");
		Database.noticeTable.add(noticeInfo4);
		NoticeInfo noticeInfo5 = new NoticeInfo("제목5","내용5");
		Database.noticeTable.add(noticeInfo5);
		
	}

}
