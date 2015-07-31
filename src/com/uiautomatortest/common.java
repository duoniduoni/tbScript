package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;

public class common {
	static public String SEARCHCONDITION = "SC";
	static public String COMMODITYDESCRIBE = "CD";
	static public String ISENTRYOTHERCOMMODITY = "IEOC";
	static public String SHOWCOMMODITYTIMEOUT = "SCT";
	static public String SHOWEVALUATIONTIMEOUT = "SET";
	
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
		
		while(timeLength < timeout)
		{
			long begin, end;
			
			begin = common.getTimestamp();
			common.scrollWindow(1);
			end = common.getTimestamp();
			
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
