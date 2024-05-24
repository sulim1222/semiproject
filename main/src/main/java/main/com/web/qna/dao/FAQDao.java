//package main.com.web.qna.dao;
//
//
//import main.com.web.qna.dto.FAQ;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import java.io.FileReader;
//import java.io.IOException;
//
//import static main.com.web.common.JDBCTemplate.close;
//
//public class FaqDao {
//    private Properties sql = new Properties();
//
//    public FaqDao() {
//        String path = FaqDao.class.getResource("/sql/faq_sql.properties").getPath();
//        try (FileReader fr = new FileReader(path)) {
//            sql.load(fr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //
//
//
//}
//    
//	  
//	
