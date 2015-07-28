package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class mainActivity implements IActivity {

	//该edit是需要被单击的
	public UiObject searchEdit = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/home_searchedit"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return searchEdit.waitForExists(timeout);
	}
	
	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		if(searchEdit.exists())
		{
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
		}
		
		return true;
	}
	
	public boolean entrySearchActivity()
	{
		try {
			return searchEdit.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return false;
	}
}
