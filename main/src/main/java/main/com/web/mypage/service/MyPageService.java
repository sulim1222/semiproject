package main.com.web.mypage.service;

import static main.com.web.common.JDBCTemplate.close;
import static main.com.web.common.JDBCTemplate.commit;
import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import main.com.web.mypage.dao.MyPageDao;
import main.com.web.reservation.dto.Reserve;
import main.com.web.review.dto.Review;



public class MyPageService {
	
	private MyPageDao dao;
	
	public MyPageService() {
		dao = new MyPageDao();
	}

	
	public int updateMember(String memberId, String newName, String newPassword, String newPhone) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, memberId, newName, newPassword, newPhone);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	public List<Reserve> selectMyReservation(String id) {
		Connection conn = getConnection();
		List<Reserve> reservations = dao.selectMyReservation(conn, id);
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


	public boolean cancelReservation(String reserveNo) {
	    Connection conn = getConnection();
	    boolean isCancelled = false;
	    try {
	        conn.setAutoCommit(false); // 자동 커밋 해제

	        // 예약 정보 삭제
	        int result1 = dao.cancelReservationInfo(conn, reserveNo);
	        // 결제 정보 삭제
	        int result2 = dao.cancelPayment(conn, reserveNo);
	        // 예약 상세 정보 삭제
	        int result3 = dao.cancelReservationDetail(conn, reserveNo);

	        // 모든 작업이 성공했을 경우에만 커밋
	        if (result1 > 0 && result2 > 0 && result3 > 0) {
	            commit(conn);
	            isCancelled = true;
	        } else {
	            rollback(conn); // 하나라도 실패하면 롤백
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        rollback(conn); // 예외 발생 시 롤백
	    } finally {
	        close(conn);
	    }
	    return isCancelled;
	}



	

}
