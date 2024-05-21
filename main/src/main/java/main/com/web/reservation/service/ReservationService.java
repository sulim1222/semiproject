package main.com.web.reservation.service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import static main.com.web.common.JDBCTemplate.getConnection;

import main.com.web.reservation.dao.ReservationDao;
import main.com.web.room.dto.RoomTest;

public class ReservationService {

	//date값이 없을경우 
	public List<RoomTest> selectRoom(String roomType) {
		Connection conn = getConnection();
		List<RoomTest> roomList = new ReservationDao().selectRoom(conn,roomType);
		
		return roomList;
	}
	//date값이 있을경우
	public List<RoomTest> selectRoom(String roomType, String checkInDate, String checkOutDate) {
		Connection conn = getConnection();
		SimpleDateFormat indate = new SimpleDateFormat("yyyy-MM-dd");
		
		/* List<RoomTest> roomList = new RerservationDao(). */
		return null;
	}

}
