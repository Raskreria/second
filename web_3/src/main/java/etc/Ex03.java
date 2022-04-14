package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Ex03 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// JDBC드라이버 로딩

//			접속 정보 구성요소
//			-> 프로토콜 : jdbc:mariadb
//			-> DBMS서버 도메인 또는 주소 : localhost, 127.0.0.1, 서버의 도메인 또는 IP 주소
//			-> 포트번호 : mysql이나 mariadb는 일반적으로 3306번 포트를 사용
//			-> 접속할 DB명
//			-> 접속할 사용자 계정과 비밀번호 : GET Parameter 형식으로 접속 정보의 마지막에
//										?가 붙고 name=value 로 넣음
//			http://localhost:80/web_3
//			jdbc:mariadb://localhost:3306/employees?user=root&password=1234
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=1234");
//			연결정보를 가지고 있는 Connecttion 객체를 getconnection이 리턴해줌

			stmt = conn.createStatement();
//			우리의 쿼리를 실행시켜주고 쿼리의 실행결과를 가져오는 객체

			String sql = "SELECT * FROM employees LIMIT 5";
//			실행하고자하는 쿼리 작성

			ResultSet rs = stmt.executeQuery(sql);
//			stmt를 통해서 쿼리 실행 및 결과 받아오기
//			1. SELECT 쿼리를 실행하고 싶다. -> executeQuery 메서드 호출
//			2. INSERT UPDATE DELETE 쿼리를 실행하고 싶다 -> executeUpdate 메서드 호출

//			위 ResultSet 안에는 SELECT의 결과들이 들어있는 것
			int count = 1;
			while (rs.next()) {
				System.out.println("<<" + count + " 번째 데이터 출력>>");
				int empNo = rs.getInt("emp_no");
				String birth = rs.getString("birth_date");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				char gender = rs.getString("gender").charAt(0);
				String hireDate = rs.getString("hire_date");

				System.out.println("emp_no => " + empNo);
				System.out.println("birth_date => " + birth);
				System.out.println("first_name => " + firstName);
				System.out.println("last_name => " + lastName);
				System.out.println("gender => " + gender);
				System.out.println("hire_date => " + empNo);
				count++;
			}
//			stmt.close();
//			conn.close();
			// 해제는 역순으로 해준다.
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

	}
}
