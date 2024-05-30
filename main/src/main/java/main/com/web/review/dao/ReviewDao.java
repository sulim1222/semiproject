package main.com.web.review.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.review.dto.Review;
import main.com.web.rating.dto.Rating;

public class ReviewDao {
    private Properties prop = new Properties();

    public ReviewDao() {
        try {
            String path = ReviewDao.class.getResource("/sql/review_sql.properties").getPath();
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int insertReview(Connection conn, Review review) {
        PreparedStatement pstmt = null;
        int result = 0;
        String sql = prop.getProperty("insertReview");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, review.getReviewContent());
            pstmt.setInt(2, review.getMemberNo());
            pstmt.setString(3, review.getCategory());
            pstmt.setInt(4, review.getEntityId());
            result = pstmt.executeUpdate();
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
        String sql = prop.getProperty("insertRating");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rating.getRatingScore());
            pstmt.setInt(2, rating.getMemberNo());
            pstmt.setString(3, rating.getCategory());
            pstmt.setInt(4, rating.getEntityId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<Review> getReviewsByCafeId(Connection conn, int cafeId) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Review> reviews = new ArrayList<>();
        String sql = prop.getProperty("getReviewsByCafeId");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cafeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Review review = Review.builder()
                        .reviewNo(rs.getInt("reviewNo"))
                        .reviewContent(rs.getString("reviewContent"))
                        .memberNo(rs.getInt("memberNo"))
                        .category(rs.getString("category"))
                        .entityId(rs.getInt("entityId"))
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return reviews;
    }
}
