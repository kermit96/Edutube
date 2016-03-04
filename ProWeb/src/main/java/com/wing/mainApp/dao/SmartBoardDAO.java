package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.SmartBoardData;



@Repository
public class SmartBoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	일련번호 받아오기 함수
	 */
	public int selectMax() {
		return sqlSession.selectOne("smart.selectMax");
	}
	/*
	 * 게시물 등록 함수
	 */
	public void insertBoard(SmartBoardData data) {
		sqlSession.insert("smart.insertBoard", data);
	}
	
	/*
	 * 첨부 파일 등록 함수
	 */
	public void insertFile(SmartBoardData data) {
		sqlSession.insert("smart.insertFile", data);
	}
	/*
	 * 	총 데이터 개수구하기 함수
	 */
	public int selectTotal() {
		return sqlSession.selectOne("smart.selectTotal");
	}
	/*
	 * 	목록 검색 함수
	 */
	public	ArrayList	selectList(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectList", map);
	}
	
	/*
	 * 	상세보기 질의실행
	 */
	public SmartBoardData selectView(int no) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectView", no);
	}
	/*
	 * 	파일 검색 질의 실행
	 */
	public	ArrayList	selectFile(int no) {
		return (ArrayList) sqlSession.selectList("smart.selectFile", no);
	}
	/*
	 * 	이전글, 다음글 질의 실행
	 */
	public HashMap selectPreNext(int no) {
		return (HashMap) sqlSession.selectOne("smart.selectPreNext", no);
	}
	/*
	 * 	이미본 글번호 검색 질의
	 */
	public String selectHit(String ID) {
		return (String) sqlSession.selectOne("smart.selectHit", ID);
	}
	/*
	 * 	이미본 글번호 수정 질의
	 */
	public void updateHit(HashMap map) {
		sqlSession.update("smart.updateHit", map);
	}
	/*
	 * 	글번호 생성 질의
	 */
	public	void insertHit(HashMap map) {
		sqlSession.insert("smart.insertHit", map);
	}
	/*
	 * 	실제 조회수 증가 질의
	 */
	public void updateBoardHit(int NO) {
		sqlSession.update("smart.updateBoardHit", NO);
	}
	
	/*
	 * 	파일 정보 검색 질의
	 */
	public HashMap selectFileInfo(int NO) {
		return (HashMap) sqlSession.selectOne("smart.selectFileInfo", NO);
	}
	
	/*
	 * 	수정/삭제를 위한 정보 검색 질의
	 */
	public SmartBoardData selectPwInfo(int NO) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectPwInfo", NO);
	}
	
	/*
	 * 파일 삭제 질의 
	 */
	public void fileDelete(int NO) {
		sqlSession.delete("smart.deleteFile", NO);
	}
	
	/*
	 *	게시판 수정 질의 
	 */
	public void updateBoard(SmartBoardData data) {
		sqlSession.update("smart.updateBoard", data);
	}
	
	/*
	 * 검색 질의
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectSearch", map);
	}
}




