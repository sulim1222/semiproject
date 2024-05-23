//package main.com.web.admin.qna.dao;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import main.com.web.qna.dto.OneToOne;
//
//public class OneToOneDao {
//	 private Properties sql = new Properties();
//
//	    public OneToOneDao() {
//	        String path = OneToOneDao.class.getResource("/sql/one_to_one_sql.properties").getPath();
//	        try (FileReader fr = new FileReader(path)) {
//	            sql.load(fr);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public OneToOne selectInquiryById(Connection conn, int inquiryId) {
//	        PreparedStatement pstmt = null;
//	        ResultSet rs = null;
//	        OneToOne inquiry = null;
//	        try {
//	            pstmt = conn.prepareStatement(sql.getProperty("selectInquiryById"));
//	            pstmt.setInt(1, inquiryId);
//	            rs = pstmt.executeQuery();
//	            if (rs.next()) {
//	                inquiry = getOneToOne(rs);
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            close(rs);
//	            close(pstmt);
//	        }
//	        return inquiry;
//	    }
//
//	    public static OneToOne getOneToOne(ResultSet rs) throws SQLException {
//	        return OneToOne.builder()
//	            .onToOneInquiryId(rs.getInt("one_to_one_inquiry_id"))
//	            .inquiryType(rs.getString("inquiry_type"))
//	            .title(rs.getString("title"))
//	            .content(rs.getString("content"))
//	            .inquiryDate(rs.getDate("inquiry_date"))
//	            .MemberNo(rs.getString("member_no"))
//	            .build();
//	    }
//	}