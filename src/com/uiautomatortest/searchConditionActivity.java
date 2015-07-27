package com.uiautomatortest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;

public class searchConditionActivity implements IActivity {

	public UiObject searchEdit = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchedit"));
	public UiObject searchBtn = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchbtn"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return searchEdit.waitForExists(timeout) && searchBtn.waitForExists(timeout);
	}

}
