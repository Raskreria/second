package etc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import vo.MemberInfo;

public class Ex02 {
	public static int inputMenuNumber() {
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< 뫄뫄뫄 프로그램 >>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴 선택 -> ");
		
		int menu = scanf.nextInt();
		
		return menu;
	
	}
	public static MemberInfo joinInput() {
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< 회원 가입 >>");
		
		System.out.print("아이디 -> ");
		String id = scanf.next();
		
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
		
		System.out.print("이름 -> ");
		String name = scanf.next();
		
		MemberInfo memberInfo = new MemberInfo(id,pw,name);
		return memberInfo;
	}
	
	public static boolean executeJoinQuery(MemberInfo memberInfo) throws SQLIntegrityConstraintViolationException{
		Connection conn =null;
		Statement stmt = null;
		
		boolean isJoin = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			stmt = conn.createStatement();
			String sql = "INSERT INTO userInfo(id,pw,name) VALUES ('"+memberInfo.getId()+"','"+memberInfo.getPw()+"','"+memberInfo.getName()+"')";
			int count = stmt.executeUpdate(sql);
			
		
			if(count == 1) {
				isJoin = true;
				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return isJoin;
	}
	public static void main(String[] args) {

		}


}
