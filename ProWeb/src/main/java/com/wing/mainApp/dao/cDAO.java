package com.wing.mainApp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.cListData;

@Repository
public class cDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public void insertclass(cListData data) {
		sqlSession.insert("clist.classinsert", data);
	}

}
