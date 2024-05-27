package main.com.web.enjoy.service;

import static main.com.web.common.JDBCTemplate.*;

import java.sql.Connection;
import main.com.web.enjoy.dao.RatingDao;
import main.com.web.enjoy.dto.Rating;

public class RatingService {
    private RatingDao dao = new RatingDao();

    public boolean saveRating(Rating rating) {
        Connection conn = getConnection();
        boolean result = dao.insertRating(conn, rating) > 0;
        close(conn);
        return result;
    }
}
