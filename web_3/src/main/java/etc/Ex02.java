package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

import vo.MemberInfo;

public class Ex02 {
	public static int inputMenuNumber() {
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< 뫄뫄뫄프로그램 >>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원 정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴 선택 -> ");

		int menu = scanf.nextInt();

		return menu;
	}

	// 회원 가입 시 회원 정보를 입력 받는 메서드.
	public static MemberInfo joinInput() {
//		1. 회원 정보 입력받는 부분
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< 회원 가입 >>");

		System.out.print("아이디 -> ");
		String id = scanf.next();

		System.out.print("비밀번호 -> ");
		String pw = scanf.next();

		System.out.print("이름 -> ");
		String name = scanf.next();
//		//1. 회원 정보 입력받는 부분

		MemberInfo memberInfo = new MemberInfo(id, pw, name);

		return memberInfo;

	}

	public static boolean executeJoinQuery(MemberInfo memberInfo) throws SQLIntegrityConstraintViolationException {
//		DB를 사용할 때 제일 중요한 점.
//		우리가 원하는 동작을 다 했다면 반드시 close로 DB와 프로그램의 연결을 끊어줘야함.
		Connection conn = null;
		Statement stmt = null;
//		2.회원가입 쿼리를 실행하는 부분
		boolean isJoin = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			String sql = "INSERT INTO userInfo(id, pw, name) VALUES ('" + memberInfo.getId() + "','"
					+ memberInfo.getPw() + "','" + memberInfo.getName() + "')";
			int count = stmt.executeUpdate(sql);

			if (count == 1) {
				isJoin = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
		} catch (SQLException e) {
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
		return isJoin;
	}

	public static void joinResult(boolean isJoin) {
		if (isJoin) {
			System.out.println("회원 가입 완료");
		} else {
			System.out.println("회원 가입 실패");
		}
	}

	public static void join() {

		boolean isJoin;
		MemberInfo memberInfo = joinInput();

		try {
			isJoin = executeJoinQuery(memberInfo);
			joinResult(isJoin);
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 사용중인 아이디 입니다.");
		}

	}

	public static void login() {
		// 1. 아이디와 비밀번호를 입력받는 부분
		MemberInfo loginInfo = loginInput();

		// 2. 로그인 쿼리를 실행하고 결과를 받아오는 부분
		boolean isLogin = executeLoginQuery(loginInfo);
		// 3. 결과를 출력하는 부분.

		if (isLogin) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
	}

	public static MemberInfo loginInput() {
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< 로그인 >>");

		System.out.print("아이디 -> ");
		String id = scanf.next();

		System.out.print("비밀번호 -> ");
		String pw = scanf.next();

		MemberInfo loginInfo = new MemberInfo(id, pw);

		return loginInfo;
	}

	public static boolean executeLoginQuery(MemberInfo loginInfo) {
		Connection conn = null;
		Statement stmt = null;
//		2.회원가입 쿼리를 실행하는 부분

		boolean isLoginSuccess = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			String sql = "SELECT pw" + " FROM userInfo" + " WHERE id LIKE '" + loginInfo.getId() + "';";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String pw = rs.getString("pw");
				if (loginInfo.getPw().equals(pw))
					isLoginSuccess = true;
			} else {
				System.out.println("존재하지 않는 아이디입니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return isLoginSuccess;
	}

	public static MemberInfo executeUpdateQuery(MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;
//		2.회원가입 쿼리를 실행하는 부분

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			String sql = "SELECT name" + " FROM userInfo" + " WHERE id LIKE '" + memberInfo.getId() + "';";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String name = rs.getString("name");
				memberInfo.setName(name);

			} else {
				System.out.println("존재하지 않는 아이디입니다.");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return memberInfo;
	}

	public static boolean executeNameUpdateQuery(String name, String id) {
		Connection conn = null;
		Statement stmt = null;
		boolean isUpdate = false;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			String sql = "UPDATE userInfo" + " SET name = '" + name + "' WHERE id = '" + id + "';";
			int count = stmt.executeUpdate(sql);

			if (count == 1) {
				isUpdate = true;
			} else {
				isUpdate = false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return isUpdate;
	}

	public static void update() {
		MemberInfo memberInfo = loginInput();

//		1. 회원 정보 수정을 하기 위한 아이디, 비밀번호 입력 받기

		memberInfo = executeUpdateQuery(memberInfo);

//		2. 사용자가 입력한 아이디, 비밀번호를 사용해서 수정할 회원의 정보 찾기

		if (memberInfo.getName() == null) {
			System.out.println("존재하지 않는 계정입니다.");
		} else {
//			3. 찾은 회원의 정보 출력
			System.out.println("회원 이름 -> " + memberInfo.getName());
			Scanner scanf = new Scanner(System.in);
//			4. 수정할 이름 입력 받기

			System.out.print("수정할 이름을 입력하세요 -> ");
			String newName = scanf.next();
//			5. 사용자가 입력한 이름으로 회원 정보 수정
			boolean isUpdate = executeNameUpdateQuery(newName,memberInfo.getId());
//			6. 수정 결과 출력
			if(isUpdate) {
				System.out.println("이름을 성공적으로 수정했습니다.");
			}else {
				System.out.println("이름을 성공적으로 수정하지 못했습니다.");
			}
		}



	}
	public static boolean executeDeleteQuery(String id) {
		Connection conn = null;
		Statement stmt = null;
		boolean isDelete = false;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			String sql = "DELETE FROM userInfo WHERE id = '" + id + "';";
			int count = stmt.executeUpdate(sql);

			if (count == 1) {
				isDelete = true;
			} else {
				isDelete = false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		return isDelete;
	}
	public static void delete() {
		Scanner scanf = new Scanner(System.in);
		MemberInfo memberInfo = loginInput();
//		1. 회원 탈퇴하기 위한 아이디, 비밀번호 입력 받기
		memberInfo = executeUpdateQuery(memberInfo);
//		2. 사용자가 입력한 아이디, 비밀번호를 사용해서 탈퇴할 회원의 정보 찾기
		boolean isDelete = false;
		if (memberInfo.getId() == null) {
			System.out.println("존재하지 않는 계정입니다.");
			
		}else {
//			3. 정말 탈퇴할 것인지 묻기
			System.out.println("정말 삭제하시겠습니까?(y/n)");

			char choice = scanf.next().charAt(0);
			if(choice == 'y') {
				isDelete = executeDeleteQuery(memberInfo.getId());
				if(isDelete) {
					System.out.println("정상적으로 회원 탈퇴 되었습니다.");
				}else {
					System.out.println("회원 탈퇴 처리 실패");
				}
			}
			else{
				System.out.println("메뉴로 돌아갑니다.");
			}
		}

//		4. 정말 탈퇴한다면 탈퇴(회원 정보 삭제) 처리
	}
	public static void main(String[] args) {

		boolean isRunning = true;

		while (isRunning) {

			int menu = inputMenuNumber();

			switch (menu) {
			case MenuNumber.JOIN:
				join();
				break;
			case MenuNumber.LOGIN:
				login();
				break;
			case MenuNumber.UPDATE:
				update();
				break;
			case MenuNumber.DELETE:
				delete();
				break;
			case MenuNumber.EXIT:
				System.out.println("프로그램을 종료합니다.");
				isRunning = false;
				break;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
			}// end switch
		} // end while
	}

}
