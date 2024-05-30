package main.com.web.room.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import main.com.web.room.dao.RoomDao;
import main.com.web.room.dto.Room;
import main.com.web.room.dto.RoomImages;

public class RoomService {

	private RoomDao dao = new RoomDao();

	// 몰라
	public List<Room> getAllRoomsService() {
		Connection conn = getConnection();
		List<Room> rooms = dao.getAllRooms(conn);
		close(conn);
		return rooms;
	}
	
	public List<Room> getAllRoomsServiceByLocation(String location){
		Connection conn = getConnection();
		List<Room> rooms = dao.getAllRooms(conn,location);
		close(conn);
		return rooms;
	}

	// 쌤이 다시 봐주심 사진넣을때!
	public Room getRoomDetailService(String roomType, String location) {
		Connection conn = getConnection();
		Room room = dao.getRoomDetail(conn, roomType, location);
		close(conn);
		return room;
	}

	// 요한작성,, 우선잠시 잠금
//	public List<RoomImages> getImgs(int count) {
//		Connection conn = getConnection();
//		List<RoomImages> imges = dao.getImgs(conn, count);
//		close(conn);
//		return imges;
//	}

}