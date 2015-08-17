package com.uiautomatortest;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;

public class common {
	static public String SEARCHCONDITION = "SC";
	static public String COMMODITYDESCRIBE = "CD";
	static public String ISENTRYOTHERCOMMODITY = "IEOC";
	static public String SHOWCOMMODITYTIMEOUT = "SCT";
	static public String SHOWEVALUATIONTIMEOUT = "SET";
	
	static public String com_tag = "taobao_make_monkey";
	
	static public String ARGS = "args";
	
	static public int index_SC = 0;
	static public int index_CD = 1;
	static public int index_IEOC = 2;
	static public int index_SCT = 3;
	static public int index_SET = 4;
	
	/*
	 * 	参数由一个字符串包裹
	 * 	参数由5部分组成，每部分使用*分隔
	 * 	CD由多个关键字组成，每部发使用#分隔
	 * 	参数排列由5部分依次排列，不再使用key区分
	 */
	
	static public String[] splitParams(String str)
	{		
		return str.split("\\*");
	}
	
	static void Log(String log)
	{
		Log.d(common.com_tag, log);
	}
	
	static public void scrollWindow(int times)
	{
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		
		for (int tmp = 0; tmp < times; tmp++)
		{
			UiDevice.getInstance().drag(width / 2, height * 2 / 3, width / 2, height / 3, 30);
		}
	}
	
	static public void scrollWindowWithTimeout(long timeout)
	{
		long timeLength = 0;
		
		common.Log("begin to scroll window , total time is " + timeout);
		
		while(timeLength < timeout)
		{
			long begin, end;
			
			begin = common.getTimestamp();
			common.scrollWindow(1);
			end = common.getTimestamp();
			
			common.Log("scroll window once , take time  " + (end - begin) + "   sumer time " + timeLength);
			
			timeLength += end - begin;
		}
	}
	
	static public void sleep(long timeout)
	{
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static public long getTimestamp()
	{
		return System.currentTimeMillis();
	}
}
