package main.com.web.enjoy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.web.enjoy.dto.Food;
import main.com.web.enjoy.service.FoodService;
import main.com.web.rating.dto.Rating;
import main.com.web.rating.service.RatingService;

@WebServlet("/enjoy/food")
public class FoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FoodService foodService = new FoodService();
    private RatingService ratingService = new RatingService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cPage = 1;
        int numPerpage = 6;

        try {
            cPage = Integer.parseInt(request.getParameter("cPage"));
        } catch (NumberFormatException e) {
            cPage = 1;
        }
        try {
            numPerpage = Integer.parseInt(request.getParameter("numPerpage"));
        } catch (NumberFormatException e) {
            numPerpage = 6;
        }

        List<Food> foods = foodService.selectAllFoods(cPage, numPerpage);
        List<Rating> ratings = ratingService.getAllRatings();

        // 평균 별점 계산
        for (Food food : foods) {
            double averageRating = ratings.stream()
                .filter(r -> r.getEntityId() == food.getFoodNo() && "FOOD".equals(r.getCategory()))
                .mapToInt(Rating::getRatingScore)
                .average()
                .orElse(0.0);
            food.setAverageRating(averageRating);
        }

        int totalData = foodService.selectFoodAllCount();
        int totalPage = (int) Math.ceil((double) totalData / numPerpage);
        int pageBarSize = 5;
        int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
        int pageEnd = pageNo + pageBarSize - 1;

        StringBuilder pageBar = new StringBuilder();
        if (pageNo == 1) {
            pageBar.append("<span>[이전]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo - 1).append("&numPerpage=").append(numPerpage).append("'>[이전]</a>");
        }

        while (!(pageNo > pageEnd || pageNo > totalPage)) {
            if (pageNo == cPage) {
                pageBar.append("<span>").append(pageNo).append("</span>");
            } else {
                pageBar.append("<a href='").append(request.getRequestURI())
                       .append("?cPage=").append(pageNo).append("&numPerpage=").append(numPerpage).append("'>").append(pageNo).append("</a>");
            }
            pageNo++;
        }

        if (pageNo > totalPage) {
            pageBar.append("<span>[다음]</span>");
        } else {
            pageBar.append("<a href='").append(request.getRequestURI())
                   .append("?cPage=").append(pageNo).append("&numPerpage=").append(numPerpage).append("'>[다음]</a>");
        }

        request.setAttribute("pageBar", pageBar.toString());
        request.setAttribute("foods", foods);
        request.getRequestDispatcher("/WEB-INF/views/enjoy/food.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
