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
		System.out.println("<< ������ ���α׷� >>");
		System.out.println("1. ȸ������");
		System.out.println("2. �α���");
		System.out.println("3. ȸ�� ���� ����");
		System.out.println("4. ȸ�� Ż��");
		System.out.println("5. ���α׷� ����");
		System.out.print("�޴� ���� -> ");
		
		int menu = scanf.nextInt();
		
		return menu;
	
	}
	public static MemberInfo joinInput() {
		Scanner scanf = new Scanner(System.in);
		System.out.println("<< ȸ�� ���� >>");
		
		System.out.print("���̵� -> ");
		String id = scanf.next();
		
		System.out.print("��й�ȣ -> ");
		String pw = scanf.next();
		
		System.out.print("�̸� -> ");
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
