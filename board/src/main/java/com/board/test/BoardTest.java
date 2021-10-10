package com.board.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat; // Replace Deprecated

import java.sql.Connection;
import java.util.List;

import javax.inject.Inject;
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

import com.board.domain.BoardVO;
import com.board.service.BoardService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //locations ={"classpath:/WEB-INF/spring/**/*.xml"}
public class BoardTest {
	//https://logging.apache.org/log4j/2.x/manual/api.html
	private Logger log = LogManager.getLogger("BoardTest");	
	
	@Autowired
	private DataSource ds;
	@Autowired
	SqlSessionTemplate st;
	@Inject
	BoardService service;
	 
    @Test
    public void dsTest() { 
        assertNotNull(ds); // DataSource가 null이 아니다! 
        
        try(Connection con = ds.getConnection()){
			log.info("DataSource 설정 성공");
			log.info(con);
		}catch(Exception e) {
			log.error("실패");
			e.printStackTrace();
		}   
    }

	// 게시물 목록
    @Test
	public void getList() throws Exception{
		List<BoardVO> list = null;
		list = service.list();
		assertNotNull(list);
	}
	
    private static final String TITLE = "CCC";
    private static final String CONTENT = "BBB";
    private static final String WRITER = "AAA";
    
    // 게시물 작성 POST
    @Test
	public void posttWrite() throws Exception {
    	BoardVO vo = new BoardVO();
    	vo.setTitle(TITLE);
    	vo.setContent(CONTENT);
    	vo.setWriter(WRITER);
    	
    	assertThat(vo.getTitle(), is(notNullValue()));
    	assertThat(vo.getContent(), is(notNullValue()));
    	assertThat(vo.getWriter(), is(equalTo(WRITER)));	
    }

}
/*
 * Using transactional may create false negative.
 * https://java.christmas/2019/24 
 * https://www.marcobehler.com/2014/06/25/should-my-tests-be-transactional
 * https://dev.to/henrykeys/don-t-use-transactional-in-tests-40eb
 * 
 */
//https://github.com/aeisele/tx-sample/blob/master/src/test/java/com/andreaseisele/sample/tx/repository/UserRepositoryIntegrationTests.java


//https://www.marcobehler.com/guides/java-logging
//Unit test
//https://developyo.tistory.com/80