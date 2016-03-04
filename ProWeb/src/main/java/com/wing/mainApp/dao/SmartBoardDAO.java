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
	 * 	�Ϸù�ȣ �޾ƿ��� �Լ�
	 */
	public int selectMax() {
		return sqlSession.selectOne("smart.selectMax");
	}
	/*
	 * �Խù� ��� �Լ�
	 */
	public void insertBoard(SmartBoardData data) {
		sqlSession.insert("smart.insertBoard", data);
	}
	
	/*
	 * ÷�� ���� ��� �Լ�
	 */
	public void insertFile(SmartBoardData data) {
		sqlSession.insert("smart.insertFile", data);
	}
	/*
	 * 	�� ������ �������ϱ� �Լ�
	 */
	public int selectTotal() {
		return sqlSession.selectOne("smart.selectTotal");
	}
	/*
	 * 	��� �˻� �Լ�
	 */
	public	ArrayList	selectList(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectList", map);
	}
	
	/*
	 * 	�󼼺��� ���ǽ���
	 */
	public SmartBoardData selectView(int no) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectView", no);
	}
	/*
	 * 	���� �˻� ���� ����
	 */
	public	ArrayList	selectFile(int no) {
		return (ArrayList) sqlSession.selectList("smart.selectFile", no);
	}
	/*
	 * 	������, ������ ���� ����
	 */
	public HashMap selectPreNext(int no) {
		return (HashMap) sqlSession.selectOne("smart.selectPreNext", no);
	}
	/*
	 * 	�̹̺� �۹�ȣ �˻� ����
	 */
	public String selectHit(String ID) {
		return (String) sqlSession.selectOne("smart.selectHit", ID);
	}
	/*
	 * 	�̹̺� �۹�ȣ ���� ����
	 */
	public void updateHit(HashMap map) {
		sqlSession.update("smart.updateHit", map);
	}
	/*
	 * 	�۹�ȣ ���� ����
	 */
	public	void insertHit(HashMap map) {
		sqlSession.insert("smart.insertHit", map);
	}
	/*
	 * 	���� ��ȸ�� ���� ����
	 */
	public void updateBoardHit(int NO) {
		sqlSession.update("smart.updateBoardHit", NO);
	}
	
	/*
	 * 	���� ���� �˻� ����
	 */
	public HashMap selectFileInfo(int NO) {
		return (HashMap) sqlSession.selectOne("smart.selectFileInfo", NO);
	}
	
	/*
	 * 	����/������ ���� ���� �˻� ����
	 */
	public SmartBoardData selectPwInfo(int NO) {
		return (SmartBoardData) sqlSession.selectOne("smart.selectPwInfo", NO);
	}
	
	/*
	 * ���� ���� ���� 
	 */
	public void fileDelete(int NO) {
		sqlSession.delete("smart.deleteFile", NO);
	}
	
	/*
	 *	�Խ��� ���� ���� 
	 */
	public void updateBoard(SmartBoardData data) {
		sqlSession.update("smart.updateBoard", data);
	}
	
	/*
	 * �˻� ����
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectSearch", map);
	}
}




