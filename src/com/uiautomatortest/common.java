package com.uiautomatortest;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;

public class common {
	static public String com_tag = "taobao_make_monkey";
	
	static public String ARGS = "args";
	
	static public String strSplite = "#";
	static public String strKg = "$";
	
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
