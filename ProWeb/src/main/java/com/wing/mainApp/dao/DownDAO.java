package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.DownLoadData;

@Repository
public class DownDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// ������ ���� �Լ�
	public void insertDown(DownLoadData data){
		sqlSession.insert("down.wdown", data);
	}
	// ������ ������ �Լ�
	public ArrayList getDownList(){
		ArrayList list = (ArrayList)sqlSession.selectList("down.getlist");
		return list;
	}
	// ������ ���� ���ϴ� �Լ�
	public int getTotal(int kind){
		if(kind == 1){
			return sqlSession.selectOne("down.gettotal");
		}
		else{
			return 0;
		}
	}
}
