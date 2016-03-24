package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 공지사항 가져오기
	public ArrayList getNotice(){
		return (ArrayList)sqlSession.selectList("main.getnotice");
	}
	// 강의 게시물 가져오기
	public ArrayList getClassd(){
		return (ArrayList)sqlSession.selectList("main.getclass");
	}
	// 묻고 답하기 게시글 가져오기
	public ArrayList getQaList(){
		return (ArrayList) sqlSession.selectList("main.getqa");
	}
	
	// 자료실 목록 가져오기
	public ArrayList getDown(){
		return (ArrayList)sqlSession.selectList("main.getdown");
	}
}
