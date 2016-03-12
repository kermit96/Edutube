package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.data.MediaData;



@Repository
public class ClassListDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// 총데이터 수 구하기 요청
	public int getTotal(String code) {

		/**
		 *  코드값이 없으면 전체 게시물을 구하고
		 * 	과목코드값이 있으면 해당 과목에 해당하는 게시물만 구한다.
		 * (03/11완)
		 * */
		if(code.equals("default")) {
			return sqlSession.selectOne("clist.gettotal");
		}
		else {
			return sqlSession.selectOne("clist.gettotalofsub", code);
		}
	}
	/**
	 *  전체 과목 구하기(03/11완) 
	 * */
	@SuppressWarnings("rawtypes")
	public ArrayList getSubList(){
		ArrayList list = (ArrayList) sqlSession.selectList("comm.getSubList");		
		return list;
	}	
	
	// 게시물등록 요청 (03/11 미완)
	public void insertclass(ClassListData data,int kind) {
			
		if(kind == 0){			
			sqlSession.insert("clist.classinsert", data);
		}
		else{	
			int tempNum = getMaxNO();			
			sqlSession.insert("clist.classinsert", data);
			data.no = tempNum;
			sqlSession.insert("clist.classinsertM", data);
		}
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
	}// 03/11
	
	// 상세보기 요청
	public ClassListData selectView(int NO) {
		return sqlSession.selectOne("clist.classview", NO);
	}
	/**
	 * 03/12 대충완료
	 * */
	
	// 동영상 리스트 요청
	@SuppressWarnings("rawtypes")
	public ArrayList selectMediaList(int NO){
		return (ArrayList)sqlSession.selectList("clist.mediaList",NO);
	}
	/**
	 * 03/12 대충완료
	 * */
	
	// 삭제 함수
	public void deleteclass(int NO) {
		sqlSession.update("clist.deleteclass", NO);	
	}
	/**
	 * 03/12 대충완료
	 * */
	// 수정폼 요청
	public ClassListData selectModi(int NO) {
		return sqlSession.selectOne("clist.classmodi", NO);
	}
	/**
	 * 03/12 대충완료
	 * */
	
	// 수정 함수
	public void updateclass(ClassListData data) {
		sqlSession.update("clist.updateclass", data);
	}
	// 현재 등록할 강의 맥스넘버 구하기
	public int getMaxNO(){
		return sqlSession.selectOne("clist.selectMAX");
	}
	
}
