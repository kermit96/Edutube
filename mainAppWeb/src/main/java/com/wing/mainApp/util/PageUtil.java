package com.wing.mainApp.util;
/*
 * 	�씠 �겢�옒�뒪�뒗 �럹�씠吏� �굹�닎 湲곕뒫�씠 �븘�슂�븳 怨녹뿉 �럹�씠吏� �굹�닎 湲곕뒫�뿉 �븘�슂�븳 媛곸쥌 �뜲�씠�꽣瑜� 泥섎━�빐 以� �겢�옒�뒪
 */
public class PageUtil {
	public		int		nowPage;			//	�쁽�옱 �럹�씠吏�
	public		int		totalCount;		//	珥� �뜲�씠�꽣 媛쒖닔
	public		int		pageList;			//	�븳 �럹�씠吏��뿉 蹂댁뿬以� 寃뚯떆臾쇱쓽 媛��닔
	public		int		pageGroup;		//	�븳 �솕硫댁뿉�꽌 �꽑�깮�븷 �닔 �엳�뒗 �럹�씠吏� �닔
	public		int		startPage;			//	�쁽�옱 �솕硫댁뿉�꽌�떆�옉�븷 �럹�씠吏� �닔
	public		int		endPage;			//	�쁽�옱 �솕硫댁뿉�꽌 醫낅즺�맆 �럹�씠吏� �닔
	public		int		totalPage;			//	珥� �럹�씠吏��닔
	
	//	�깮�꽦�옄 �븿�닔瑜� �씠�슜�빐�꽌 �븘�닔�쟻�씤 �궡�슜�쓣 �엯�젰 諛쏅룄濡� �븳�떎.
	public PageUtil(int nowPage, int totalCount) {
		this(nowPage, totalCount, 10, 5);
	}
	//	寃뚯떆�뙋 醫낅쪟留덈떎 �븳 �솕硫댁뿉 蹂댁뿬以� 媛쒖떆臾쇱쓽 媛쒖닔媛� �떖�씪吏� �닔�룄 �엳�떎.
	//	�씠寃껋뿭�떆 �깮�꽦�옄 �븿�닔瑜� �씠�슜�빐�꽌 �궗�슜�옄媛� �엯�젰�븯�룄濡� �븯�옄.
	public PageUtil(int nowPage, int totalCount, int pageList, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageList = pageList;
		this.pageGroup = pageGroup;
		calcInfo();//2016.2.4 異붽�
	}
	//	�씠�젣 �븘�슂�븳 �굹癒몄� �젙蹂대�� 怨꾩궛�븯�옄.
	public void calcInfo() {
		//	珥� �럹�씠吏� �닔
		totalPage = ((totalCount % pageList) == 0) ? totalCount / pageList : (totalCount / pageList) + 1;
		//	�떆�옉 �럹�씠吏� 援ы븯湲�
		//		�쁽�옱 �럹�씠吏�媛� 臾댁뾿�씤媛��뿉 �뵲�씪 �떆�옉 �럹�씠吏�媛� �떖�씪吏꾨떎.
		//		�삁>
		//			�쁽�옱 �럹�씠吏�媛� 2, 3�럹�씠吏�		
		//			[1][2][3][4][5]						1洹몃９
		//			�쁽�옱 �럹�씠吏�媛� 7�럹�씠吏�
		//			[6][7][8][9][10]					2洹몃９
		//	利�	1~5�럹�씠吏�源뚯��뒗 媛숈� 洹몃９�씠 �굹���빞 �븯怨�	6~10�럹�씠吏�源뚯��룄 媛숈� 洹몃９�씠 �굹���빞 �븳�떎.
		//		�씠寃껋쓣 泥섎━�븯湲� �쐞�빐�꽌�뒗 �쁽�옱 �럹�씠吏�媛� �뼱�뼡 洹몃９�뿉 �냽�븯�뒗吏�瑜� �븣�븘�빞寃좊떎.
		int	tempGroup = ((nowPage % pageGroup) == 0) ? nowPage / pageGroup - 1: (nowPage / pageGroup);
		//		�삁>		1			0	
		//					2			0
		//					3			0
		//					4			0
		//					5			0
		//					6			1
		//		�떆�옉�럹�씠吏�		group		0				1
		//										1				6
		//										2				11
		startPage = tempGroup * pageGroup + 1;
		endPage = startPage + pageGroup - 1;			//	怨꾩궛
		//	留덉�留� �럹�씠吏�媛� 珥앺럹�씠吏��닔 蹂대떎 �옉�쑝硫� 留덉�留됲럹�씠吏�瑜� �궗�슜�븷 �닔 �뾾�떎.
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	public void calcInfo2() {
		//	珥� �럹�씠吏� �닔
		totalPage = ((totalCount % pageList) == 0) ? totalCount / pageList : (totalCount / pageList) + 1;
		//	媛��슫�뜲濡� �쁽�옱 �럹�씠吏�媛� 媛�湲� �쐞�빐�꽌 媛��슫�뜲 遺�遺꾩쓣 援ы븳�떎.
		int		gab = pageGroup / 2;
		startPage = nowPage - gab;
		//	�떆�옉 �럹�씠吏�媛� 1�럹�씠吏�蹂대떎 �옉�쑝硫� 媛뺤젣濡� 1�럹�씠吏�濡� �븳�떎.
		if(startPage < 1) {
			startPage = 1;
		}
		//	留덉�留� �럹�씠吏��뒗 �떆�옉 �럹�씠吏��뿉�꽌 5留뚰겮 �뼥�뼱吏꾨떎.
		endPage = startPage + pageGroup - 1;
		//	怨꾩궛�맂 留덉�留� �럹�씠吏�媛� 珥� �럹�씠吏�蹂대떎 �겕硫� 媛뺤젣濡� 留덉�留됱쓣 珥� �럹�씠吏�濡� �븳�떎.
		if(endPage > totalPage) {
			endPage = totalPage;
			//	留덉�留� �럹�씠吏�媛� �떎�떆 怨꾩궛�릺�뿀�쑝誘�濡� �떆�옉 �럹�씠吏�瑜� �떎�떆 怨꾩궛�븳�떎.
			startPage = endPage - pageGroup + 1;
			//	�뜲�씠�꽣媛� 紐뉕컻 �뾾�뼱�꽌 5媛쒕�� �떎 異쒕젰�븯吏� 紐삵븯硫�(利�, �떆�옉 �럹�씠吏�媛� �쓬�닔媛� �릺硫�....)
			//	�뼱姨붿닔 �뾾�씠 1�럹�씠吏�遺��꽣 蹂댁씠�옄.
			if(startPage < 1) {
				startPage = 1;
			}
		}
	}
	//		�닕�젣
	//				�쁽�옱 �럹�씠吏�蹂대떎 2留뚰겮 �쟻怨�	�쁽�옱 �럹�씠吏�蹂대떎 2留뚰겮 �겕寃� �굹�삤�룄濡� �븯�옄.
	//		�삁>		�쁽�옱�럹�씠吏�			7
	//											[5][6][7][8][9]
	//					�쁽�옱�럹�씠吏�媛� 		1, 2�럹�씠吏��씤 寃쎌슦�뒗 �븵履쎌뿉 2留뚰겮 �뾾�떎.
	//					�쁽�옱�럹�씠吏�媛�			留덉�留� �럹�씠吏��씤 寃쎌슦�뒗 �뮘履쎌뿉 2留뚰겮 �뾾�떎.
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageList() {
		return pageList;
	}
	public void setPageList(int pageList) {
		this.pageList = pageList;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
