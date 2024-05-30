package main.com.web.enjoy.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import main.com.web.enjoy.dao.FoodDao;
import main.com.web.enjoy.dto.Food;

public class FoodService {
	  private FoodDao dao = new FoodDao();

	    public List<Food> selectAllFoods(int cPage, int numPerpage) {
	        Connection conn = getConnection();
	        List<Food> foods = dao.selectAllFoods(conn, cPage, numPerpage);
	        close(conn);
	        return foods;
	    }

	    public int selectFoodAllCount() {
	        Connection conn = getConnection();
	        int result = dao.selectFoodAllCount(conn);
	        close(conn);
	        return result;
	    }
	}

