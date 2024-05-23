package main.com.web.room.service;

import static main.com.web.reserveAdmin.common.JDBCTemplate.close;
import static main.com.web.reserveAdmin.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import main.com.web.room.dao.RoomDao;
import main.com.web.room.dto.Room;

public class RoomService {
	private RoomDao dao=new RoomDao();
	
	public List<Room> GetAllRooms() {
	      Connection conn=getConnection();
	      List<Room> rooms=dao.getAllRooms(conn);
	      close(conn);
	      return rooms;
	   }
	
	public List<Room> getRoomDetailService(){
		Connection conn=getConnection();
		List<Room> rooms=dao.getRoomDetail(conn);
		close(conn);
		return rooms;
	}
	public Room getARoomService() {
		Connection conn=getConnection();
		Room room=dao.getARoom(conn);
		close(conn);
		return room;
	}

}
