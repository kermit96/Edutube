package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.data.MediaData;
import com.wing.mainApp.data.ReplyData;



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
	public void updateclass(ClassListData data,int kind,MediaData mdData) {
		if(kind==1){
			sqlSession.update("clist.updateclass", data);
		}
		else if(kind==2){//수정
			sqlSession.update("clist.updateclass", data);
			sqlSession.update("clist.modiVideo",mdData);
		}
		else if(kind==3){//삭제
			sqlSession.update("clist.updateclass", data);
			sqlSession.delete("clist.deleteVideo",mdData);
		}
		else if(kind==4){//추가
			sqlSession.update("clist.updateclass", data);
			sqlSession.insert("clist.insertVideo",mdData);
		}
		
	}
	// 현재 등록할 강의 맥스넘버 구하기
	public int getMaxNO(){
		return sqlSession.selectOne("clist.selectMAX");
	}
	
	/*봤던글 조회*/
	@SuppressWarnings("rawtypes")
	public HashMap getShowno(String ID){
		return sqlSession.selectOne("clist.showno",ID);		
	}
	
	/*본글 업데이트 혹은 삽입*/
	@SuppressWarnings("rawtypes")
	public void updateShowno(HashMap map,int kind){
		// kind 1 이면 update, 2이면 insertt 를 실행 시키도록 한다.
		if(kind == 1){
			sqlSession.update("clist.updateshowno",map);
		}
		else{
			sqlSession.insert("clist.insertshowno",map);
		}		
	}

	/*
	 *  조회수 실제로 증가 시키기
	 */
	public void updateHit(int NO){
		sqlSession.update("clist.updatehit",NO);
	}
	
	/**
	 * 추천 SQL 
	 * */
	/*추천수 조회*/
	@SuppressWarnings("rawtypes")
	public HashMap getShownoGood(String ID){
		return sqlSession.selectOne("clist.shownogood",ID);		
	}
	
	/*본글 업데이트 혹은 삽입*/
	@SuppressWarnings("rawtypes")
	public void updateShownoGood(HashMap map,int kind){
		// kind 1 이면 update, 2이면 insertt 를 실행 시키도록 한다.
		if(kind == 1){
			sqlSession.update("clist.updateshownogood",map);
		}
		else{
			sqlSession.insert("clist.insertshownogood",map);
		}		
	}
	
	/*
	 *  추천수 실제로 증가 시키기
	 */
	public void updateGood(int NO){
		sqlSession.update("clist.updategood",NO);
	}
	/*추천수 조회*/
	public int selectGood(int NO){
		return sqlSession.selectOne("clist.selectGood",NO);
	}
	
	/**
	 * 	댓글 기능
	 * */
	/*댓글 갯수*/
	public int getReTotal(int orino){
		return sqlSession.selectOne("clist.getRetotal",orino);
	}	
	/*댓글 등록*/
	public void insertReply(ReplyData data){
		sqlSession.insert("clist.insertReply", data);
	}
	/*댓글 리스트*/
	@SuppressWarnings("rawtypes")
	public ArrayList getReplyList(int orino) {
		return (ArrayList) sqlSession.selectList("clist.selectReply",orino);		
	}
	/*댓글 삭제*/
	public void deleteReply(int reno){
		sqlSession.update("clist.deleteReply",reno);
	}
	/*댓글내용(수정폼)*/
	public String getRebody(int reno){
		return sqlSession.selectOne("clist.getRebody", reno);
	}
	/*댓글수정*/
	public void updateReply(ReplyData data){
		sqlSession.update("clist.updateReply",data);
	}
	
}
