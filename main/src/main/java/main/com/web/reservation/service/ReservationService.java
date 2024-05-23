package main.com.web.reservation.service;

import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.reserveAdmin.common.JDBCTemplate.close;
//import static main.com.web.reserveAdmin.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import main.com.web.reservation.dao.ReservationDao;
import main.com.web.room.dto.Room;
import main.com.web.room.dto.RoomTest;

public class ReservationService {

	//Date를 선택하지 않는경우 roomType만 넘겨준 경우 
	public List<Room> selectNoDateRoom(String roomType) {
		Connection conn = getConnection();
		List<Room> roomList = new ReservationDao().selectNoDateRoom(conn,roomType);
		close(conn);
		return roomList;
	}

	public List<Room> selectDateRoom(String roomType, String checkInDate, String checkOutDate) {
		
		return null;
	}
	//날짜는 선택이 없고, 
	public List<Room> selectPagedRooms(String roomType, int currentPage, int itemsPerPage) {
		Connection conn = getConnection();
		List<Room> roomList = new ReservationDao().selectPagedRooms(conn,roomType, currentPage, itemsPerPage);
		close(conn);
		return roomList;
	}

	public int selectAllCountRoom(String roomType) {
		Connection conn = getConnection();
		int result = new ReservationDao().selectAllCountRoom(conn,roomType);
		close(conn);
		return result;
	}

}
