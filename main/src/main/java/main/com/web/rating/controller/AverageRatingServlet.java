package main.com.web.rating.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.enjoy.dto.Cafe;
import main.com.web.enjoy.service.CafeService;
import main.com.web.rating.service.RatingService;
import main.com.web.rating.dto.Rating;

/**
 * Servlet implementation class AverageRatingServlet
 */
@WebServlet("/rating/average")
public class AverageRatingServlet extends HttpServlet {
	
	    private CafeService cafeService = new CafeService();
	    private RatingService ratingService = new RatingService();

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Cafe> cafes = cafeService.selectAllCafes(0, 0);
	        List<Rating> ratings = ratingService.getAllRatings();

	        // 평균 별점 계산
	        Map<Integer, Double> averageRatings = new HashMap<>();
	        for (Cafe cafe : cafes) {
	            double averageRating = ratings.stream()
	                .filter(r -> r.getEntityId() == cafe.getCafeNo() && "CAFE".equals(r.getCategory()))
	                .mapToInt(Rating::getRatingScore)
	                .average()
	                .orElse(0.0);
	            averageRatings.put(cafe.getCafeNo(), averageRating);
	        }

	        request.setAttribute("cafes", cafes);
	        request.setAttribute("averageRatings", averageRatings);
	        request.getRequestDispatcher("/WEB-INF/views/cafe.jsp").forward(request, response);
	    }
	}