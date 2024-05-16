package main.com.web.mypage.dao;

import static main.com.web.common.JDBCTemplate.*;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import main.com.web.member.dao.MemberDao;
public class MyPageDao {
	private Properties sql = new Properties(); //properties 값을 가져온다
	{
		String path =MemberDao.class.getResource("/sql/mypage_sql.properties").getPath();
		try (FileReader fr=new FileReader(path)){ //패스 경로를 찾아 파일을 받아온다
			sql.load(fr); //properteis에 load해준다. 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int updateMember(Connection conn, String memberId, String newName, String newPassword, String newPhone) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateMember"));
			
			pstmt.setString(1, newName);
			pstmt.setString(2, newPassword);
			pstmt.setString(3, newPhone);
			pstmt.setString(4, memberId);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

}
