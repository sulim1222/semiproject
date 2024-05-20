package main.com.web.reservation.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.room.dto.RoomTest;

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
	public List<RoomTest> selectRoom(Connection conn, String roomType) {
		List<RoomTest> roomList =new ArrayList<RoomTest>();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt =conn.prepareStatement(sql.getProperty("selectRoom"));
			pstmt.setString(1, roomType);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				roomList.add(getRoomTest(rs));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return roomList;
	}
	
	public RoomTest getRoomTest(ResultSet rs) throws SQLException {
		return RoomTest.builder().roomNo(rs.getInt("roomNo")).roomPrice(rs.getInt("roomPrice")).location(rs.getString("roomPrice"))
				.roomAmenity(rs.getString("roomAmenity"))
				.roomCount(rs.getInt("roomCount")).rooArea(rs.getInt("rooArea"))
				.roomPeoleNo(rs.getInt("roomPeoleNo"))
				.roomType(rs.getString("roomType"))
				.roomInform(rs.getString("roomInform"))
				.bedType(rs.getString("bedType"))
				.build();
	}

}
