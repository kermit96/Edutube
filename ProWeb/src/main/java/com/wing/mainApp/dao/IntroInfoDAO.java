package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.IntroInfoData;


@Repository
public class IntroInfoDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 
	 */
	public int getTotal(int kind) {
		
		if(kind == 1) {
			
			return sqlSession.selectOne("intromanager.getTotal");
			
		}
		else {
			return 0;
		}
	}
	public void insertIntroInfo(IntroInfoData data) {
		sqlSession.insert("intromanager.insertIntroInfo", data);
	}
	public ArrayList selectIntroList(HashMap map) {
		return (ArrayList)sqlSession.selectList("intromanager.selectIntroList",map);
	}
	public ArrayList selectIntroSub(HashMap map) {
		ArrayList list = new ArrayList();
		if(map.get("lang")=="korlang"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectkorlang",map);
		}
		else if(map.get("lang")=="japlang"){
			list =  (ArrayList)sqlSession.selectList("intromanager.selectjaplang",map);
		}
		else if(map.get("lang")=="chnlang"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectchnlang",map);
		}
		else if(map.get("lang")=="progjava"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectjavalang",map);
		}
		else if(map.get("lang")=="progclang"){
			list =  (ArrayList)sqlSession.selectList("intromanager.selectclang",map);
		}
		else if(map.get("lang")=="progphp"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectphplang",map);
		}
		else if(map.get("lang")=="cookkor"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectcookkor",map);
		}
		else if(map.get("lang")=="cookjap"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectcookjap",map);
		}
		else if(map.get("lang")=="cookchn"){
			list =  (ArrayList)sqlSession.selectList("intromanager.selectcookchn",map);
		}
		else if(map.get("lang")=="cookwest"){
			list = (ArrayList)sqlSession.selectList("intromanager.selectcookwest",map);
		}
				
		return list;
	}
	/*
	 * 	
	 */
	public IntroInfoData selectView(int NO) {
		System.out.println("NO="+NO);
		HashMap map= new HashMap();
		map.put("NO",NO);
		return sqlSession.selectOne("intromanager.introview", map);
	}	
	public int selectTotal() {
		return sqlSession.selectOne("intromanager.getTotal");
	}
	/*
	 * 수정 혹은 삭제가 가능한 질의 요청 함수
	 */
	public int isUpdate(HashMap map) {
		return sqlSession.selectOne("intromanager.isupdate", map);
	}
	/*
	 * 	게시물 수정 함수
	 */
	public void updateIntroPhoto(IntroInfoData data,int flag) {
		if(flag==1){//그림 변경 없이 업데이트
			sqlSession.update("intromanager.updateintrophoto", data);
			
		}
		else { //새로운 그림을 포함하여 업데이트
			sqlSession.update("intromanager.updateintro", data);
		}	
	}
	/*
	 * 	게시물 삭제 함수
	 */
	public void deleteIntro(int NO) {
		sqlSession.update("intromanager.deleteintro", NO);
	}
	public HashMap getShowno(String ID){
		return sqlSession.selectOne("intromanager.showno",ID);
	}
	
	public void updateShowno(HashMap map,int kind){
		
		if(kind == 1){
			sqlSession.update("intromanager.updateshowno",map);
		}
		else{
			sqlSession.insert("intromanager.insertshowno",map);
		}
	}
	
	public void updateHit(int NO){
		sqlSession.update("intromanager.updatehit", NO);
	}
	/*
	 * 	
	 */
	public int getSearchCount(HashMap map) {
		return sqlSession.selectOne("intromanager.searchcount", map);
	}
	
	/*
	 * 	
	 */
	public ArrayList getSearch(HashMap map) {
		return (ArrayList) sqlSession.selectList("intromanager.searchintro", map);
	}
	
}
