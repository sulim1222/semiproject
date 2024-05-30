package main.com.web.review.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import main.com.web.review.dto.Review;
import main.com.web.review.service.ReviewService;

@WebServlet("/enjoy/getReviews")
public class GetReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReviewService reviewService = new ReviewService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cafeId = Integer.parseInt(request.getParameter("cafeId"));
        List<Review> reviews = reviewService.getReviewsByCafeId(cafeId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // GSON을 사용하여 리뷰 데이터를 JSON 형식으로 변환
        Gson gson = new Gson();
        String json = gson.toJson(reviews);
        
        out.print(json);
        out.flush();
    }
}
