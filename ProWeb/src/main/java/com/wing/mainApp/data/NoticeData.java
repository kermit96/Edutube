package com.wing.mainApp.data;

import java.util.Date;

import com.wing.mainApp.util.StringUtil;

public class NoticeData {
	private int notice_no;
	private String mem_id;
	private String notice_title="";
	private String notice_body;
	private Date notice_date;
	private Date notice_cDate;
	private int notice_isDeleted;
	public int		nowPage;
	//�씪諛� DB �옉�뾽�뿉�꽌�뒗 �궇吏쒖� �떆媛꾩쓣 �뵲濡� 諛쏆븘�꽌 泥섎━�빐�빞 �븯�뒗�뜲
	//myBatis�뒗 �궡遺��쟻�쑝濡� �궇吏쒖� �떆媛꾩쓣 �떎 諛쏆븘�꽌 臾띠뼱�꽌 泥섎━�빐 以��떎.
	//洹몃윭誘�濡� myBatis瑜� �궗�슜�븷 �븣�뒗 援녹씠 �궇吏쒖� �떆媛꾩쓣 �뵲濡� 諛쏆쓣 �븘�슂媛� �뾾�떎.
	//���떊�뿉 myBatis�뒗 �궇吏쒖� �떆媛꾩쓣 ���꽕�엫�쑝濡� 異쒕젰�븯誘�濡� 臾몄젣媛� �깮湲� �닔 �엳�떎.
	//蹂��닔�쓽 �씠由꾩� �씪遺��윭 �뤌�쓽 name �냽�꽦怨� 媛숆쾶 �뻽�떎.
		//洹몃옒�빞 set �븿�닔媛� �옄�룞�쑝濡� 留뚮뱾�뼱吏꾨떎.
	public String	nick;
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_body() {
		return notice_body;
	}
	public void setNotice_body(String notice_body) {
		this.notice_body = notice_body;
	}
	public Date getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(Date notice_date) {
		this.notice_date = notice_date;
	}
	public Date getNotice_cDate() {
		return notice_cDate;
	}
	public void setNotice_cDate(Date notice_cDate) {
		this.notice_cDate = notice_cDate;
	}
	public int getNotice_isDeleted() {
		return notice_isDeleted;
	}
	public void setNotice_isDeleted(int notice_isDeleted) {
		this.notice_isDeleted = notice_isDeleted;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

//	�궇吏쒖� �떆媛꾩쓣 �썝�븯�뒗 �삎�깭濡� 蹂�寃쏀빐�꽌 異쒕젰�븷 �닔 �엳�뒗 getXXX�븿�닔瑜�
	//	�젣�옉�빐 二쇱뼱�빞 �븳�떎.
	//	�씠 �씠濡좎� 異쒕젰�쓣 �떎瑜� 諛⑹떇�쑝濡� �븯怨좎옄 �븯硫� getXXX�븿�닔瑜� 留뚮뱾�뼱�꽌
	//	�궗�슜�븳�떎...	�씪�뒗 �씠濡좎뿉 �뵲瑜멸쾬�씠�떎.
	public String getDatetime() {
		return StringUtil.getDateTimeStr(notice_date);
	}
	public String getDate() {
		return StringUtil.getDateStr(notice_date);
	}
	public String getBrbody() {
		return StringUtil.setBr(notice_body);
	}
}
