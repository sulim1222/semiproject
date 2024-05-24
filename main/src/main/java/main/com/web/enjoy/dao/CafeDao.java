//package main.com.web.enjoy.dao;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Properties;
//
//
//
//public class CafeDao {
//	
//	private Properties sql=new Properties();
//	
//	{
//		String path=CafeDao.class.getResource("/sql/sql_cafe.properties").getPath();
//		try(FileReader fr=new FileReader(path)){
//			sql.load(fr);
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public List<Cafe> cafeAll(Connection conn, intcPage, int numPerpage{
//		PreparedStatment pstmt=null;
//		ResultSet rs=null;
//		List<Board> result=new ArrayList<>();
//		try {
//			pstmt=conn.prepareStatment(sql.getOrDefault("cafeAll"));
//			pstmt.setInt(1,(cPage-1)*numPerpage+1);
//			
//			
//			
//		}
//	}
//	
//
//}
