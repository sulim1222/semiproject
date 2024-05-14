package main.com.web.member.service;

import static main.com.web.common.JDBCTemplate.getConnection;
import static main.com.web.common.JDBCTemplate.close;
import java.sql.Connection;

import main.com.web.member.dao.MemberDao;
import main.com.web.member.dto.Member;
public class MemberService {

	public int memberEnroll(Member member) {
		Connection conn = getConnection();
		int result = new MemberDao().memberEnroll(conn,member);
		close(conn);
		return result;
	}

	public Member memberLogin(String memberId, String memberPwd) {
		Connection conn =getConnection();
		Member m = new MemberDao().memberLogin(conn,memberId,memberPwd);
		close(conn);
		return m;
	}
	
	
	/* request data : memberId
	 * 기능 : 이메일 중복체크  
	 * response data : 중복여부(boolean)
	 */ 
	public boolean duplicateId(Member m) {
		Connection conn = getConnection();
		boolean b = new MemberDao().duplicateId(conn,m);
		close(conn);
		return b;
	}

}
