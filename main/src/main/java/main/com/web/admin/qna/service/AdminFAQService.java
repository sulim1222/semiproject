package main.com.web.admin.qna.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;
import static main.com.web.admin.reserve.common.JDBCTemplate.commit;
import static main.com.web.admin.reserve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import main.com.web.admin.qna.dao.AdminFAQDao;
import main.com.web.qna.dto.FAQ;

public class AdminFAQService {

    private AdminFAQDao dao = new AdminFAQDao();

    public List<FAQ> selectFAQAll(int cPage, int numPerPage) {
        Connection conn = getConnection();
        List<FAQ> faqs = dao.selectFAQAll(conn, cPage, numPerPage);
        close(conn);
        return faqs;
    }

    public int selectFAQAllCount() {
        Connection conn = getConnection();
        int result = dao.selectFAQAllCount(conn);
        close(conn);
        return result;
    }

    public List<FAQ> searchPAY(String faqCategory, String Title, String location, int cPage, int numPerPage) {
        Connection conn = getConnection();
        List<FAQ> result = dao.searchPAY(conn, faqCategory, Title, location, cPage, numPerPage);
        close(conn);
        return result;
    }

    public int searchPAYCount(String type, String keyword) {
        Connection conn = getConnection();
        int count = dao.searchPAYCount(conn, type, keyword);
        close(conn);
        return count;
    }

    public List<FAQ> searchETC(String faqCategory, String Title, String location, int cPage, int numPerPage) {
        Connection conn = getConnection();
        List<FAQ> result = dao.searchETC(conn, faqCategory, Title, location, cPage, numPerPage);
        close(conn);
        return result;
    }

    public int searchETCCount(String type, String keyword) {
        Connection conn = getConnection();
        int count = dao.searchETCCount(conn, type, keyword);
        close(conn);
        return count;
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
