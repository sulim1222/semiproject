package main.com.web.enjoy.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import main.com.web.enjoy.dao.ReviewDaoEnjoy;
import main.com.web.review.dao.ReviewDao;
import main.com.web.review.dto.Review;

//reviewDao와 중복. 내용확인하여 결정하 
public class ReviewService {
	 private ReviewDaoEnjoy dao = new ReviewDaoEnjoy();

	    public boolean saveReview(Review review) {
	        Connection conn = getConnection();
	        boolean result = dao.insertReview(conn, review) > 0;
	        close(conn);
	        return result;
	    }
	}

