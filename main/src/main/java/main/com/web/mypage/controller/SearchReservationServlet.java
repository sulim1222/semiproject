package main.com.web.mypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import main.com.web.mypage.service.MyPageService;
import main.com.web.reservation.dto.Reserve;

@WebServlet("/mypage/searchReservation")
public class SearchReservationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchBy = request.getParameter("searchBy");
        String keyword = request.getParameter("keyword");

        List<Reserve> searchResult = new ArrayList<>();

        if ("reserveNo".equals(searchBy)) {
            // 예약번호로 검색하는 로직
            searchResult = new MyPageService().searchByReserveNo(keyword);
        } else if ("location".equals(searchBy)) {
            // 호텔명으로 검색하는 로직
            searchResult = new MyPageService().searchByLocation(keyword);
        }

        // JSON 형식으로 검색 결과 반환
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 문자열 생성
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < searchResult.size(); i++) {
            Reserve reserve = searchResult.get(i);
            jsonBuilder.append("{");
            jsonBuilder.append("\"reserveNo\":\"").append(reserve.getReserveNo()).append("\",");
            jsonBuilder.append("\"location\":\"").append(reserve.getLocation()).append("\",");
            jsonBuilder.append("\"roomType\":\"").append(reserve.getRoomType()).append("\",");
            jsonBuilder.append("\"checkInDate\":\"").append(reserve.getCheckInDate()).append("\",");
            jsonBuilder.append("\"checkOutDate\":\"").append(reserve.getCheckOutDate()).append("\",");
            jsonBuilder.append("\"payPrice\":").append(reserve.getPayPrice());
            if (i < searchResult.size() - 1) {
                jsonBuilder.append("},");
            } else {
                jsonBuilder.append("}");
            }
        }
        jsonBuilder.append("]");

        // JSON 문자열을 클라이언트로 전송
        PrintWriter out = response.getWriter();
        out.print(jsonBuilder.toString());
        out.flush();
    }
}
