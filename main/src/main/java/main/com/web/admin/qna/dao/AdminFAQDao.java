package main.com.web.admin.qna.dao;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;


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

public class AdminFAQDao {
    
    private Properties sql = new Properties();
    
    public AdminFAQDao() {
        String path = AdminFAQDao.class.getResource("/sql/faq_sql.properties").getPath();
        try (FileReader fr = new FileReader(path)) {
            sql.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<FAQ> selectFAQAll(Connection conn, int cPage, int numPerPage) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FAQ> faqs = new ArrayList<>();
        try {
            String query = sql.getProperty("selectFAQAll");
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
            pstmt.setInt(2, cPage * numPerPage);
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
            String query = sql.getProperty("selectFAQAllCount");
            pstmt = conn.prepareStatement(query);
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

    public List<FAQ> searchPAY(Connection conn, String faqCategory, String Title, String location, int cPage, int numPerPage) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FAQ> faqs = new ArrayList<>();
        try {
            String query = sql.getProperty("searchPAY");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faqCategory);
            pstmt.setString(2, "%" + Title + "%");
            pstmt.setString(3, "%" + Title + "%");
            pstmt.setString(4, location);
            pstmt.setInt(5, (cPage - 1) * numPerPage + 1);
            pstmt.setInt(6, cPage * numPerPage);
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

    public int searchPAYCount(Connection conn, String faqCategory, String Title) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String query = sql.getProperty("searchPAYCount");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faqCategory);
            pstmt.setString(2, "%" + Title + "%");
            pstmt.setString(3, "%" + Title + "%");
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

    public List<FAQ> searchETC(Connection conn, String faqCategory, String Title, String location, int cPage, int numPerPage) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FAQ> faqs = new ArrayList<>();
        try {
            String query = sql.getProperty("searchETC");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faqCategory);
            pstmt.setString(2, "%" + Title + "%");
            pstmt.setString(3, "%" + Title + "%");
            pstmt.setString(4, location);
            pstmt.setInt(5, (cPage - 1) * numPerPage + 1);
            pstmt.setInt(6, cPage * numPerPage);
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

    public int searchETCCount(Connection conn, String faqCategory, String Title) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            String query = sql.getProperty("searchETCCount");
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faqCategory);
            pstmt.setString(2, "%" + Title + "%");
            pstmt.setString(3, "%" + Title + "%");
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
        String query = sql.getProperty("insertNewFAQ");
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, faq.getFaqCategory());
            pstmt.setString(2, faq.getFaqTitle());
            pstmt.setString(3, faq.getFaqContent());
            pstmt.setDate(4, faq.getFaqDate());
            pstmt.setString(5, faq.getLocation());
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
        String query = sql.getProperty("updateFAQ");
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
        String query = sql.getProperty("deleteFAQ");
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

    private static FAQ getFAQ(ResultSet rs) throws SQLException {
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
