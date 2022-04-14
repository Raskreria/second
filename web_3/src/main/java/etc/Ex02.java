package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {

		Scanner scanf = new Scanner(System.in);

		System.out.println("<< 뫄뫄뫄프로그램 >>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.print("메뉴 선택 -> ");

		int menu = scanf.nextInt();

		switch (menu) {
		case 1:
			System.out.println("<< 회원 가입 >>");

			System.out.print("아이디 -> ");
			String id = scanf.next();

			System.out.print("비밀번호 -> ");
			String pw = scanf.next();

			System.out.print("이름 -> ");
			String name = scanf.next();

			Connection conn = null;
			Statement stmt = null;
			try {
				Class.forName("org.mariadb.jdbc.Driver");

				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");

				stmt = conn.createStatement();

				String sql = "INSERT INTO userInfo(id, pw, name) VALUES ('" + id + "','" + pw + "','" + name + "')";
				System.out.println(sql);
				int count = stmt.executeUpdate(sql);
//				executeUpdate가 리턴해주는건 ResultSet 타입이 아닌 int 타입 정수
//				정수를 return 해주는 이유는 쿼리를 실행해서 영향 받은 행을 알려주기 위해
				
				if(count == 1) {
					System.out.println("회원 가입 완료");
				}else {
					System.out.println("회원 가입 실패");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			System.out.println("<< 로그인 >>");
			break;
		default:
			System.out.println("번호를 잘못 입력하셨습니다.");
		}
	}

}
