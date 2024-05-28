package main.com.web.qna.service;

import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import main.com.web.qna.dao.FAQDao;
import main.com.web.qna.dto.FAQ;

public class FAQService {
    private FAQDao dao = new FAQDao();

    public List<FAQ> selectFAQAll(int cPage, int numPerpage) {
        Connection conn = getConnection();
        int start = (cPage - 1) * numPerpage + 1;
        int end = cPage * numPerpage;
        List<FAQ> faqs = dao.selectFAQAll(conn, start, end);
        close(conn);
        return faqs;
    }

    public int selectFAQAllCount() {
        Connection conn = getConnection();
        int result = dao.selectFAQAllCount(conn);
        close(conn);
        return result;
    }
}
