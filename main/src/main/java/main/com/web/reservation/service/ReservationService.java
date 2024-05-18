package main.com.web.reservation.service;

import java.sql.Connection;
import java.util.List;
import static main.com.web.common.JDBCTemplate.getConnection;

import main.com.web.reservation.dao.ReservationDao;
import main.com.web.room.dto.RoomTest;

public class ReservationService {

	public List<RoomTest> selectRoom(String roomType) {
		Connection conn = getConnection();
		List<RoomTest> roomList = new ReservationDao().selectRoom(conn,roomType);
		
		return roomList;
	}

}
