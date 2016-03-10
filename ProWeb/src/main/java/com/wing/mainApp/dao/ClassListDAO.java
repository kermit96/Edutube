package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;



@Repository
public class ClassListDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// 총데이터 수 구하기 요청
	public int getTotal(String code) {

		/**
		 *  코드값이 없으면 전체 게시물을 구하고
		 * 	과목코드값이 있으면 해당 과목에 해당하는 게시물만 구한다.
		 * 
		 * */
		if(code.equals("default")) {
			return sqlSession.selectOne("clist.gettotal");
		}
		else {
			return sqlSession.selectOne("clist.gettotalofsub", code);
		}
	}
	/**
	 *  전체 과목 구하기 
	 * */
	@SuppressWarnings("rawtypes")
	public ArrayList getSubList(){
		ArrayList list = (ArrayList) sqlSession.selectList("comm.getSubList");		
		return list;
	}
	
	
	
	// 게시물등록 요청
	public void insertclass(ClassListData data) {
		sqlSession.insert("clist.classinsert", data);
	}
	
	// 목록보기 요청
	@SuppressWarnings("rawtypes")
	public ArrayList getClassList(String code) {
		ArrayList	result = null;
		
		if(code.equals("default")){
			result = (ArrayList)sqlSession.selectList("clist.getlist");
		}
		else{
			result = (ArrayList)sqlSession.selectList("clist.getlistofsub",code);
		}		
				
		return result;
	}
	
	// 상세보기 요청
	public ClassListData selectView(int NO) {
		return sqlSession.selectOne("clist.classview", NO);
	}
	// 수정 삭제 요청
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("clist.isupdate", map);
	}
	// 삭제 함수
	public void deleteclass(int NO) {
		sqlSession.update("clist.deleteclass", NO);
	}
	// 수정 함수
	public void updateclass(ClassListData data) {
		sqlSession.update("clist.updateclass", data);
	}
}
