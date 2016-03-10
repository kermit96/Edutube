package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.data.DownLoadData;

@Repository
public class DownDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 총데이터 갯수구하는 함수
	public int getTotal() {
		return sqlSession.selectOne("down.getTotal");
	}

	// 게시글 불러오는 함수
	
	public	ArrayList	getList() {
		return (ArrayList) sqlSession.selectList("down.getList");
	}
	
	// 게시글 등록함수
	
	public void insertBoard(DownLoadData data) {
		sqlSession.insert("down.insertBoard", data);
	}

	// 파일 업로드 함수
	
	public void insertFile(DownLoadData data) {
		sqlSession.insert("down.insertFile", data);
	}
	
	// 일련번호 구하는 함수
	 
	public int selectMax() {
		return sqlSession.selectOne("down.selectMax");
	}
	
	// 글보기 함수
	
	public DownLoadData selectView(int no) {
		return (DownLoadData) sqlSession.selectOne("down.selectView", no);
	}
	
	// 파일 보기 함수
	
	public	ArrayList	selectFile(int no) {
		return (ArrayList) sqlSession.selectList("down.selectFile", no);
	}
	
	
	
	
	/*
	 * 	이전글, 다음글 질의 실행
	 */
	public HashMap selectPreNext(int no) {
		return (HashMap) sqlSession.selectOne("down.selectPreNext", no);
	}
	/*
	 * 	이미본 글번호 검색 질의
	 */
	public String selectHit(String ID) {
		return (String) sqlSession.selectOne("down.selectHit", ID);
	}
	/*
	 * 	이미본 글번호 수정 질의
	 */
	public void updateHit(HashMap map) {
		sqlSession.update("down.updateHit", map);
	}
	/*
	 * 	글번호 생성 질의
	 */
	public	void insertHit(HashMap map) {
		sqlSession.insert("down.insertHit", map);
	}
	/*
	 * 	실제 조회수 증가 질의
	 */
	public void updateBoardHit(int NO) {
		sqlSession.update("down.updateBoardHit", NO);
	}
	
	/*
	 * 	파일 정보 검색 질의
	 */
	public HashMap selectFileInfo(int NO) {
		return (HashMap) sqlSession.selectOne("down.selectFileInfo", NO);
	}
	
	/*
	 * 	수정/삭제를 위한 정보 검색 질의
	 */
	public DownLoadData selectPwInfo(int NO) {
		return (DownLoadData) sqlSession.selectOne("down.selectPwInfo", NO);
	}
	
	/*
	 * 파일 삭제 질의 
	 */
	public void fileDelete(int NO) {
		sqlSession.delete("down.deleteFile", NO);
	}
	
	/*
	 *	게시판 수정 질의 
	 */
	public void updateBoard(DownLoadData data) {
		sqlSession.update("down.updateBoard", data);
	}
	
	/*
	 * 검색 질의
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("down.selectSearch", map);
	}
}
