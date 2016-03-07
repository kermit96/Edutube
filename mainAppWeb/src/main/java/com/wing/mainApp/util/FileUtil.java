package com.wing.mainApp.util;

import java.io.File;

public class FileUtil {
	/* 
	 * �씠由꾩씠 以묐났�맂 寃쎌슦 �뙆�씪�쓽 �씠由꾩쓣 諛붽퓭二쇰뒗 �븿�닔
	 */
	public static String rename(String path,String name){
		/*
		 * �굹�뒗 �씠由꾩씠 媛숈쑝硫� �썝�옒 �씠由� �뮘�뿉 _�닽�옄瑜� 遺숈뿬�꽌 �씠由꾩쓣 蹂�寃쏀븯�룄濡� �븷 �삁�젙�씠�떎.
		 * 
		 */
		String tempName = name;
		File file = new File(path,tempName);
		int count=0; //�뮘�뿉 遺숈씪 �닽�옄瑜� �젣�뼱�븷 蹂��닔
		while(file.exists()){
			count = count + 1;
			//�뙆�씪 �씠由꾩쓽 �떎�젣 �씠由꾧낵 �솗�옣�옄瑜� 遺꾨━�븳�떎.
			//�솢�깘�븯硫� test.txt瑜� �삱�졇�뒗�뜲. test_1.txt濡� 蹂�寃쏀븷 �삁�젙�씠湲� �븣臾몄뿉.
			int index = name.lastIndexOf(".");
			String rName = name.substring(0,index);
			String ext = name.substring(index + 1);
			rName = rName+"_"+ count;
			//�룄濡� �썝�옒 紐⑥뼇��濡� �빀移쒕떎.
			tempName = rName + "."+ ext;
			file = new File(path,tempName);
		}
		return tempName;
	}
}
