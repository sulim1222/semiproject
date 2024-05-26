package main.com.web.admin.qna.dao;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.com.web.qna.dto.FAQ;

public class AdminFAQDao {

    public List<FAQ> selectFAQAll(Connection conn, int cPage, int numPerPage, String searchCategory, String searchKeyword) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FAQ> result = new ArrayList<>();
        String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, A.* FROM (SELECT * FROM FAQ WHERE (faqCategory = ? OR ? = 'ALL') AND (faqTitle LIKE '%' || ? || '%' OR faqContent LIKE '%' || ? || '%') ORDER BY faqDate DESC) A) WHERE RNUM BETWEEN ? AND ?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchCategory);
            pstmt.setString(2, searchCategory);
            pstmt.setString(3, searchKeyword);
            pstmt.setString(4, searchKeyword);
            pstmt.setInt(5, (cPage - 1) * numPerPage + 1);
            pstmt.setInt(6, cPage * numPerPage);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(getFAQ(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return result;
    }

    public int selectFAQAllCount(Connection conn, String searchCategory, String searchKeyword) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        String query = "SELECT COUNT(*) FROM FAQ WHERE (faqCategory = ? OR ? = 'ALL') AND (faqTitle LIKE '%' || ? || '%' OR faqContent LIKE '%' || ? || '%')";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, searchCategory);
            pstmt.setString(2, searchCategory);
            pstmt.setString(3, searchKeyword);
            pstmt.setString(4, searchKeyword);
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

    public int insertNewFAQ(Connection conn, FAQ faq) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "INSERT INTO FAQ (faqAllNo, faqCategory, faqTitle, faqContent, faqDate, location) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, faq.getFaqAllNo());
            pstmt.setString(2, faq.getFaqCategory());
            pstmt.setString(3, faq.getFaqTitle());
            pstmt.setString(4, faq.getFaqContent());
            pstmt.setDate(5, faq.getFaqDate());
            pstmt.setString(6, faq.getLocation());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int updateFAQ(Connection conn, FAQ faq) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "UPDATE FAQ SET faqCategory=?, faqTitle=?, faqContent=?, faqDate=? WHERE faqAllNo=?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faq.getFaqCategory());
            pstmt.setString(2, faq.getFaqTitle());
            pstmt.setString(3, faq.getFaqContent());
            pstmt.setDate(4, faq.getFaqDate());
            pstmt.setInt(5, faq.getFaqAllNo());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int deleteFAQ(Connection conn, int faqAllNo) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "DELETE FROM FAQ WHERE faqAllNo=?";
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, faqAllNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
