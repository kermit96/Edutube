package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.ClassListData;



@Repository
public class ClassListDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// 총데이터 수 구하기 요청
	public int getTotal(int kind) {
//		DAO 함수의 매개변수는 parameterType하고는 전혀 무관하다.
			//	다만 그 질의를 실행하기 위해서 필요한 모든 데이터를 넘겨주는 역활을 한다.
			//	여기서는 동일한 형태의 질의 명령이 2개 존재할 것인데.....
			//	그러면 원칙은 함수를 2개 만들어야 한다.
			//	하지만		DAO함수와 질의 명령은 절대 1:1 이 아니다.
			//	이 함수의 매개변수는 질의 명령과는 무관하다.
			
			//	매개변수의 역활
			//		총 데이터개수를 구하는데... 어떤것을 구할지를 알려주는 기호
		if(kind == 1) {
			return sqlSession.selectOne("clist.gettotal");
		}
		else {
			return 0;
		}
	}
	// 게시물등록 요청
	public void insertclass(ClassListData data) {
		sqlSession.insert("clist.classinsert", data);
	}
	// 목록보기 요청
	public ArrayList getClassList() {
		ArrayList	result = (ArrayList)sqlSession.selectList("clist.getlist");
		return result;
	}
	// 상세보기 요청
	public ClassListData selectView(int NO) {
		return sqlSession.selectOne("clist.classview", NO);
	}
	// 수정 삭제 요청
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("clist.isupdate", map);
	}
	// 삭제 함수
	public void deleteclass(int NO) {
		sqlSession.update("clist.deleteclass", NO);
	}
	// 수정 함수
	public void updateclass(ClassListData data) {
		sqlSession.update("clist.updateclass", data);
	}
}
