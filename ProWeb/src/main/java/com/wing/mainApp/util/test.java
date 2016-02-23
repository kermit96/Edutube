package com.wing.mainApp.util;

public class test {

	public test() {
		// 현재 페이지, 총게시물, 한페이지보여줄, 페이지 그룹 안에 갯수
		PagingUtil pinfo = new PagingUtil(3, 15, 3, 3);
		pinfo.pagingProc();
		
		System.out.println("총 페이지수 : " + pinfo.pageNum);
		System.out.println("현재 페이지 : " + pinfo.nowPage);
		
		
		/* 페이징 테스트 */
		if(pinfo.startPage != 1){
		System.out.print("[이전]");
		}
		
		int temp = pinfo.startPage;
		for(int i =0 ;i<(pinfo.endPage-pinfo.startPage)+1;i++){			
			System.out.print("["+temp+"]");
			temp = temp+1;
		}
		
		if(pinfo.endPage != pinfo.pageNum){
		System.out.println("[다음]");
		}
		/*-----------------------------------*/
	}

	public static void main(String[] args) {
		new test();
	}

}
