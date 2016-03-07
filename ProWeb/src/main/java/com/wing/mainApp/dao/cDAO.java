package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.cListData;

@Repository
public class cDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// �ѵ����� �� ���ϱ� ��û
	public int getTotal(int kind) {
		//	DAO �Լ��� �Ű������� parameterType�ϰ�� ���� �����ϴ�.
		//	�ٸ� �� ���Ǹ� �����ϱ� ���ؼ� �ʿ��� ��� �����͸� �Ѱ��ִ� ��Ȱ�� �Ѵ�.
		//	���⼭�� ������ ������ ���� ����� 2�� ������ ���ε�.....
		//	�׷��� ��Ģ�� �Լ��� 2�� ������ �Ѵ�.
		//	������		DAO�Լ��� ���� ����� ���� 1:1 �� �ƴϴ�.
		//	�� �Լ��� �Ű������� ���� ��ɰ��� �����ϴ�.
		
		//	�Ű������� ��Ȱ
		//		�� �����Ͱ����� ���ϴµ�... ����� �������� �˷��ִ� ��ȣ
		if(kind == 1) {
			return sqlSession.selectOne("clist.gettotal");
		}
		else {
			return 0;
		}
	}
	// �Խù���� ��û
	public void insertclass(cListData data) {
		sqlSession.insert("clist.classinsert", data);
	}
	// ��Ϻ��� ��û
	public ArrayList getClassList() {
		ArrayList	result = (ArrayList)sqlSession.selectList("clist.getlist");
		return result;
	}
	// �󼼺��� ��û
	public cListData selectView(int NO) {
		return sqlSession.selectOne("clist.classview", NO);
	}
	// ���� ���� ��û
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("clist.isupdate", map);
	}
	// ���� �Լ�
	public void deleteclass(int NO) {
		sqlSession.update("clist.deleteclass", NO);
	}
	// ���� �Լ�
	public void updateclass(cListData data) {
		sqlSession.update("clist.updateclass", data);
	}
}
