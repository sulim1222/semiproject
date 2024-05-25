package main.com.web.room.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import main.com.web.room.dao.RoomDao;
import main.com.web.room.dto.Room;

public class RoomService {
	private RoomDao dao=new RoomDao();
	
	public List<Room> GetAllRoomsService() {
	      Connection conn=getConnection();
	      List<Room> rooms=dao.getAllRooms(conn);
	      close(conn);
	      return rooms;
	   }
	
	public Room getRoomDetailService(String roomType,String location){
		Connection conn=getConnection();
		Room room=dao.getRoomDetail(conn,roomType,location);
		close(conn);
		return room;
	}
}