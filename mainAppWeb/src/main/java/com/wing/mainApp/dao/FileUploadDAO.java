package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Repository;

import com.wing.mainApp.data.FileUploadData;

@Repository
public class FileUploadDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	寃뚯떆臾� �벑濡� 泥섎━ �븿�닔
	 */
	public void insertBoard(FileUploadData data) {
		sqlSession.insert("fileupload.insertBoard", data);
	}
	/*
	 * 寃뚯떆臾� 紐⑸줉 泥섎━ �븿�닔
	 */
	public ArrayList selectBoard(){
		return (ArrayList)sqlSession.selectList("fileupload.selectBoard");
	}
	/* 
	 * �떎�슫濡쒕뱶 �쉶�닔瑜� 利앷� 泥섎━ �븿�닔
	 */
	public void updateDownHit(int NO){
		sqlSession.update("fileupload.updateDownHit",NO);
	}
	/*
	 * 	�떎�슫濡쒕뱶 �뙆�씪 �젙蹂� 寃��깋 �븿�닔
	 */
	public FileUploadData selectDownInfo(int NO) {
		return (FileUploadData) sqlSession.selectOne("fileupload.selectDownload", NO);
	}
}








