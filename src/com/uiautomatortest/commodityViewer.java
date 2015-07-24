package com.uiautomatortest;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class commodityViewer {
	
	public UiObject Evaluation = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/detail_main_comment_count"));
	public UiObject commodityDescribe = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/detail_main_title_sellingpoint"));
	public UiObject btn_gotoTop = new UiObject(new UiSelector().className("android.widget.Button").resourceId("com.taobao.taobao:id/detail_main_bottom_gotop"));
	
	static public void scrollWindow(int times)
	{
		int width = UiDevice.getInstance().getDisplayWidth();
		int height = UiDevice.getInstance().getDisplayHeight();
		
		for (int tmp = 0; tmp < times; tmp++)
		{
			UiDevice.getInstance().drag(width / 2, height * 2 / 3, width / 2, height / 3, 30);
		}
	}
	
	public boolean isInCommodityView()
	{
		return Evaluation.exists() && commodityDescribe.exists();
	}
	
	public void showEvaluation() {
		try {
			if(btn_gotoTop.exists())
				btn_gotoTop.click();
			
			if(!Evaluation.exists())
				return ;
			
			Evaluation.clickAndWaitForNewWindow();
			
			scrollWindow(20);
			
			UiDevice.getInstance().pressBack();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showCommodityDetial()
	{
		scrollWindow(40);
	}
}
