package com.wing.mainApp.util;

public class PagingUtil {
	/*
	 * 페이징 처리
	 */
	public int nowPage;
	public int startPage;
	public int endPage;
	public int totalCount;			// 총 게시물 수
	public int onePageCount;	// 한개의 페이지에 나타날 게시글 수
	public int groupSize;			// 한개의 페이지 그룹에 나타날 페이지 수 
	public int pageNum;			// 파라메터에 따른 총 페이지수	
	
	/*
		PagingUtil = new PagingUtil
			(현재페이지, 게시물총갯수, 한페이지게시글, 한 페이지그룹의 갯수)
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
			
			// 총 페이지 수
			if(totalCount % onePageCount == 0){
				pageNum = totalCount / onePageCount;
			}
			else{
				pageNum = totalCount / onePageCount + 1;
			}
			
			// 잘못된 nowPage 값 1로
			if(!(nowPage > 0) || nowPage > pageNum){
				nowPage = 1;
			}
			
			// 시작 페이지 (6~10 일때 (6) 구하기)
			int tempP = nowPage / groupSize;
			
			if(nowPage%groupSize == 0){
				startPage = (tempP-1)*groupSize + 1;
			}
			else{
				startPage = tempP*groupSize + 1;
			}
			
			// 끝 페이지 (6~10 일때 (10) 구하기)
			endPage = startPage + groupSize - 1;			
			if(endPage > pageNum){
				endPage = pageNum;
			}
			
	} // 계산 함수 끝

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






