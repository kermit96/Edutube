package com.wing.mainApp.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.MessageData;

@Repository
public class MessageDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	// 메세지 보내기 요청
	public void sendMessage(MessageData data){
		sqlSession.insert("msg.sendmsg", data);
	}
	// 아이디 알아내기
	public String selectId(int no){
		return sqlSession.selectOne("msg.selectid",no);
	}
	// 받은쪽지 리스트
	public ArrayList selectMsgList(String id){
		return (ArrayList) sqlSession.selectList("msg.selectmsglist",id);
	}
	// 메세지 상세보기
	public MessageData msgView(int no){
		return (MessageData) sqlSession.selectOne("msg.msgView",no);
	}
	// 보낸쪽지 리스트
	public ArrayList selectSeMsgList(String id){
		return (ArrayList) sqlSession.selectList("msg.selectsemsglist",id);
	} 
	// 확인여부
	public void msgCom(int no){
		sqlSession.update("msg.msgcom",no);
	}
	// 메세지 삭제
	public void delM(int no){
		sqlSession.delete("msg.delm",no);
	}
}
