package main.com.web.enjoy.dao;


import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.com.web.enjoy.dto.Rating;

public class RatingDao {
	public int insertRating(Connection conn, Rating rating) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "INSERT INTO Rating (ratingNo, cafeNo, ratingScore, MemberNo) VALUES (SEQ_RATING_NO.NEXTVAL, ?, ?, ?)";
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, rating.getRatingNo());
            pstmt.setInt(2, rating.getRatingScore());
            pstmt.setInt(3, rating.getMemberNo());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
