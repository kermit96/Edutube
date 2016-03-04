package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.cListData;

@Repository
public class cDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
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
	
	public void insertclass(cListData data) {
		sqlSession.insert("clist.classinsert", data);
	}
	public ArrayList getClassList() {
		ArrayList	result = (ArrayList)sqlSession.selectList("clist.getlist");
		return result;
	}

}
