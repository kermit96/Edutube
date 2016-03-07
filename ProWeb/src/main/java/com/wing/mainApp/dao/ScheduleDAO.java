package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@SuppressWarnings("rawtypes")
	public ArrayList  selectEvents(String id){
		return (ArrayList) sqlSession.selectList("sch.eventList",id);		
	}
}
