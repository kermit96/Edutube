package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;

@Repository
public class ClassDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getTotal(int kind) {
		
		if(kind == 1) {
			return sqlSession.selectOne("classlist.gettotal");
		}
		else {
			return 0;
		}
	}
	/*
	 	목록보기 요청 함수
	 */
	public ArrayList	getClassList() {
		//	DAO의 반환값은 resultType과 동일한것이 아니고 유관하다.
		//	즉	질의의 실제 결과는 여러줄이 나올 가망성이 있으므로
		//	질의 결과는 여러줄을 기억하는 방식으로 처리해야 한다.
		ArrayList	result = (ArrayList)sqlSession.selectList("classlist.classlist");
		return result;
	}
	/*
	 * 	상세보기 요청 질의 실행 함수
	 */
	public ClassListData selectView(int NO) {
		return sqlSession.selectOne("classlist.classview", NO);
	}
	
	/*
	 * 	검색 결과 개수 구하기 질의 실행 함수
	 */
	public int getSearchCount(HashMap map) {
		return sqlSession.selectOne("classlist.searchcount", map);
	}
	
	/*
	 * 	검색 질의 실행 함수
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("classlist.classsearch", map);
	}
}
