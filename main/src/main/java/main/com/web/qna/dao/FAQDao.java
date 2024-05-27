package main.com.web.qna.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.com.web.qna.dto.FAQ;

public class FAQDao {
    private Properties sql = new Properties();

    public FAQDao() {
        String path = FAQDao.class.getResource("/sql/faq_sql.properties").getPath();
        try (FileReader fr = new FileReader(path)) {
            sql.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<FAQ> selectFAQAll(Connection conn, int start, int end) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FAQ> faqs = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql.getProperty("selectFAQAll"));
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                faqs.add(getFAQ(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return faqs;
    }

    public int selectFAQAllCount(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("selectFAQAllCount"));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return result;
    }

    private FAQ getFAQ(ResultSet rs) throws SQLException {
        return FAQ.builder()
                .faqAllNo(rs.getInt("faqAllNo"))
                .faqCategory(rs.getString("faqCategory"))
                .faqTitle(rs.getString("faqTitle"))
                .faqContent(rs.getString("faqContent"))
                .faqDate(rs.getDate("faqDate"))
                .location(rs.getString("location"))
                .build();
    }
}
