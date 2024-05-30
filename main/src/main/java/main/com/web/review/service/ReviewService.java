package main.com.web.review.service;

import java.sql.Connection;
import java.util.List;

import main.com.web.review.dao.ReviewDao;
import main.com.web.review.dto.Review;
import main.com.web.rating.dto.Rating;

import static main.com.web.common.JDBCTemplate.*;

public class ReviewService {
    private ReviewDao dao = new ReviewDao();

    public boolean saveReviewAndRating(Review review, Rating rating) {
        Connection conn = getConnection();
        boolean result = false;
        try {
            int reviewResult = dao.insertReview(conn, review);
            int ratingResult = dao.insertRating(conn, rating);
            if (reviewResult > 0 && ratingResult > 0) {
                commit(conn);
                result = true;
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

    public List<Review> getReviewsByCafeId(int cafeId) {
        Connection conn = getConnection();
        List<Review> reviews = dao.getReviewsByCafeId(conn, cafeId);
        close(conn);
        return reviews;
    }
}
