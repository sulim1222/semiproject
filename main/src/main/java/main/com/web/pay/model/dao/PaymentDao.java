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

	private Properties sql = new Properties(); //properties 값을 가져온다
	{
		String path =MemberDao.class.getResource("/sql/payment_sql.properties").getPath();
		try (FileReader fr=new FileReader(path)){ //패스 경로를 찾아 파일을 받아온다
			sql.load(fr); //properteis에 load해준다. 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int savePaymentInfo(Connection conn, String impUid, String merchantUid) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("savePaymentInfo"));
			
			pstmt.setString(1, impUid);
			pstmt.setString(2, merchantUid);

			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
		
	}

}
