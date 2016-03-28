package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ScheduleData;

@Repository
public class ScheduleDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	/**
	 * 이벤트 리스트
	 * */
	@SuppressWarnings("rawtypes")
	public ArrayList  selectEvents(String id){
		return (ArrayList) sqlSession.selectList("sch.eventList",id);		
	}
	
	/**
	 * 이벤트 삭제
	 * */
	public void deleteEvent(int no){
		sqlSession.update("sch.deleteEven",no);
	}
	/**
	 * 이벤트 추가
	 * */
	public void insertEvent(ScheduleData data){
		sqlSession.insert("sch.insertEvent",data);		
	}
	
	
	
}
