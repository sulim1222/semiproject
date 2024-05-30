package main.com.web.enjoy.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import main.com.web.enjoy.dao.CafeDao;
import main.com.web.enjoy.dto.Cafe;

public class CafeService {
    private CafeDao dao = new CafeDao();

    public List<Cafe> selectAllCafes(int cPage, int numPerpage) {
        Connection conn = getConnection();
        List<Cafe> cafes = dao.selectAllCafes(conn, cPage, numPerpage);
        close(conn);
        return cafes;
    }

    public int selectCafeAllCount() {
        Connection conn = getConnection();
        int result = dao.selectCafeAllCount(conn);
        close(conn);
        return result;
    }
}
