package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.IntroInfoData;

@Repository
public class IntroInfoDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 소개글 삽입 처리
	 */
	public void insertIntroInfo(IntroInfoData data) {
		sqlSession.insert("intromanager.insertIntroInfo", data);
	}
	public ArrayList selectIntroList(HashMap map) {
		return (ArrayList)sqlSession.selectList("intromanager.selectIntroList",map);
	}
	public int selectTotal() {
		return sqlSession.selectOne("intromanager.getTotal");
	}
		
}
