package com.uiautomatortest;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

public class shopActivity implements IActivity {
	UiObject all = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/shop_new_homepage_head_tab_allitem_tv").text("全部宝贝"));
	UiScrollable allList = new UiScrollable(new UiSelector().resourceId("com.taobao.taobao:id/searchgoodsMid").className("android.widget.AbsListView"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return all.waitForExists(timeout);
	}

	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		if(all.exists())
			UiDevice.getInstance().pressBack();
		
		return true;
	}

	public boolean entryCommodityActivityRandomly()
	{
		if(!all.exists())
			return false;
		
		try {
			all.click();
			
			common.sleep(3000);
			
			allList.waitForExists(timeout);
//			allList.scrollForward();
			
			int index = 1;//(int)(Math.random() * 100)%allList.getChildCount();
			UiObject item = allList.getChild(new UiSelector().index(index));
			if(!item.exists())
			{
				Log.d("shopActivity", "random index is " + index + " but fail !");
				return false;
			}
			
			Log.d("shopActivity", "random index is " + index + " success ! bround is : " + item.getBounds().toString());
			item.clickAndWaitForNewWindow();
			
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
