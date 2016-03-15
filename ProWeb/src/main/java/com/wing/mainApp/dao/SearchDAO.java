package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SearchDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	/*
	 * 검색 결과 갯수 구하기 질의실행 함수
	 */
	public int searchCount(HashMap map) {
		return sqlSession.selectOne("search.searchcount",map);
	}
	
	/*
	 * 검색 질의 실행 함수
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList)sqlSession.selectList("search.mainsearch", map);
	}
}
