package main.com.web.admin.sales.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;
import java.sql.Connection;
import java.util.List;

import main.com.web.admin.reserve.dto.Sales;
import main.com.web.admin.sales.dao.AdminSalesDao;

public class AdminSalesService {

	private AdminSalesDao asd=new AdminSalesDao();
	
	public List<Sales> salesByMonth(){
		Connection conn=getConnection();
		List<Sales> sales=asd.salesByMonth(conn);
		close(conn);
		return sales;
	}

	public List<Sales> addReservation(String month,int newRevenue){
		Connection conn=getConnection();
		List<Sales> sales=asd.addReservation(conn, month, newRevenue);
		close(conn);
		return sales;
	}

}

