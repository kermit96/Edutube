package com.iedu.util;

public class PagingUtil {
	/*
	 * ����¡ ó��
	 */
	public int nowPage;
	public int startPage;
	public int endPage;
	public int totalCount;			// 게시글 수
	public int onePageCount;	// 한 페이지에 나타날 게시글 수
	public int groupSize;			// 한 페이지 그룹에 나타날 페이지 갯수 (1~5) 
	public int pageNum;			// 전체 페이지 숫자
	
	/*
		PagingUtil = new PagingUtil
			
	*/
	public PagingUtil (int nowPage,int totalCount){
		this(nowPage,totalCount,10,5);		
	}
	
	public PagingUtil(int nowPage, int totalCount, int onePageCount, int groupSize) {
		
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.onePageCount = onePageCount;
		this.groupSize = groupSize;
		
	}

	public void pagingProc(){
			
			// 전체 페이지 구하기
			if(totalCount % onePageCount == 0){
				pageNum = totalCount / onePageCount;
			}
			else{
				pageNum = totalCount / onePageCount + 1;
			}
			
			// 현재 페이지 오류 보정
			if(!(nowPage > 0) || nowPage > pageNum){
				nowPage = 1;
			}
			
			// 시작 페이지 (6~10 이면 (6) 구하기)
			int tempP = nowPage / groupSize;
			
			if(nowPage%groupSize == 0){
				startPage = (tempP-1)*groupSize + 1;
			}
			else{
				startPage = tempP*groupSize + 1;
			}
			
			// �� ������ (6~10 �϶� (10) ���ϱ�)
			endPage = startPage + groupSize - 1;			
			if(endPage > pageNum){
				endPage = pageNum;
			}
			
	} // ��� �Լ� ��

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getOnePageCount() {
		return onePageCount;
	}

	public void setOnePageCount(int onePageCount) {
		this.onePageCount = onePageCount;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
}
