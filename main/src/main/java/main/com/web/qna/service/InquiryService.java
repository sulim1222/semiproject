package main.com.web.qna.service;

import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.close;

import java.sql.Connection;

import main.com.web.qna.dao.InquiryDao;
import main.com.web.qna.dto.Inquiry;

public class InquiryService {
    private InquiryDao dao = new InquiryDao();

    public int saveInquiry(Inquiry inquiry) {
        Connection conn = getConnection();
        int result = dao.insertInquiry(conn, inquiry);
        close(conn);
        return result;
    }
}
