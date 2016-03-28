package com.wing.mainApp.util;

import java.io.File;

public class FileUtil {
	/*
	 * 	�̸��� �ߺ��� ��� ������ �̸��� �ٲ��ִ� �Լ�
	 */
	public static String rename(String path, String name) {
		/*
		 * 	����	�̸��� ������ ���� �̸� �ڿ� "_����"	�� �ٿ��� �̸��� �����ϵ��� �� �����̴�. 
		 */
		String	tempName = name;
		File		file = new File(path, tempName);
		int		count = 0;			//	�ڿ� ���� ���ڸ� ������ ����
		while(file.exists()) {
			count = count + 1;	
			//	�����̸��� ���� �̸��� Ȯ���ڸ� �и��Ѵ�.
			//	��?		test.txt,	test_1.txt, 	test_2.txt	�� �����Ѵ�.
			//			name			test.txt
			//			tempName		test_1.txt
			int	index = name.lastIndexOf(".");
			
			String	rName = name.substring(0, index);
			String	ext = name.substring(index + 1);
			rName = rName + "_" + count;		
			tempName = rName + "." + ext;		
			file = new File(path, tempName);
		}
		
		return tempName;
	}
}




