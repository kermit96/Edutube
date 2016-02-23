package com.wing.mainApp.dao;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 *  �� Ŭ������ ������ ���̽� �۾��� ���ֱ� ���� Ŭ�����̴�.
 *  ��� �� Ŭ���������� Ŀ�ؼ� Ǯ�� �մ� Ŀ�ؼǰ� myBatis �� �����ϴ�
 *  		������Ʈ ��Ʈ�� �̿��ؼ� �۾��� ���־�� �Ѵ�.
 *  
 *  �׷��� ���ؼ��� �ݵ��
 *  		SqlSessionSupport ��� Ŭ������ ��� �޾Ƽ� �����ؾ��Ѵ�.
 */
@Repository
public class SampleDAO {
		
	
	//		DI ������� SqlSession �� �˾ƿͼ� ����Ѵ�
	@Autowired
	SqlSessionTemplate sqlSession;
	public int getTotal(){
		
		int total = sqlSession.selectOne("sample.total");
				
	    return total;		
	}

	
}










