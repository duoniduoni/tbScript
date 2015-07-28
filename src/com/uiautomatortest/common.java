package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;

public class common {
	static public void scrollWindow(int times)
	{
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		
		for (int tmp = 0; tmp < times; tmp++)
		{
			UiDevice.getInstance().drag(width / 2, height * 2 / 3, width / 2, height / 3, 30);
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
}
