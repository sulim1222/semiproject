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

import main.com.web.enjoy.dto.Food;

public class FoodDao {
	 private Properties sql = new Properties();

	    public FoodDao() {
	        try {
	            String path = FoodDao.class.getResource("/sql/food_sql.properties").getPath();
	            sql.load(new FileReader(path));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public List<Food> selectAllFoods(Connection conn, int cPage, int numPerpage) {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<Food> foods = new ArrayList<>();

	        try {
	            pstmt = conn.prepareStatement(sql.getProperty("selectAllFoods"));
	            int start = (cPage - 1) * numPerpage + 1; 
	            int end = cPage * numPerpage; 
	            pstmt.setInt(1, start);
	            pstmt.setInt(2, end);
	            rs = pstmt.executeQuery();
	            while (rs.next()) {
	                foods.add(getFood(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rs);
	            close(pstmt);
	        }
	        return foods;
	    }

	    public int selectFoodAllCount(Connection conn) {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        int result = 0;
	        try {
	            pstmt = conn.prepareStatement(sql.getProperty("selectFoodAllCount"));
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

	    private Food getFood(ResultSet rs) throws SQLException {
	        return Food.builder()
	                .foodNo(rs.getInt("foodNo"))
	                .foodName(rs.getString("foodName"))
	                .foodAddress(rs.getString("foodAddress"))
	                .foodPhone(rs.getString("foodPhone"))
	                .foodTime(rs.getString("foodTime"))
	                .foodImg(rs.getString("foodImg"))
	                .location(rs.getString("location"))
	                .category(rs.getString("category"))
	                .build();
	    }
	}


