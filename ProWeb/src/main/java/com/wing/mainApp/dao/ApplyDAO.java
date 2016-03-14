package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ApplyData;

@Repository
public class ApplyDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 권한 확인 질의
	public String whatNal(String id){
		return (String)sqlSession.selectOne("app.whatnal",id);
	}
	
	// 강사 신청했는지 확인 질의
	public String exist(String id){
		return (String)sqlSession.selectOne("app.exist",id);
	}
	
	// 강사 신청하기
	public void insertApply(ApplyData data){
		sqlSession.insert("app.insertapp",data);
	}
	
	// 강사 신청 리스트
	public ArrayList selectTeacher(){
		return (ArrayList)sqlSession.selectList("app.selectTeacher");
	}
	
	// 강사 아이디 불러오기
	public String selectApply(int no){
		return sqlSession.selectOne("app.selectapp",no);
	}
	
	// 강사 승인
	public void acceptApply(String id){
		sqlSession.update("app.acceptapp",id);
	}
	
	// 강사 승인후 데이터 지우기
	public void deleteApply(String id){
		sqlSession.update("app.deleteapp",id);
	}
	
	// 이메일 알아내기
	public String email(String id){
		return sqlSession.selectOne("app.email",id);
	}
	
	// 신청 되있는 데이터 지우기
	public void deleterec(int no){
		sqlSession.delete("app.deleterec",no);
	}
	// 다운로드 파일 정보 검색
	public HashMap isFile(int no){
		return (HashMap) sqlSession.selectOne("app.isFile",no);
	}
}
