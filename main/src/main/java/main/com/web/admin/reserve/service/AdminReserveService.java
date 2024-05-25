package main.com.web.admin.reserve.service;

import static main.com.web.admin.reserve.common.JDBCTemplate.close;
import static main.com.web.admin.reserve.common.JDBCTemplate.commit;
import static main.com.web.admin.reserve.common.JDBCTemplate.getConnection;
import static main.com.web.admin.reserve.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import main.com.web.admin.reserve.dao.AdminReserveDao;
import main.com.web.admin.reserve.dto.Member;


public class AdminReserveService {

	private AdminReserveDao dao=new AdminReserveDao();
	
	public List<Member> selectMemberAll(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> members=dao.selectMemberAll(conn,cPage,numPerpage);
		close(conn);
		return members;
	}
	public int selectMemberAllCount(){
		Connection conn=getConnection();
		int result=dao.selectMemberAllCount(conn);
		close(conn);
		return result;
	}
	
	
	public List<Member> searchMember(String type, String keyword,String location, int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> result=dao.searchMember(conn,type,keyword,location,cPage,numPerpage);
		close(conn);
		return result;
	}
	public int searchMemberCount(String type, String keyword) {
		Connection conn=getConnection();
		int count=dao.searchMemberCount(conn,type,keyword);
		close(conn);
		return count;
	}
	
	public List<Member> selectMemberByLocation(String location,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Member> members=dao.selectMemberByLocation(conn,location,cPage,numPerpage);
		close(conn);
		return members;
	}
	
	public int selectMemberCountByLocation(String location){
		Connection conn=getConnection();
		int result=dao.selectMemberCountByLocation(conn,location);
		close(conn);
		return result;
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertNewMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	
	public Member selectByReserveNo(String reserveNo) {
		Connection conn=getConnection();
		Member m=dao.selectByReserveNo(conn,reserveNo);
		close(conn);
		return m;
	}
	
	public int updateReserve(Member m) {
		Connection conn=getConnection();
		int result=dao.updateReserve(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
	
	public int deleteReserve(String reserveNo) {
		Connection conn = getConnection();
        int result = dao.deleteReserve(conn, reserveNo);
        if (result > 0) commit(conn);
        else rollback(conn);
        close(conn);
        return result;
	}
	
	
}
