package main.com.web.enjoy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import main.com.web.enjoy.dto.Rating;
import main.com.web.enjoy.service.RatingService;
import main.com.web.enjoy.service.ReviewService;
import main.com.web.review.dto.Review;
import main.com.web.member.dto.Member;

@WebServlet("/enjoy/submitReview")
public class SubmitReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubmitReviewServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 사용자 정보 가져오기
        HttpSession session = request.getSession();
        Member loggedInUser = (Member) session.getAttribute("member");

        // 사용자가 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/member/login");
            return;
        }

        // 요청-응답 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        // 요청 본문 JSON 데이터를 ReviewRequest 객체로 변환
        ReviewRequest reviewRequest = gson.fromJson(request.getReader(), ReviewRequest.class);

        // Review 객체 생성
        Review review = Review.builder()
                .reviewNo(reviewRequest.categoryId)
                .reviewContent(reviewRequest.text)
                .memberNo(loggedInUser.getMemberNo())
                .category(reviewRequest.category)
                .build();

        // Rating 객체 생성
        Rating rating = Rating.builder()
                .ratingNo(reviewRequest.categoryId)
                .ratingScore(reviewRequest.rating)
                .MemberNo(loggedInUser.getMemberNo())
                .build();

        ReviewService reviewService = new ReviewService();
        RatingService ratingService = new RatingService();

        // 리뷰와 점수를 DB에 저장 후 성공 여부 가져오기
        boolean reviewSaved = reviewService.saveReview(review);
        boolean ratingSaved = ratingService.saveRating(rating);

        // 응답 성공 여부를 JSON 형태로 전송
        response.getWriter().write(gson.toJson(new SubmitReviewResponse(reviewSaved && ratingSaved)));
    }

    // 요청 본문 JSON 파싱을 위한 클래스
    private static class ReviewRequest {
        int categoryId;  // 카페, 관광지, 음식점 등의 ID
        String category; // 카테고리 (CAFE, TOUR, FOOD 등)
        int memberId;    // 사용자 ID
        String name;
        int rating;
        String text;
    }

    // 응답을 위한 클래스
    private static class SubmitReviewResponse {
        boolean success;

        // 생성자
        SubmitReviewResponse(boolean success) {
            this.success = success;
        }
    }
}
