package main.com.reserveAdmin.dao;

import static main.com.web.reserveAdmin.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.reserveAdmin.dto.Member;

public class AdminReserveDao {

	private Properties sql=new Properties();
	
	{
		String path=AdminReserveDao.class
				.getResource("/sql/admin/sql_admin.properties").getPath();
		try (FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Member> selectMemberAll(Connection conn,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAll"));
//			pstmt.setInt(1, (cPage-1)*numPerpage+1);
//			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberAllCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAllCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public List<Member> searchMember(Connection conn, 
			String type, String keyword, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList<>();
		try {
			String sql=this.sql.getProperty("selectSearchMember");
			if (type.equals("roomType")) {
	            sql = sql.replace("#COL", "UPPER(roomType)");
	        } else {
	            sql = sql.replace("#COL", type);
	        }
			pstmt=conn.prepareStatement(sql);
			if(type.equals("memberName") || type.equals("reserveNo") || type.equals("roomType")) {
				pstmt.setString(1, "%"+keyword.toUpperCase()+"%");
			}
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int searchMemberCount(Connection conn,String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String sql=this.sql.getProperty("searchMemberCount");
		sql=sql.replace("#COL", type);
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,type.equals("memberName")?"%"+keyword+"%":keyword);
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
			
		}catch(SQLException e) {
			
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public List<Member> selectMemberByLocation(Connection conn, String location, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByLocation"));
			pstmt.setString(1,location);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(AdminReserveDao.getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberCountByLocation(Connection conn, String location) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberCountByLocation"));
			pstmt.setString(1, location);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	
//	public int inputNewMember(Connection conn, Member m) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("insertNewMember"));
//			pstmt.setInt(1, m.getReserveNo());
//			pstmt.setString(2, m.getMemberId());
//			pstmt.setString(3, m.getMemberName());
//			pstmt.setString(4,m.getRoomType());
//			pstmt.setDate(5, m.getCheckInDate());
//			pstmt.setDate(6, m.getCheckOutDate());
//			pstmt.setString(7, m.getMemberPhone());
//			pstmt.setInt(8, m.getPayPrice());
//			pstmt.setInt(9, m.getRoomPeopleNo());
//			pstmt.setString(10, m.getMemberAddress());
//			pstmt.setDate(11, m.getReserveDate());
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
	
	
	
	public static Member getMember(ResultSet rs) throws SQLException{
		return Member.builder()
				.reserveNo(rs.getInt("RESERVENO"))
				.location(rs.getString("LOCATION"))
				.memberId(rs.getString("MEMBERID"))
				.memberName(rs.getString("MEMBERNAME"))
				.roomType(rs.getString("ROOMTYPE"))
				.bedType(rs.getString("BEDTYPE"))
				.checkInDate(rs.getDate("CHECKINDATE"))
				.checkOutDate(rs.getDate("CHECKOUTDATE"))
				.memberPhone(rs.getString("MEMBERPHONE"))
				.payPrice(rs.getInt("PAYPRICE"))
				.roomPeopleNo(rs.getInt("ROOMPEOPLENO"))
				.memberAddress(rs.getString("MEMBERADDRESS"))
				.reserveDate(rs.getDate("reservedate"))
				.build();
	}
	
	
	
	
	
//	public Member selectMemberByReserveNo(Connection conn, String reserveNo) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		Member m=null;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectMemberByReserveNo"));
//			pstmt.setString(1, reserveNo);
//			rs=pstmt.executeQuery();
//			if(rs.next()) m=getMember(rs);
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return m;
//		
//	}
//	
	
	
	
	
	
	
	
	
	
}
