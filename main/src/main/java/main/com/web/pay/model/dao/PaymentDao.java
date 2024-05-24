package main.com.web.pay.model.dao;

import static main.com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import main.com.web.member.dao.MemberDao;
import main.com.web.pay.model.dto.Payment;

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

    public int savePaymentInfo(Connection conn, String impUid, String merchantUid, int payPrice, String paymentMethod, String status, String location, String reserveNo) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = conn.prepareStatement(sql.getProperty("savePaymentInfo"));
            pstmt.setString(1, impUid);
            pstmt.setString(2, merchantUid);
            pstmt.setInt(3, payPrice);
            pstmt.setString(4, paymentMethod);
            pstmt.setString(5, status);
            pstmt.setString(6, location);
            pstmt.setString(7, reserveNo);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    public Payment selectPayment(Connection conn, String reserveNo) {
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	Payment p = null;
    	try {
    		pstmt = conn.prepareStatement(sql.getProperty("selectPayment"));
    		pstmt.setString(1, reserveNo);
    		rs = pstmt.executeQuery();
    		if(rs.next()) p = getPayment(rs);
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		close(rs);
    		close(pstmt);
    	}
    	
    	return p;
    }
	private Payment getPayment(ResultSet rs) throws SQLException{
		return Payment.builder()
				.payNo(rs.getString("payno"))
				.payCoupon(rs.getInt("paycoupon"))
				.payMethod(rs.getString("paymethod"))
				.payPrice(rs.getInt("payprice"))
				.payDate(rs.getDate("paydate"))
				.payBank(rs.getString("paybank"))
				.reserveNo(rs.getString("reserveno"))
				.location(rs.getString("location"))
				.merchant_uid(rs.getString("merchant_uid"))
				.status(rs.getString("status"))
				.build();
	}
}