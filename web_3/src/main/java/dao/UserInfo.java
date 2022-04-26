package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import etc.Database;
import vo.MemberInfo;

public class UserInfo {
	public boolean insertUserInfo(MemberInfo newMemberInfo) {
		Database db = new Database();
		Connection conn = db.getConnection();

		

		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO userInfo(id,pw,name) VALUES(?,?,?)";
			//pstmt�� �� �� �ڸ��� ?�� �Է��Ѵ�.
			pstmt = conn.prepareStatement(sql);
			//stmt�� ����� ������ �ۼ��ϰ� �����ؾ� �ϴ� �ݸ�
			//pstmt�� �̸� ������ ����� ȣ���Ҷ� sql �־����.
			pstmt.setString(1, newMemberInfo.getId());
			pstmt.setString(2, newMemberInfo.getPw());
			pstmt.setString(3, newMemberInfo.getName());		
			//pstmt�� set�޼������ Ȱ���ؼ� ?�ڸ��� ���� �־���.
			//setString�� �ε����� 1���� ����
			//�ڵ� �������� ���ؼ� stmt�Ⱦ��� �� pstmt�� ����.
//		3.���� �ۼ�
			
//		4.stmt�� ���ؼ� ���� ����� ��� ����
			int count = pstmt.executeUpdate();
//		5.����� Ȱ���ؼ� ���� ����
		//pstmt�� �̹� ������ ������ �߱� ������ executeUpdate()�޼����� ���ڴ�
//			������Ѵ�.��

			return count == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
	}
	public MemberInfo selectUserInfo(MemberInfo memberInfo) {
		Database db = new Database();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			String sql = "SELECT * FROM userInfo WHERE id=? AND pw=?)";
			//pstmt�� �� �� �ڸ��� ?�� �Է��Ѵ�.
			pstmt = conn.prepareStatement(sql);
			//stmt�� ����� ������ �ۼ��ϰ� �����ؾ� �ϴ� �ݸ�
			//pstmt�� �̸� ������ ����� ȣ���Ҷ� sql �־����.
			pstmt.setString(1, memberInfo.getId());
			pstmt.setString(2, memberInfo.getPw());
			SS
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				memberInfo.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			db.closeResultSet(rs);
			db.closePstmt(pstmt);
			db.closeConn(conn);
		}
		return memberInfo;
	}
}
