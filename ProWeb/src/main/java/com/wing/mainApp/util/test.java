package com.wing.mainApp.util;

public class test {

	public test() {
		// ���� ������, �ѰԽù�, ��������������, ������ �׷� �ȿ� ����
		PagingUtil pinfo = new PagingUtil(3, 15, 3, 3);
		pinfo.pagingProc();
		
		System.out.println("�� �������� : " + pinfo.pageNum);
		System.out.println("���� ������ : " + pinfo.nowPage);
		
		
		/* ����¡ �׽�Ʈ */
		if(pinfo.startPage != 1){
		System.out.print("[����]");
		}
		
		int temp = pinfo.startPage;
		for(int i =0 ;i<(pinfo.endPage-pinfo.startPage)+1;i++){			
			System.out.print("["+temp+"]");
			temp = temp+1;
		}
		
		if(pinfo.endPage != pinfo.pageNum){
		System.out.println("[����]");
		}
		/*-----------------------------------*/
	}

	public static void main(String[] args) {
		new test();
	}

}
