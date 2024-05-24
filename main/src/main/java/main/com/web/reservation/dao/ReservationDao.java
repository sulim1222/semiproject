package main.com.web.reservation.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static main.com.web.common.JDBCTemplate.*;

import main.com.web.reservation.dto.Reserve;
import main.com.web.room.dto.Room;
import main.com.web.room.dto.RoomTest;

public class ReservationDao {
	private Properties sql = new Properties();
	{
		String path = ReservationDao.class.getResource("/sql/reservation_sql.properties").getPath();
		try(FileReader fr = new FileReader(path);){
			sql.load(fr);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	//결제 완료페이지에 출력할 Reserve(동훈)
	public Reserve selectMyReserve(Connection conn, String reserveNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Reserve reserve = new Reserve();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMyReserve"));
			pstmt.setString(1, reserveNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) reserve = getReserve(rs);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return reserve;
	}

	private Reserve getReserve(ResultSet rs) throws SQLException{
		return Reserve.builder()
				.reserveNo(rs.getString("reserveno"))
				.location(rs.getString("location"))
				.memberId(rs.getString("memberid"))
				.memberName(rs.getString("membername"))
				.roomType(rs.getString("roomtype"))
				.bedType(rs.getString("bedtype"))
				.checkInDate(rs.getDate("checkindate"))
				.checkOutDate(rs.getDate("checkindate"))
				.memberPhone(rs.getString("memberphone"))
				.payPrice(rs.getInt("payprice"))
				.roomPeopleNo(rs.getInt("roompeopleno"))
				.memberAddress(rs.getString("memberaddress"))
				.reserveDate(rs.getDate("reservedate"))
				.build();
	}
	//날짜(x) roomType(o) 단순 페이징 처리를 위한 메서드 
	public List<Room> selectNoDateRoom(Connection conn, String roomType) {
		
		return null;
	}
	//room 정보담는 Resultset 
	private Room getRoom(ResultSet rs)throws  SQLException{
		return Room.builder().roomNo(rs.getInt("roomNo"))
				.roomPrice(rs.getInt("roomPrice"))
				.roomAmenity(rs.getString("roomAmenity"))
				.roomArea(rs.getFloat("roomArea"))
				.roomInfo(rs.getString("roomInfo"))
				.location(rs.getString("location"))
				.category(rs.getString("category"))
				.hotelService(rs.getString("hotelService"))
				.roomUrl(rs.getString("roomUrl"))
				.build();
	}

	public List<Room> selectPagedRooms(Connection conn, String roomType, int currentPage, int itemsPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Room> roomList =new  ArrayList<Room>();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectPagedRooms"));
			pstmt.setString(1, roomType);
	        pstmt.setInt(2, (currentPage - 1) * itemsPerPage + 1); // OFFSET 계산
	        pstmt.setInt(3, currentPage * itemsPerPage); // FETCH 계산
	        rs = pstmt.executeQuery();
			while(rs.next()) {
				roomList.add(getRoom(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return roomList;
	}

	public int selectAllCountRoom(Connection conn, String roomType) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllCountRoom"));
			pstmt.setString(1, roomType);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result++;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}