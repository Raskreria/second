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
			//pstmt는 값 들어갈 자리에 ?를 입력한다.
			pstmt = conn.prepareStatement(sql);
			//stmt를 만들고 쿼리를 작성하고 실행해야 하는 반면
			//pstmt는 미리 쿼리를 만들고 호출할때 sql 넣어야함.
			pstmt.setString(1, newMemberInfo.getId());
			pstmt.setString(2, newMemberInfo.getPw());
			pstmt.setString(3, newMemberInfo.getName());		
			//pstmt의 set메서드들을 활용해서 ?자리에 값을 넣어줌.
			//setString의 인덱스는 1부터 시작
			//코드 가독성을 위해서 stmt안쓰고 다 pstmt를 쓴다.
//		3.쿼리 작성
			
//		4.stmt를 통해서 쿼리 실행민 결과 전달
			int count = pstmt.executeUpdate();
//		5.결과를 활용해서 서비스 구현
		//pstmt는 이미 쿼리를 연결을 했기 때문에 executeUpdate()메서드의 인자는
//			없어야한다.ㄴ

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
			//pstmt는 값 들어갈 자리에 ?를 입력한다.
			pstmt = conn.prepareStatement(sql);
			//stmt를 만들고 쿼리를 작성하고 실행해야 하는 반면
			//pstmt는 미리 쿼리를 만들고 호출할때 sql 넣어야함.
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
