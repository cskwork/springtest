package com.board.controller;


import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	//https://logging.apache.org/log4j/2.x/manual/api.html
	private Logger log = LogManager.getLogger("DataSourceTest");
	
	 @Autowired
	private DataSource ds;
	
	 @Autowired
	 SqlSessionTemplate st;
	 
	// 모든 테스트들은 @Test를 갖는다.
    @Test
    public void dsTest() {
        // DataSource가 null이 아니다! 
        assertNotNull(ds);
        
        try(Connection con = ds.getConnection()){
			log.info("DataSource 설정 성공");
			log.info(con);
		}catch(Exception e) {
			log.error("실패");
			e.printStackTrace();
		}
        
    }
    
    @Test
    public void templateTest() {
    	// SqlSessionTemplate가 null이 아니다!
        assertNotNull(st);
    }

}
//https://www.marcobehler.com/guides/java-logging