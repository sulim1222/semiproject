package main.com.web.admin.sales.dao;

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

import main.com.web.admin.reserve.dao.AdminReserveDao;
import main.com.web.admin.reserve.dto.Sales;

public class AdminSalesDao {

private Properties sql=new Properties();
	
	{
		String path=AdminReserveDao.class
				.getResource("/sql/admin/sql_admin.properties").getPath();
		try (FileReader fr=new FileReader(path)){
			sql.load(fr);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Sales> salesByMonth(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Sales> sales=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("salesByMonth"));
			rs=pstmt.executeQuery();
			 while (rs.next()) {
                String month = rs.getString("month");
                int revenue = rs.getInt("revenue");
                Sales salesByMonth = new Sales(month, revenue);
                sales.add(salesByMonth);
	            }
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(conn);
		}
		return sales;
	}
	
	 public List<Sales> addReservation(Connection conn, String month, int newRevenue) {
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List<Sales> sales = new ArrayList<>();
	        try {
	            pstmt = conn.prepareStatement(sql.getProperty("addNewRevenue"));
	            pstmt.setInt(1, newRevenue);
	            pstmt.setString(2, month);
	            pstmt.executeUpdate();
	            while (rs.next()) {
	                String months = rs.getString("month");
	                int revenue = rs.getInt("revenue");
	                Sales salesByMonth = new Sales(months, revenue);
	                sales.add(salesByMonth);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(rs);
	            close(pstmt);
	        }
	        return sales;
	 }

	
	
}
