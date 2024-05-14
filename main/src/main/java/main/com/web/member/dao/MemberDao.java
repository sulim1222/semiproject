package main.com.web.member.dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static main.com.web.common.JDBCTemplate.close;

import main.com.web.member.dto.Member;

public class MemberDao {
	private Properties sql = new Properties(); //properties 값을 가져온다
	{
		String path =MemberDao.class.getResource("/sql/member_sql.properties").getPath();
		try (FileReader fr=new FileReader(path)){ //패스 경로를 찾아 파일을 받아온다
			sql.load(fr); //properteis에 load해준다. 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int memberEnroll(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("memberEnroll"));
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberCheckNo());
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	public Member memberLogin(Connection conn, String memberId, String memberPwd) {
		Member m = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberLogin"));
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			m=getMember(rs);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return m;
	}
	public Member getMember(ResultSet rs)throws SQLException{
		Member m = null;
		 while(rs.next()) {
		 m = Member.builder().memberNo(rs.getInt("memberNo")).memberId(rs.getString("memberId")).memberPwd(rs.getString("memberPwd"))
				.memberName(rs.getString("memberName")).memberPhone(rs.getString("memberPhone")).memberCheckNo(rs.getString("memberCheckNo"))
				.memberEnrollDate(rs.getDate("memberEnrollDate"))
				.build();
			};
		return m;
	}
	public boolean duplicateId(Connection conn, Member m) {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("duplicateId"));
			pstmt.setString(1, m.getMemberId()); //더간단하게 변경가능
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		if(count==0) {
			result = true;
		}
		return result;
	}
}
