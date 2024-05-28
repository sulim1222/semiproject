package main.com.web.review.dao;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import main.com.web.admin.qna.dao.AdminFAQDao;
import main.com.web.enjoy.dto.Rating;
import main.com.web.review.dto.Review;

public class ReviewDao {
    private Properties sql = new Properties();

    public ReviewDao() {
    	 String path = AdminFAQDao.class.getResource("/sql/review_sql.properties").getPath();
         try (FileReader fr = new FileReader(path)) {
             sql.load(fr);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    public int insertReview(Connection conn, Review review) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
        	String query=sql.getProperty("insertReview");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, review.getReviewContent());
            pstmt.setInt(2, review.getMemberNo());
            pstmt.setString(3, review.getCategory());
            result= pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertRating(Connection conn, Rating rating) {
        PreparedStatement pstmt = null;	
        int result = 0;
        try {
        	pstmt=conn.prepareStatement(sql.getProperty("insertRating"));
            pstmt.setInt(1, rating.getRatingScore());
            pstmt.setInt(2, rating.getMemberNo());
            pstmt.setString(3, rating.getCategory()); 
            result=pstmt.executeUpdate(); //int
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}
