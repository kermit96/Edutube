package com.iedu.util;

public class PageInfo {
  public int nowPage; //
  public int  totalCount; // 
  public int pageList;  // 
  public int  pageGroup;  // 
  public int  startPage;  //  
  public int endPage;  // 
  public int totalPage;   // 
  
  
  public PageInfo(int nowPage, int totalCount, int PageList,int pageGroup) 
  {
	  
	   this.nowPage=  nowPage;
	   this.totalCount =totalCount;
	   this.pageList = PageList;
	   this.pageGroup = pageGroup;	    
  }
  
  public PageInfo(int nowPage, int totalCount) 
  {	  
	  this(nowPage,totalCount,10,5);
	  CalcInfo();
  }

  
  private void  CalcInfo()
  {
	  
	  totalPage = ((totalCount%pageList)==0) ?  totalCount/pageList : (totalCount/pageList)+1;    
	  int tempGroup = ((nowPage % pageGroup ) ==0) ? nowPage /pageGroup : (nowPage/pageGroup)-1; 

	startPage = tempGroup *pageGroup +1;
	endPage = startPage+ pageGroup-1;
	// 
	
	if ( endPage>totalPage) {
	  endPage = totalPage;	
    }
	
	    
  }
  
}

