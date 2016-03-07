package com.wing.mainApp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 	臾몄옄�뿴�뿉 愿��젴�맂 湲곕뒫�쓣 �떒�닚�솕 �떆�궎湲� �쐞�븳 �쑀�떥由ы떚 �겢�옒�뒪
 */
public final class StringUtil {
	/*
	 * 	臾몄옄�뿴�씠 �꼸�씤吏�瑜� �솗�씤�빐 二쇰뒗 �븿�닔
	 */
	public static boolean isNull(String data) {
		if(data == null || data.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	/*
	 * 	�궇吏쒕�� �썝�븯�뒗 �삎�깭�쓽 臾몄옄濡� 蹂��솚�떆耳쒖＜�뒗 �븿�닔
	 */
	public static String getDateStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		return form.format(date);
	}
	public static String getDateTimeStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return form.format(date);
	}
	public static String getTimeStr(Date date) {
		SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss");
		return form.format(date);
	}
	public static String getDateStr(Date date, String pattern) {
		SimpleDateFormat form = new SimpleDateFormat(pattern);
		return form.format(date);
	}
	
	/*	
	 * BR �깭洹몃줈 蹂��솚�떆耳쒖＜�뒗 �븿�닔
	 */
	public static String setBr(String data) {
		return data.replaceAll("\r\n", "<br>");
	}
}




