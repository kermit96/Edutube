package com.wing.mainApp.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wing.mainApp.data.Member;


//  member 관리 Dao
@Repository
public class MemberDAO {
		
	@Autowired
	private SqlSessionTemplate sqlSession;


	// 로그인 할수 있는지 check 
	public Member  isLogin(String userid,String password) 
	{

		HashMap map = new HashMap();
		map.put("userid", userid);
		map.put("password", password);

		Member member = null;
		try {
		   member = sqlSession.selectOne("member.login",map);		
		} catch (Exception ex) {
			
		  ex.printStackTrace();	
		}
		return member;
	}

	// 같은 userid 가 있는지 check 
	public int  isSameuserid(String userid) 
	{
		HashMap map = new HashMap();
		map.put("userid", userid);
		return (int)sqlSession.selectOne("member.usercount",userid);			
	}


	// 같은 nick이름이  있는지 check 
	public int  isSamenick(String nickname) 
	{
		
		HashMap map = new HashMap();
		map.put("nickname", nickname);
		return (int)sqlSession.selectOne("member.nickcount",map);			
	}

	//username 과   email 가지고     id 를 찾는다   
	public String  findid(String username,String email) 
	{
		HashMap map = new HashMap();
		map.put("name", username);
		map.put("email", email);
						
	   try { 	 		
		   return (String)sqlSession.selectOne("member.findid",map);
	   } catch(Exception ex) {
		   ex.printStackTrace();
		   return "";
	   }
	}

	//username 과   email 가지고     id 를 찾는다   
	public int  changepassword(String userid,String username,String email,String password) 
	{
		HashMap map = new HashMap();
		map.put("name", username);
		map.put("email", email);
		map.put("userid", userid);
		map.put("newpassword", password);

		return sqlSession.update("member.changepassword",map);			
	}

	public Member getMember(String userid)
	{		
		HashMap map = new HashMap();
		map.put("userid", userid);
		return (Member)sqlSession.selectOne("member.getmember",map);		
	}

	public int   joinmember(Member member) 
	{

		return sqlSession.insert("member.joinmember",member);			
	}


	public int  updatemember(Member member) 
	{

		return sqlSession.update("member.updatemember",member);			
	}


	public int  changepassword(String userid,String oldpassword,String newpassword) 
	{
		HashMap map = new HashMap();
		map.put("oldpassword", oldpassword);
		map.put("newpassword", newpassword);
		map.put("userid", userid);	
		int count = sqlSession.update("member.changepassword",map);
		return count;
	}

	public int  changepassword(String userid,String newpassword) 
	{
		HashMap map = new HashMap();
		map.put("oldpassword", "");
		map.put("newpassword", newpassword);
		map.put("userid", userid);	
		int count = sqlSession.update("member.changepassword",map);
		return count;
	}

}
