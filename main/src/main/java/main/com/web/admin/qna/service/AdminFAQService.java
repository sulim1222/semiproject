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

    public List<FAQ> searchPAY(String faqCategory, String title, String location, int cPage, int numPerPage) {
        Connection conn = getConnection();
        List<FAQ> result = dao.searchPAY(conn, faqCategory, title, location, cPage, numPerPage);
        close(conn);
        return result;
    }

    public int searchPAYCount(String faqCategory, String title) {
        Connection conn = getConnection();
        int count = dao.searchPAYCount(conn, faqCategory, title);
        close(conn);
        return count;
    }

    public List<FAQ> searchETC(String faqCategory, String title, String location, int cPage, int numPerPage) {
        Connection conn = getConnection();
        List<FAQ> result = dao.searchETC(conn, faqCategory, title, location, cPage, numPerPage);
        close(conn);
        return result;
    }

    public int searchETCCount(String faqCategory, String title) {
        Connection conn = getConnection();
        int count = dao.searchETCCount(conn, faqCategory, title);
        close(conn);
        return count;
    }

    public int insertNewFAQ(FAQ faq) {
        Connection conn = getConnection();
        int result = dao.insertNewFAQ(conn, faq);
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
    }

    public int updateFAQ(FAQ faq) {
        Connection conn = getConnection();
        int result = dao.updateFAQ(conn, faq);
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

    public FAQ getFAQById(int faqAllNo) {
        Connection conn = getConnection();
        FAQ faq = dao.getFAQById(conn, faqAllNo);
        close(conn);
        return faq;
    }
}
