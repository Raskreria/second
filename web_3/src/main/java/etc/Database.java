package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vo.MemberInfo;
import vo.NoticeInfo;

public class Database {
	public static List<MemberInfo> memberInfoTable = new ArrayList<>();
	public static List<NoticeInfo> noticeTable = new ArrayList<>();
	
	//DB 커넥션을 생성해서 반환하는 메서드
	public Connection getConnection() {
		Connection conn=null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?username=root&password=1234");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	public void closeStmt(Statement stmt) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closePstmt(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

