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
	
	// ������ ���� ���ϱ�
	public int getTotal() {
		return sqlSession.selectOne("down.getTotal");
	}

	// �Խù� ��� �����ֱ�
	
	public	ArrayList	getDownList() {
		return (ArrayList) sqlSession.selectList("down.getList");
	}
	
	// �Խù� ���
	
	public void insertBoard(DownLoadData data) {
		sqlSession.insert("down.insertBoard", data);
	}

	// ÷������ ���
	
	public void insertFile(DownLoadData data) {
		sqlSession.insert("down.insertFile", data);
	}
	
	
	
	
	
	
	/*
	 * 	�Ϸù�ȣ �޾ƿ��� �Լ�
	 */
	public int selectMax() {
		return sqlSession.selectOne("smart.selectMax");
	}
	
	
	
	
	
	/*
	 * 	�󼼺��� ���ǽ���
	 */
	public DownLoadData selectView(int no) {
		return (DownLoadData) sqlSession.selectOne("smart.selectView", no);
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
	public DownLoadData selectPwInfo(int NO) {
		return (DownLoadData) sqlSession.selectOne("smart.selectPwInfo", NO);
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
	public void updateBoard(DownLoadData data) {
		sqlSession.update("smart.updateBoard", data);
	}
	
	/*
	 * �˻� ����
	 */
	public ArrayList	selectSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("smart.selectSearch", map);
	}
}
