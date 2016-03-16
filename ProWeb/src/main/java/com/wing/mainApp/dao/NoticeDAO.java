package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.NoticeData;

@Repository
public class NoticeDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getTotal(int kind) {
		
		if(kind == 1) {
			
			return sqlSession.selectOne("notice.gettotal");
			
		}
		else {
			return 0;
		}
	}
	
	//public ArrayList selectNoticeList(HashMap map) {
	public ArrayList selectNoticeList() {
		
		//ArrayList	result = (ArrayList)sqlSession.selectList("notice.getlist",map);
		//return result;
		return (ArrayList)sqlSession.selectList("notice.getlist");
	}
	//public ArrayList selectNoticeList(HashMap map) {
	public ArrayList selectNoticeList(HashMap map) {
			
			
		return (ArrayList)sqlSession.selectList("notice.getlist",map);
	}
	
	public NoticeData selectFinalList(int NO) {
		
		//System.out.println("NO="+NO);
		HashMap map= new HashMap();
		map.put("NO",NO);
		return sqlSession.selectOne("notice.getfinallist",map);
	}
	public void insertNotice(NoticeData data) {
		sqlSession.insert("notice.noticeinsert", data);
	}
	/*
	 * 수정 혹은 삭제가 가능한 질의 요청 함수
	 */
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("notice.isupdate", map);
	}
	/*
	 * 	게시물 수정 함수
	 */
	public void updateNotice(NoticeData data) {
		sqlSession.update("notice.updatenotice", data);
	}
	
	/*
	 * 	게시물 삭제 함수
	 */
	public void deleteNotice(int NO) {
		sqlSession.update("notice.deletenotice", NO);
	}
	public HashMap getShowno(String ID){
		return sqlSession.selectOne("notice.showno",ID);
	}
	
	public void updateShowno(HashMap map,int kind){
		
		if(kind == 1){
			sqlSession.update("notice.updateshowno",map);
		}
		else{
			sqlSession.insert("notice.insertshowno",map);
		}
	}
	
	public void updateHit(int NO){
		sqlSession.update("notice.updatehit", NO);
	}
	
	public int noticeMax(){
		//System.out.println("noticeMax");
		int temp=(int)sqlSession.selectOne("notice.noticemax");
		System.out.println("max="+temp);
		return temp;
	}
		
	/*
	 * 	�긽�꽭蹂닿린 �슂泥� 吏덉쓽 �떎�뻾 �븿�닔
	 */
	public NoticeData selectView(int NO) {
		System.out.println("NO="+NO);
		HashMap map= new HashMap();
		map.put("NO",NO);
		return sqlSession.selectOne("notice.noticeview", map);
	}
	/*
	 * 	
	 */
	public int getSearchCount(HashMap map) {
		return sqlSession.selectOne("notice.searchcount", map);
	}
	
	/*
	 * 	
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("notice.searchnotice", map);
	}
		
}
