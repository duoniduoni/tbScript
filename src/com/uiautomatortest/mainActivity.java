package com.uiautomatortest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;

public class mainActivity implements IActivity {

	//该edit是需要被单击的
	public UiObject searchEdit = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/home_searchedit"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return searchEdit.waitForExists(timeout);
	}

}
