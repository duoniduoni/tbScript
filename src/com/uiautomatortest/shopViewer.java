package com.uiautomatortest;

import java.util.Random;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class shopViewer {

	UiObject all = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/shop_new_homepage_head_tab_allitem_tv").text("全部宝贝"));
	UiScrollable allList = new UiScrollable(new UiSelector().resourceId("com.taobao.taobao:id/searchgoodsMid").className("android.widget.AbsListView"));
	
	public boolean selectCommodityRandomly()
	{
		if(!all.exists())
			return false;
		
		try {
			all.click();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			allList.scrollForward();
			
			UiObject item = allList.getChild(new UiSelector().index((int)Math.random()%4));
			if(item.exists())
				item.click();
			
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			UiDevice.getInstance().pressBack();
			
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
