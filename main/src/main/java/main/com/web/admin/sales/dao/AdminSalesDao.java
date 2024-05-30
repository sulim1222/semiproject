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
import main.com.web.admin.reserve.dto.Member;
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
				sales.add(AdminSalesDao.getSalesByMonth(rs));
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(conn);
		}
		return sales;
	}
	 
	public List<Sales> salesByLocation(Connection conn) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Sales> sales = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql.getProperty("salesByLocation"));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sales.add(AdminSalesDao.getSalesByLocation(rs));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return sales;
    }
	
	
	
	
	
	public static Sales getSalesByMonth(ResultSet rs) throws SQLException{
		return Sales.builder()
				.month(rs.getString("month"))
				.revenue(rs.getInt("revenue"))
				.build();
	}
	public static Sales getSalesByLocation(ResultSet rs) throws SQLException{
		return Sales.builder()
				.location(rs.getString("location"))
				.revenue(rs.getInt("revenue"))
				.build();
	}
	
	
	
	
}
