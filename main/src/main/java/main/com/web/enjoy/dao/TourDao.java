package main.com.web.enjoy.dao;

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

import main.com.web.enjoy.dto.Tour;

public class TourDao {
	 private Properties sql = new Properties();

	    public TourDao() {
	        try {
	            String path = TourDao.class.getResource("/sql/tour_sql.properties").getPath();
	            sql.load(new FileReader(path));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Tour> selectAllTours(Connection conn, int cPage, int numPerpage) {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<Tour> tours = new ArrayList<>();

	        try {
	            pstmt = conn.prepareStatement(sql.getProperty("selectAllTours"));
	            int start = (cPage - 1) * numPerpage + 1; 
	            int end = cPage * numPerpage; 
	            pstmt.setInt(1, start);
	            pstmt.setInt(2, end);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                tours.add(getTour(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rs);
	            close(pstmt);
	        }
	        return tours;
	    }

	    public int selectTourAllCount(Connection conn) {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        int result = 0;
	        try {
	            pstmt = conn.prepareStatement(sql.getProperty("selectTourAllCount"));
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	                result = rs.getInt(1);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rs);
	            close(pstmt);
	        }
	        return result;
	    }

	    private Tour getTour(ResultSet rs) throws SQLException {
	        return Tour.builder()
	                .tourNo(rs.getInt("tourNo"))
	                .tourName(rs.getString("tourName"))
	                .tourAddress(rs.getString("tourAddress"))
	                .tourPhone(rs.getString("tourPhone"))
	                .tourTime(rs.getString("tourTime"))
	                .tourImg(rs.getString("tourImg"))
	                .location(rs.getString("location"))
	                .category(rs.getString("category"))
	                .build();
	    }
	}

