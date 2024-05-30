package main.com.web.enjoy.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import main.com.web.enjoy.dao.TourDao;
import main.com.web.enjoy.dto.Tour;

public class TourService {
	 private TourDao dao = new TourDao();

	    public List<Tour> selectAllTours(int cPage, int numPerpage) {
	        Connection conn = getConnection();
	        List<Tour> tours = dao.selectAllTours(conn, cPage, numPerpage);
	        close(conn);
	        return tours;
	    }

	    public int selectTourAllCount() {
	        Connection conn = getConnection();
	        int result = dao.selectTourAllCount(conn);
	        close(conn);
	        return result;
	    }
	}



