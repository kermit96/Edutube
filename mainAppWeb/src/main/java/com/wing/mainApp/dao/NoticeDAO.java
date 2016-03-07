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
	
	public ArrayList selectNoticeList(HashMap map) {
		
		//ArrayList	result = (ArrayList)sqlSession.selectList("notice.getlist",map);
		//return result;
		return (ArrayList)sqlSession.selectList("notice.getlist");
	}		
	public void insertNotice(NoticeData data) {
		sqlSession.insert("notice.noticeinsert", data);
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
		
	/*
	 * 	�긽�꽭蹂닿린 �슂泥� 吏덉쓽 �떎�뻾 �븿�닔
	 */
	public NoticeData selectView(int NO) {
		return sqlSession.selectOne("notice.noticeview", NO);
	}
	/*
	 * 	�뙎湲��벑濡� 吏덉쓽 �슂泥� �븿�닔
	 */
	public void insertReple(NoticeData data) {
		//sqlSession.insert("notice.insertreple", data);
	}
	/*
	 * 	�뙎湲� 寃��깋 吏덉쓽 �슂泥� �븿�닔
	 */
	public ArrayList	getReple(int NO) {
		return (ArrayList)sqlSession.selectList("notice.selectreple", NO);
	}
	/*
	 * �닔�젙 �샊�� �궘�젣媛� 媛��뒫�븳 吏덉쓽 �슂泥� �븿�닔
	 */
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("notice.isupdate", map);
	}

	/*
	 * 	寃뚯떆臾� �닔�젙 �븿�닔
	 */
	public void updateNotice(NoticeData data) {
		sqlSession.update("notice.updatenotice", data);
	}
	/*
	 * 	寃뚯떆臾� �궘�젣 �븿�닔
	 */
	public void deleteNotice(int NO) {
		sqlSession.update("rboard.deleteboard", NO);
	}
		
}
