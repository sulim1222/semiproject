package main.com.web.review.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.com.web.member.dto.Member;
import main.com.web.rating.dto.Rating;
import main.com.web.review.dto.Review;
import main.com.web.review.service.ReviewService;

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

        // 사용자가 로그인하지 않은 경우 로그인 페이지로 리디렉션
        if (loggedInUser == null) {
            response.sendRedirect(request.getContextPath() + "/member/loginPage");
            return;
        }

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String category = request.getParameter("category");
        String text = request.getParameter("text");
        int score = Integer.parseInt(request.getParameter("rating"));

        Review review = Review.builder()
                .reviewNo(categoryId)
                .reviewContent(text)
                .memberNo(loggedInUser.getMemberNo())
                .category(category)
                .entityId(categoryId) // 추가된 부분
                .build();

        Rating rating = Rating.builder()
                .ratingNo(categoryId)
                .ratingScore(score)
                .memberNo(loggedInUser.getMemberNo())
                .category(category)
                .entityId(categoryId) // 추가된 부분
                .build();

        ReviewService reviewService = new ReviewService();

        // 리뷰와 점수를 DB에 저장
        boolean success = reviewService.saveReviewAndRating(review, rating);

        // 저장 후 이전 페이지로 리디렉션
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(request.getContextPath() + "/enjoy/cafe");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
