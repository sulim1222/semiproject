package main.com.web.admin.qna.dao;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;
import static main.com.web.admin.reserve.common.JDBCTemplate.commit;
import static main.com.web.admin.reserve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import main.com.web.qna.dto.FAQ;

public class AdminFAQService {

    private AdminFAQDao dao = new AdminFAQDao();

    public List<FAQ> selectFAQAll(int cPage, int numPerPage, String searchCategory, String searchKeyword) {
        Connection conn = getConnection();
        List<FAQ> faqs = dao.selectFAQAll(conn, cPage, numPerPage, searchCategory, searchKeyword);
        close(conn);
        return faqs;
    }

    public int selectFAQAllCount(String searchCategory, String searchKeyword) {
        Connection conn = getConnection();
        int result = dao.selectFAQAllCount(conn, searchCategory, searchKeyword);
        close(conn);
        return result;
    }

    public int insertNewFAQ(FAQ f) {
        Connection conn = getConnection();
        int result = dao.insertNewFAQ(conn, f);
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    public int updateFAQ(FAQ f) {
        Connection conn = getConnection();
        int result = dao.updateFAQ(conn, f);
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    public int deleteFAQ(int faqAllNo) {
        Connection conn = getConnection();
        int result = dao.deleteFAQ(conn, faqAllNo);
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }
}
