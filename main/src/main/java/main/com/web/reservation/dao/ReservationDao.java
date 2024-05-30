package main.com.web.reservation.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.reservation.dto.Reserve;
import main.com.web.room.dto.Room;

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
		System.out.println(reserveNo);
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
				.checkOutDate(rs.getDate("checkoutdate"))
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

	public List<Room> containerDate(Connection conn, String checkInDateStr, String checkOutDateStr, String roomType, int currentPage, int itemsPerPage) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Room> roomList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date checkInDate = new Date(sdf.parse(checkInDateStr).getTime());
            Date checkOutDate = new Date(sdf.parse(checkOutDateStr).getTime());

            pstmt = conn.prepareStatement(sql.getProperty("selectPagedRoomsWithDate"));
            pstmt.setString(1, roomType);
            pstmt.setDate(2, checkInDate);
            pstmt.setDate(3, checkOutDate);
            pstmt.setDate(4, checkInDate);
            pstmt.setDate(5, checkOutDate);
            pstmt.setInt(6, (currentPage - 1) * itemsPerPage + 1);
            pstmt.setInt(7, currentPage * itemsPerPage);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                roomList.add(getRoom(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return roomList;
    }

    // other methods...


	public int containerAllCount(Connection conn, String roomType, String checkInDateStr, String checkOutDateStr) {
        int result = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date checkInDate = new Date(sdf.parse(checkInDateStr).getTime());
            Date checkOutDate = new Date(sdf.parse(checkOutDateStr).getTime());

            pstmt = conn.prepareStatement(sql.getProperty("selectAllCountRoomWithDate"));
            pstmt.setString(1, roomType);
            pstmt.setDate(2, checkInDate);
            pstmt.setDate(3, checkOutDate);
            pstmt.setDate(4, checkInDate);
            pstmt.setDate(5, checkOutDate);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return result;
    }

    // other methods...
}
