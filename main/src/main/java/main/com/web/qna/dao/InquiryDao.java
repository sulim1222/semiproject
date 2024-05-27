package main.com.web.qna.dao;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.rollback;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import main.com.web.qna.dto.Inquiry;

public class InquiryDao {
    private Properties sql = new Properties();

    public InquiryDao() {
        String path = InquiryDao.class.getResource("/sql/inquiry_sql.properties").getPath();
        try (FileReader fr = new FileReader(path)) {
            sql.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int insertInquiry(Connection conn, Inquiry inquiry) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("insertInquiry"));
            pstmt.setString(1, inquiry.getInquiryType());
            pstmt.setString(2, inquiry.getTitle());
            pstmt.setString(3, inquiry.getContent());
            pstmt.setDate(4, inquiry.getInquiryDate());
            pstmt.setTime(5, inquiry.getInquiryTime()); 
            pstmt.setInt(6, inquiry.getMemberNo());
            result = pstmt.executeUpdate();
            if (result > 0) {
                commit(conn);
            } else {
                rollback(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(conn);
        } finally {
            close(pstmt);
        }
        return result;
    }
}
