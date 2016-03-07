package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.GoodsCartData;

@Repository
public class GoodsCartDAO {
	public final static int	INSERT = 1;//인써트용 알리아스 변수
	public final static int	UPDATE = 2;//업데이트용 알리아스 변수
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	/*
	 * 	구매 내역 존재 여부 확인 함수
	 */
	public int selectCart(GoodsCartData data) {
		return (Integer) sqlSession.selectOne("cart.selectCartInfo", data);
	}
	/*
	 * 	카트 테이블 입력/수정 함수
	 *  처리 원리도 간단히 소개한다.
	 */
	public void cartInfoChange(GoodsCartData data, int kind) {
		//	1이면 insert
		//	2이면 update
		if(kind == INSERT) {
			sqlSession.insert("cart.insertCart", data);
		}
		else {
			sqlSession.update("cart.updateCart", data);
		}
	}
	/*
	 * 바구니 목록 검색 함수
	 */
	public ArrayList selectCartList(String id){
		return (ArrayList)sqlSession.selectList("cart.cartList",id);
	}
	/*
	 * 	장바구니 수량 수정 함수
	 */
	public void updateCartCount(GoodsCartData data) {
		sqlSession.update("cart.updateCartCount", data);
	}
	/*
	 * 장바구니 삭제 함수
	 */
	public void deleteCart(GoodsCartData data) {
		sqlSession.delete("cart.deleteCart", data);
	}
}




