package main.com.web.pay.model.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import main.com.web.member.dao.MemberDao;

public class PaymentDao {

    private Properties sql = new Properties();

    {
        String path = MemberDao.class.getResource("/sql/payment_sql.properties").getPath();
        try (FileReader fr = new FileReader(path)) {
            sql.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int savePaymentInfo(Connection conn, String impUid, String merchantUid, String memberId, int payPrice, String paymentMethod, String status, int hotelNo, int reserveNo) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("savePaymentInfo"));
            pstmt.setString(1, impUid);
            pstmt.setString(2, merchantUid);
            pstmt.setString(3, memberId);
            pstmt.setInt(4, payPrice);
            pstmt.setString(5, paymentMethod);
            pstmt.setString(6, status);
            pstmt.setInt(7, hotelNo);
            pstmt.setInt(8, reserveNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
}