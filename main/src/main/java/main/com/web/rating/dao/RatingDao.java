package main.com.web.rating.dao;

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

import main.com.web.rating.dto.Rating;

public class RatingDao {
    private Properties sql = new Properties();

    public RatingDao() {
        try {
            String path = RatingDao.class.getResource("/sql/rating_sql.properties").getPath();
            sql.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Rating> getAllRatings(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Rating> ratings = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql.getProperty("selectAllRatings"));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ratings.add(getRating(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return ratings;
    }

    private Rating getRating(ResultSet rs) throws SQLException {
        return Rating.builder()
                .ratingNo(rs.getInt("ratingNo"))
                .ratingScore(rs.getInt("ratingScore"))
                .memberNo(rs.getInt("memberNo"))
                .category(rs.getString("category"))
                .entityId(rs.getInt("entityId"))
                .build();
    }

    public int insertRating(Connection conn, Rating rating) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("insertRating"));
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
}
