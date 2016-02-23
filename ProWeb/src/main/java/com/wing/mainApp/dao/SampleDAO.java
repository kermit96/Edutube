package com.wing.mainApp.dao;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 *  이 클래스는 데이터 베이스 작업을 해주기 위한 클래스이다.
 *  고로 이 클래스에서는 커넥션 풀에 잇는 커넥션과 myBatis 가 제공하는
 *  		스테이트 먼트를 이용해서 작업을 해주어야 한다.
 *  
 *  그러기 위해서는 반드시
 *  		SqlSessionSupport 라는 클래스를 상속 받아서 제작해야한다.
 */
@Repository
public class SampleDAO {
		
	
	//		DI 기법으로 SqlSession 을 알아와서 사용한다
	@Autowired
	SqlSessionTemplate sqlSession;
	public int getTotal(){
		
		int total = sqlSession.selectOne("sample.total");
				
	    return total;		
	}

	
}










