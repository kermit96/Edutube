package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;

@Repository
public class ClassDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getTotal(int kind) {
		
		if(kind == 1) {
			return sqlSession.selectOne("classlist.gettotal");
		}
		else {
			return 0;
		}
	}
	/*
	 * 	�󼼺��� ��û ���� ���� �Լ�
	 */
	public ClassListData selectView(int NO) {
		return sqlSession.selectOne("classlist.classview", NO);
	}
	
	/*
	 * 	�˻� ��� ���� ���ϱ� ���� ���� �Լ�
	 */
	public int getSearchCount(HashMap map) {
		return sqlSession.selectOne("classlist.searchcount", map);
	}
	
	/*
	 * 	�˻� ���� ���� �Լ�
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("classlist.searchboard", map);
	}
}
