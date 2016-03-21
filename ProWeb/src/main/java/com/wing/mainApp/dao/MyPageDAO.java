package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.BoardData;
import com.wing.mainApp.data.MyPageData;

@Repository
public class MyPageDAO {
	@Autowired
	SqlSessionTemplate	sqlSession;
	
	/*
	 * 데이터 갯수 구하기 함수
	 */
	public int getTotal(int kind) {
		if(kind == 1) {
			return sqlSession.selectOne("mypage.gettotal");
		}
		else {
			return 0;
		}
	}
	
	/*
	 * 신고 요청 함수
	 */
	public void insertREP(MyPageData data) {
		sqlSession.insert("mypage.repinsert",data);
	}
	
	/*
	 * 신고목록 보기 함수(관리자)
	 */
	public ArrayList selectREP() {
		ArrayList result = (ArrayList)sqlSession.selectList("mypage.repselect");
		return result;
	}
	
	/*
<<<<<<< HEAD
	 * 보여줘 안보여줘
	 */
	public void visionREP(MyPageData data) {
		sqlSession.update("mypage.repvision", data);
	}
	
	/*
=======
>>>>>>> branch 'master' of https://github.com/Nepro73/EduTube.git
	 * 회원정지
	 */
	public void stopREP(String str,int no) {
		sqlSession.update("mypage.userstop", str);
		sqlSession.update("mypage.reportdelete",no);
	}
	
	// id 알아내기
	public String selectRepid(int no){
		return sqlSession.selectOne("mypage.selectrepid",no);
	}
	
}
