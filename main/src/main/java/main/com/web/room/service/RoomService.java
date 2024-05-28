package main.com.web.room.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import main.com.web.room.dao.RoomDao;
import main.com.web.room.dto.Room;
import main.com.web.room.dto.RoomImages;

public class RoomService {
	
	private RoomDao dao=new RoomDao();
	
	//몰라
	public List<Room> GetAllRoomsService() {
	      Connection conn=getConnection();
	      List<Room> rooms=dao.getAllRooms(conn);
	      close(conn);
	      return rooms;
	   }
	
	//몰라
//	public Room getRoomDetailService(String roomType,String location){
//		Connection conn=getConnection();
//		Room room=dao.getRoomDetail(conn,roomType,location);
//		close(conn);
//		return room;
//	}
	
	//해진작성
//	public List<String> getRoomImagesService(int roomNo){
//		Connection conn=getConnection();
//		List<String> roomimages=dao.getRoomImages(conn, roomNo);
//		close(conn);
//		return roomimages;
//	}
	
	//gpt가 디테일+이미지 합쳐서만듬
	public Room getRoomDetailService(String roomType, String location) {
        Connection conn = getConnection();
        Room room = dao.getRoomDetail(conn, roomType, location);
        if (room != null) {
            List<String> roomImages = dao.getRoomImages(conn, room.getRoomNo());
            room.setRoomImages(roomImages);
        }
        close(conn);
        return room;
    }

	public List<RoomImages> getImgs(int count) {
		Connection conn = getConnection();
		List<RoomImages> imges = dao.getImgs(conn,count);
		close(conn);
		return imges;
	}	
	
}