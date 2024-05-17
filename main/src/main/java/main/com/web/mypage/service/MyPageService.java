package main.com.web.mypage.service;

import static main.com.web.common.JDBCTemplate.*;
import java.sql.Connection;

import main.com.web.mypage.dao.MyPageDao;



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

}
