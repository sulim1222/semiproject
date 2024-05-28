package main.com.web.enjoy.service;

import java.sql.Connection;

import main.com.web.review.dao.ReviewDao;
import main.com.web.review.dto.Review;
import main.com.web.enjoy.dto.Rating;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.rollback;

public class ReviewService {

    private ReviewDao dao = new ReviewDao();

    public boolean saveReviewAndRating(Review review, Rating rating) {
        Connection conn = getConnection();
        boolean result = false;
        try {
            int reviewResult = dao.insertReview(conn, review);
            int ratingResult = dao.insertRating(conn, rating);
            if (reviewResult > 0 && ratingResult > 0) {
            	result=true;
                commit(conn);
                
            } else {
                rollback(conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rollback(conn);
        } finally {
            close(conn);
        }
        return result;
    }
}
