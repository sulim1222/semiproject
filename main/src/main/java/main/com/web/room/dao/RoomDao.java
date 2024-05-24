package main.com.web.room.dao;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

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
	
	//파일경로를 가져와서 sql쿼리를 properties객체에 저장	
	private Properties sql=new Properties();
	   {//초기화 블록 : 클래스가 생성될때 무조건 실행됨
		   
	      String path=RoomDao.class.getResource("/sql/room_sql.properties").getPath();
	      try(FileReader fr=new FileReader(path)){
	         sql.load(fr);
	      }catch(IOException e) {
	         e.printStackTrace();
	      }
	   }
	   
	   //모든 Room객체를 가져와서 쿼리결과를 Room객체리스트로 변환후 반환?
	   public List<Room> getAllRooms(Connection conn) {
		      PreparedStatement pstmt=null;
		      ResultSet rs=null;
		      List<Room> rooms=new ArrayList<>();
		      try {
		         pstmt=conn.prepareStatement(sql.getProperty("getallroom"));
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
	   
	   
	   //특정조건에 맞는 Room객체리스트를 반환
	   public Room getRoomDetail(Connection conn, String roomType, String location){
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   Room room=null;
		   try {
			   pstmt=conn.prepareStatement(sql.getProperty("roomdetail"));
			   pstmt.setString(1, roomType);
			   pstmt.setString(2,location);
			   rs=pstmt.executeQuery();
			   if(rs.next()) {
				   room=getRoom(rs);
			   }
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }finally {
			   close(rs);
			   close(pstmt);
		   }return room;
	   }
	   
	   
	   public static Room getRoom(ResultSet rs) throws SQLException{
		   
		   //매개변수있는 생성자보다 쉽게 값을 세팅하여 객체를 생성할수있는방법.그 생성자 안쓴지오래됨,...순서 내용등 까다로워서
		   //Room객체생성 오라클테이블객체를 자바형태의 객체로 옮겨오는작업
		      return Room.builder() //빌더를 쓰기위해 빌드를 열어줌
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
		            .build(); //빌더를 쓰고나서 값을 뱉어주기 위해 닫음
		   }
	
	   
	   
	   private List<String> getRoomAmenity(String roomType) {
	       Connection conn = null;
	       PreparedStatement ps = null;
	       ResultSet rs = null;
	       List<String> roomAmenities = new ArrayList<>();
	       
	       try {
	           conn = getConnection();
	           String query = "SELECT ROOMAMENITY FROM ROOM WHERE ROOMTYPE=?";
	           ps = conn.prepareStatement(query);
	           ps.setString(1, roomType);
	           rs = ps.executeQuery();
	           
	           while (rs.next()) {
	               roomAmenities.add(rs.getString("roomamenity"));
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           close(rs);
	           close(ps);
	           close(conn);
	       }
	       return roomAmenities;
	   }
	
	
//    public RoomDao() {
//    }
//    //서비스 로직에서 common 패키지에서 가져옴 20LINE 
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

    // Add other methods like getRoomById, addRoom, updateRoom, deleteRoom as needed
}