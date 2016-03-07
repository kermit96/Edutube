package com.wing.mainApp.dao;

import java.util.ArrayList;
import java.util.HashMap;

import 	org.mybatis.spring.SqlSessionTemplate;
import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Repository;

import com.wing.mainApp.data.GoodsCateData;
import com.wing.mainApp.data.GoodsInfoData;

//	�옄�룞 二쇱엯(DI) �겢�옒�뒪濡� 留뚮뱾湲� �쐞�븳 �뼱�끂�뀒�씠�뀡
@Repository
public class GoodsManagerDAO {
	//	�궗�슜�븷 而⑤꽖�뀡 ���쓣 �옄�룞 二쇱엯 湲곕쾿�쑝濡� 遺덈윭�삩�떎.
	@Autowired
	SqlSessionTemplate sqlSession;
	/*
	 * 	��移댄뀒怨좊━ 寃��깋 �븿�닔
	 * 	�씠 �븿�닔 �븯�굹瑜� �씠�슜�빐�꽌 ��, 以�, �냼移댄뀒怨좊━瑜� 遺덈윭�삤�룄濡� �븷 �삁�젙�씠�떎.
	 * 	�빟�냽
	 * 		kind		=	1		�� 移댄뀒
	 * 					=	2		以�
	 * 					=	3		�냼	
	 */
	public ArrayList	getCate(String cate, int kind) {
		if(kind == 1) {
			return (ArrayList) sqlSession.selectList("goodsmanager.selectLCate");
		}
		else if(kind == 2) {
			return (ArrayList) sqlSession.selectList("goodsmanager.selectMCate", cate);
		}
		else {
			return (ArrayList) sqlSession.selectList("goodsmanager.selectSCate", cate);
		}
	}
	/*
	 * 	移댄뀒怨좊━ �벑濡� 吏덉쓽 �븿�닔
	 */
	public void insertCate(GoodsCateData data, int kind) {
		if(kind == 1) {
			sqlSession.insert("goodsmanager.insertLCate", data);
		}
		else if(kind == 2) {
			sqlSession.insert("goodsmanager.insertMCate", data);
		}
		else {
			sqlSession.insert("goodsmanager.insertSCate", data);
		}
	}
	
	/*
	 * 	�긽�뭹 �벑濡� 吏덉쓽 �떎�뻾 �븿�닔
	 */
	public void insertGoodsInfo(GoodsInfoData data) {
		sqlSession.insert("goodsmanager.insertGoodsInfo", data);
	}
	
	/*
	 * 	�긽�뭹 紐⑸줉 寃��깋 吏덉쓽 �떎�뻾 �븿�닔
	 */
	public ArrayList	selectGoodsList(HashMap map) {
		return (ArrayList) sqlSession.selectList("goodsmanager.selectGoodsList", map);
	}
	
	/*
	 * 	�긽�뭹 �젙蹂� 寃��깋 吏덉쓽 �떎�뻾 �븿�닔
	 */
	public GoodsInfoData selectGoodsInfo(int no) {
		return (GoodsInfoData) sqlSession.selectOne("goodsmanager.selectGoodsInfo", no);
	}
}
