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
	 * 	�씪�젴踰덊샇 諛쏆븘�삤湲� �븿�닔
	 */
	public int selectMax() {
		return sqlSession.selectOne("smart.selectMax");
	}
	/*
	 * 寃뚯떆臾� �벑濡� �븿�닔
	 */
	public void insertBoard(SmartBoardData data) {
		sqlSession.insert("smart.insertBoard", data);
	}
	
	/*
	 * 泥⑤� �뙆�씪 �벑濡� �븿�닔
	 */
	public void insertFile(SmartBoardData data) {
		sqlSession.insert("smart.insertFile", data);
	}
	/*
	 * 	珥� �뜲�씠�꽣 媛쒖닔援ы븯湲� �븿�닔
	 */
	public int selectTotal() {
		return sqlSession.selectOne("smart.selectTotal");
	}
	/*
	 * 	紐⑸줉 寃��깋 �븿�닔
	 */
	public	ArrayList	selectList(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectList", map);
	}
	
	/*
	 * 	�긽�꽭蹂닿린 吏덉쓽�떎�뻾
	 */
	public SmartBoardData selectView(int no) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectView", no);
	}
	/*
	 * 	�뙆�씪 寃��깋 吏덉쓽 �떎�뻾
	 */
	public	ArrayList	selectFile(int no) {
		return (ArrayList) sqlSession.selectList("smart.selectFile", no);
	}
	/*
	 * 	�씠�쟾湲�, �떎�쓬湲� 吏덉쓽 �떎�뻾
	 */
	public HashMap selectPreNext(int no) {
		return (HashMap) sqlSession.selectOne("smart.selectPreNext", no);
	}
	/*
	 * 	�씠誘몃낯 湲�踰덊샇 寃��깋 吏덉쓽
	 */
	public String selectHit(String ID) {
		return (String) sqlSession.selectOne("smart.selectHit", ID);
	}
	/*
	 * 	�씠誘몃낯 湲�踰덊샇 �닔�젙 吏덉쓽
	 */
	public void updateHit(HashMap map) {
		sqlSession.update("smart.updateHit", map);
	}
	/*
	 * 	湲�踰덊샇 �깮�꽦 吏덉쓽
	 */
	public	void insertHit(HashMap map) {
		sqlSession.insert("smart.insertHit", map);
	}
	/*
	 * 	�떎�젣 議고쉶�닔 利앷� 吏덉쓽
	 */
	public void updateBoardHit(int NO) {
		sqlSession.update("smart.updateBoardHit", NO);
	}
	
	/*
	 * 	�뙆�씪 �젙蹂� 寃��깋 吏덉쓽
	 */
	public HashMap selectFileInfo(int NO) {
		return (HashMap) sqlSession.selectOne("smart.selectFileInfo", NO);
	}
	
	/*
	 * 	�닔�젙/�궘�젣瑜� �쐞�븳 �젙蹂� 寃��깋 吏덉쓽
	 */
	public SmartBoardData selectPwInfo(int NO) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectPwInfo", NO);
	}
	
	/*
	 * �뙆�씪 �궘�젣 吏덉쓽 
	 */
	public void fileDelete(int NO) {
		sqlSession.delete("smart.deleteFile", NO);
	}
	
	/*
	 *	寃뚯떆�뙋 �닔�젙 吏덉쓽 
	 */
	public void updateBoard(SmartBoardData data) {
		sqlSession.update("smart.updateBoard", data);
	}
	
	/*
	 * 寃��깋 吏덉쓽
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectSearch", map);
	}
}




