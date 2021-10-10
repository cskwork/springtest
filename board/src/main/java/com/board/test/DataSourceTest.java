package com.board.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations ={"classpath:/WEB-INF/spring/**/*.xml"})
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {
	
	 @Autowired
	private DataSource ds;
	
	 @Autowired
	 SqlSessionTemplate st;

	// 모든 테스트들은 @Test를 갖는다.
    @Test
    public void dsTest() {
        // DataSource가 null이 아니다! 
        assertNotNull(ds);
    }
    
    @Test
    public void templateTest() {
    	// SqlSessionTemplate가 null이 아니다!
        assertNotNull(st);
    }

   
	@Test
	public void testDS() throws Exception{
		try(Connection con = ds.getConnection()){
			System.out.println("DataSource 설정 성공");
			System.out.println(con);
		}catch(Exception e) {
			System.out.println("실패");
			e.printStackTrace();
		}
	}
	
}
