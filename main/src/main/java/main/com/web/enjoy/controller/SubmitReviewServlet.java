package main.com.web.enjoy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import main.com.web.enjoy.dto.Rating;
import main.com.web.enjoy.service.RatingService;
import main.com.web.enjoy.service.ReviewService;
import main.com.web.review.dto.Review;

@WebServlet("/enjoy/submitReview")
public class SubmitReviewServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
     
	    public SubmitReviewServlet() {
	        super();
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        Gson gson = new Gson();
	        ReviewRequest reviewRequest = gson.fromJson(request.getReader(), ReviewRequest.class);

	        Review review = Review.builder()
	                .reviewNo(reviewRequest.cafeId)
	                .reviewContent(reviewRequest.text)
	                .memberNo(1) // 사용자 인증 로직 추가 필요
	                .build();

	        Rating rating = Rating.builder()
	                .ratingNo(reviewRequest.cafeId)
	                .ratingScore(reviewRequest.rating)
	                .MemberNo(1) // 사용자 인증 로직 추가 필요
	                .build();

	        ReviewService reviewService = new ReviewService();
	        RatingService ratingService = new RatingService();

	        boolean reviewSaved = reviewService.saveReview(review);
	        boolean ratingSaved = ratingService.saveRating(rating);

	        response.getWriter().write(gson.toJson(new SubmitReviewResponse(reviewSaved && ratingSaved)));
	    }

	    private static class ReviewRequest {
	        int cafeId;
	        String name;
	        int rating;
	        String text;
	    }

	    private static class SubmitReviewResponse {
	        boolean success;

	        SubmitReviewResponse(boolean success) {
	            this.success = success;
	        }
	    }
	}