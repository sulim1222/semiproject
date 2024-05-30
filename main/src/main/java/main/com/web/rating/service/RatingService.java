package main.com.web.rating.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;
import java.sql.Connection;
import java.util.List;
import main.com.web.rating.dao.RatingDao;
import main.com.web.rating.dto.Rating;

public class RatingService {
    private RatingDao dao = new RatingDao();

    public List<Rating> getAllRatings() {
        Connection conn = getConnection();
        List<Rating> ratings = dao.getAllRatings(conn);
        close(conn);
        return ratings;
    }

    public int insertRating(Rating rating) {
        Connection conn = getConnection();
        int result = dao.insertRating(conn, rating);
        close(conn);
        return result;
    }
}
