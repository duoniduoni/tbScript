package com.uiautomatortest;

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
		return all.waitForExists(timeout) && allList.waitForExists(timeout);
	}

	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		if(all.exists() && allList.exists())
			UiDevice.getInstance().pressBack();
		
		return true;
	}

	public boolean entryCommodityActivityRandomly()
	{
		if(!all.exists())
			return false;
		
		try {
			all.click();
			
			common.sleep(1000);
			
			allList.scrollForward();
			
			UiObject item = allList.getChild(new UiSelector().index((int)Math.random()%allList.getChildCount()));
			if(!item.exists())
			{
				return false;
			}
			item.clickAndWaitForNewWindow();
			
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
