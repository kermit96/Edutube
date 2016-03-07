package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.BoardData;

@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 데이터 갯수 구하기 함수
	 */
	public int getTotal(int kind) {
		if(kind == 1) {
			return sqlSession.selectOne("cboard.gettotal");
		}
		else if(kind ==2) {
			return sqlSession.selectOne("cboard.gettotals");
		}
		else {
			return 0;
		}
	}
	/*
	 * 관리자 식별 함수
	 */
	public String searchAdmin() {
		return sqlSession.selectOne("cboard.adminselect");
	}
	
	/*
	 * QA_no알아내기 함수
	 */
	public int searchNO() {
		return sqlSession.selectOne("cboard.nosearch");
	}
	
	/*
	 * FAQ등록 요청 함수
	 */
	public void insertFAQ(BoardData data) {
		sqlSession.insert("cboard.faqinsert",data);
	}
	
	/*
	 * FAQ목록 보기 함수
	 */
	public ArrayList selectFAQ() {
		ArrayList result = (ArrayList)sqlSession.selectList("cboard.faqselect");
		return result;
	}
	
	/*
	 * QA등록 요청 함수
	 */
	public void insertQA(BoardData data) {
		sqlSession.insert("cboard.qainsert", data);
	}
	
	/*
	 * QA목록 보기 함수
	 */
	public ArrayList selectQA() {
		ArrayList result = (ArrayList)sqlSession.selectList("cboard.qaselect");
		return result;
	}
	
	/*
	 * QA상세보기 함수
	 */
	public BoardData viewQA(int no) {
		return sqlSession.selectOne("cboard.qaview", no);
	}
	
	/*
	 * QA답글달기 함수
	 */
	public void repleQA(BoardData data) {
		sqlSession.update("cboard.qareple", data);
	}
	
	/*
	 * 검색 결과 갯수 구하기 질의실행 함수
	 */
	public int searchCount(HashMap map) {
		return sqlSession.selectOne("cboard.searchcount",map);
	}
	
	/*
	 * 검색 질의 실행 함수
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList)sqlSession.selectList("cboard.searchboard", map);
	}
}
