package main.com.web.mypage.service;

import static main.com.web.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import main.com.web.mypage.dao.MyPageDao;
import main.com.web.reservation.dto.Reserve;



public class MyPageService {
	
	private MyPageDao dao = new MyPageDao();

	
	public int updateMember(String memberId, String newName, String newPassword, String newPhone) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, memberId, newName, newPassword, newPhone);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	public List<Reserve> selectMyReservation(String loginId) {
		Connection conn = getConnection();
		List<Reserve> reservations = dao.selectMyReservation(conn, loginId);
		close(conn);
		return reservations;
	}

}
