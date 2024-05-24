package main.com.web.review.dao;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import main.com.web.member.dao.MemberDao;

public class ReviewDao {
	private Properties sql = new Properties(); //properties 값을 가져온다

	{
		String path =MemberDao.class.getResource("/sql/review_sql.properties").getPath();
		try (FileReader fr=new FileReader(path)){ //패스 경로를 찾아 파일을 받아온다
			sql.load(fr); //properteis에 load해준다. 
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
