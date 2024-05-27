package main.com.web.enjoy.dao;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.rollback;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.com.web.review.dto.Review;

public class ReviewDaoEnjoy {
	public int insertReview(Connection conn, Review review) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "INSERT INTO Review (reviewNo, cafeNo, reviewContent, MemberNo) VALUES (SEQ_REVIEW_NO.NEXTVAL, ?, ?, ?)";
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, review.getReviewNo());
            pstmt.setString(2, review.getReviewContent());
            pstmt.setInt(3, review.getMemberNo());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}

