package main.com.web.room.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.room.dto.Room;

public class RoomDao {
	private Properties sql=new Properties();
	   {
	      String path=RoomDao.class.getResource("/sql/room_sql.properties").getPath();
	      try(FileReader fr=new FileReader(path)){
	         sql.load(fr);
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   public List<Room> getAllRooms(Connection conn) {
		      PreparedStatement pstmt=null;
		      ResultSet rs=null;
		      List<Room> rooms=new ArrayList<>();
		      try {
		         pstmt=conn.prepareStatement(sql.getProperty("getroom"));
		         rs=pstmt.executeQuery();
		         while(rs.next()) {
		        	 rooms.add(getRoom(rs));
		         }
		      }catch(SQLException e) {
		         e.printStackTrace();
		      }finally {
		         close(rs);
		         close(pstmt);
		      }return rooms;   
		   }
	   
	   public List<Room> getRoomDetail(Connection conn){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   List<Room> rooms=new ArrayList();
		   try {
			   pstmt=conn.prepareStatement(sql.getProperty("roomdetail"));
			   rs=pstmt.executeQuery();
			   while(rs.next()) {
				   rooms.add(getRoom(rs));
			   }
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return rooms;
	   }
	   public Room getARoom(Connection conn) {
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   Room room=null;
		   try {
			   pstmt=conn.prepareStatement(sql.getProperty("Aroomdetail"));
			   rs=pstmt.executeQuery();
			   if(rs.next()) room=getRoom(rs); 

		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return room;
	   }
	   
	   public static Room getRoom(ResultSet rs) throws SQLException{
		   
		      return Room.builder()
		            .roomNo(rs.getInt("ROOMNO"))
		            .roomPrice(rs.getInt("ROOMPRICE"))
		            .roomAmenity(rs.getString("ROOMAMENITY"))
		            .roomArea(rs.getFloat("ROOMAREA"))
		            .roomType(rs.getString("ROOMTYPE"))
		            .roomInfo(rs.getString("ROOMINFO"))
		            .location(rs.getString("LOCATION"))
		            .category(rs.getString("CATEGORY"))
		            .hotelService(rs.getString("HOTELSERVICE"))
		            .roomUrl(rs.getString("ROOMURL"))
		            .build();
		   }
	
	
	
	
//    public RoomDao() {
//    }
//    //서비스로 로직에서 common 패키지에서 가져옴 20LINE 
//
//    public List<Room> getAllRooms() {
//    	Connection conn=null;
//    	PreparedStatement ps = null;
//    	ResultSet rs = null;
//    	Properties sql = new Properties() ;
//    	String path = RoomDao.class.getResource("/sql/room_sql.properties").getPath();
//    	try(FileReader fr = new FileReader(path)){
//    		sql.load(fr);
//    	}catch (IOException e) {
//    		e.printStackTrace();
//		}
//        List<Room> rooms = new ArrayList<>();
//        try {
//        	ps = conn.prepareStatement(sql.getProperty("getroom"));
//        	//ps.setString(?번호, 값 ) // ?에 값을 넘겨줌
//        	//int result = ps.executeUpdate() select 제외하고
//        	rs = ps.executeQuery();
//            while (rs.next()) {
//                Room room = new Room();
//                rooms.add(room);
//				/* room.setBedType(rs.getString("bedType")); */
//             
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rooms;
//    }
//
//    private List<String> getRoomAmenities(int roomId) {
//    	Connection conn=getConnection();
//        List<String> amenities = new ArrayList<>();
//        try {
//            String query = "SELECT roomamenity FROM room WHERE roomId = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, roomId);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                amenities.add(rs.getString("amenity"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return amenities;
//    }

    // Add other methods like getRoomById, addRoom, updateRoom, deleteRoom as needed
}