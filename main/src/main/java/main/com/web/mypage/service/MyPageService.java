package main.com.web.mypage.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import main.com.web.mypage.dao.MyPageDao;
import main.com.web.reservation.dto.Reserve;
import main.com.web.review.dto.Review;



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


	public List<Reserve> selectMyReservation(int loginMemberNo) {
		Connection conn = getConnection();
		List<Reserve> reservations = dao.selectMyReservation(conn, loginMemberNo);
		if(reservations.isEmpty()) System.out.println("조회된 예약 없음");
		close(conn);
		return reservations;
	}


	public List<Review> selectMyReview(int loginMemberNo) {
		Connection conn = getConnection();
		List<Review> reviews = dao.selectMyReview(conn, loginMemberNo);
		if(reviews.isEmpty()) System.out.println("조회된 리뷰 없음");
		close(conn);
		return reviews;
	}


	

}
