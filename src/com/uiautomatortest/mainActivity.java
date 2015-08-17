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
		
		boolean ret = searchEdit.waitForExists(timeout);
		common.Log("isThisActivityRight::searchEdit is exists " + ret);
		
		return ret;
	}
	
	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		common.Log("try to exit mainActivity");
		
		do
		{
			if(searchEdit.exists())
			{
				UiDevice.getInstance().pressBack();
				common.Log("searchEdit is exists, press back !!");
			}
			else
				break;
			
			common.sleep(500);
		}while(true);
		
		return true;
	}
	
	public boolean entrySearchActivity()
	{
		try {
			boolean ret = searchEdit.clickAndWaitForNewWindow();
			common.Log("entrySearchActivity::searchEdit clickAndWaitForNewWindow " + ret);
			
			return ret; 
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			common.Log(e.toString());
		};
		
		return false;
	}
}
