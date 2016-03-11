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
	// 게시글 삭제 질의
	public void boardDelete(int no) {
		sqlSession.update("down.deleteDown",no);
		
	}
	// 파일 삭제 질의
	public void fileDelete(int no){
		sqlSession.update("down.deleteFile",no);
	}
	// 파일 존재 여부 확인 질의
	public int isFile(int NO){
		if(sqlSession.selectOne("down.isFile", NO) == null){
			return 0;
		}
		int total = sqlSession.selectOne("down.isFile", NO);
		return total;
	}
	// 다운로드를 위한 파일정보 검색
	public HashMap whatFile(int NO){
		return (HashMap) sqlSession.selectOne("down.whatFile",NO);
	}
	// 게시판 수정
	public void downUpDate(DownLoadData data) {
		System.out.println(data.title);
		System.out.println(data.body);
		System.out.println(data.no);
		sqlSession.update("down.downUpDate", data);
	}
	
	
	
	/*
	 * 	이전글, 다음글 질의 실행
	 */
	public HashMap selectPreNext(int no) {
		return (HashMap) sqlSession.selectOne("down.selectPreNext", no);
	}
	
	/*
	 * 검색 질의
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("down.selectSearch", map);
	}
}

