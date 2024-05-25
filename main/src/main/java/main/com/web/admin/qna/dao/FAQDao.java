package main.com.web.admin.qna.dao;

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
//
//        public List<FAQ> selectAllFAQs(Connection conn, int cPage, int numPerPage) {
//            PreparedStatement pstmt = null;
//            ResultSet rs = null;
//            List<FAQ> result = new ArrayList<>();
//            try {
//                pstmt = conn.prepareStatement(sql.getProperty("selectAllFAQs"));
//                pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
//                pstmt.setInt(2, cPage * numPerPage);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    result.add(getFAQ(rs));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                close(rs);
//                close(pstmt);
//            }
//            return result;
//        }

        public static FAQ getFAQ(ResultSet rs) throws SQLException {
            return FAQ.builder()
                .faqAllNo(rs.getInt("faq_all_no"))
                .faqCategory(rs.getString("faq_category"))
                .faqTitle(rs.getString("faq_title"))
                .faqContent(rs.getString("faq_content"))
                .faqWriter(rs.getString("faq_writer"))
                .faqDate(rs.getDate("faq_date"))
                .hotelNo(rs.getString("hotel_no"))
                .build();
        }
    }