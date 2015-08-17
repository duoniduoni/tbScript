package com.uiautomatortest;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class searchConditionActivity implements IActivity {

	public UiObject searchEdit = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchedit"));
	public UiObject searchBtn = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchbtn"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		boolean ret1 = searchEdit.waitForExists(timeout);
		boolean ret2 = searchBtn.waitForExists(timeout);
		
		common.Log("isThisActivityRight::searchEdit && searchBtn exists : " + ret1 + " | " + ret2);
		
		return  ret1 && ret2;
	}

	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		common.Log("try to exit searchConditionActivity ");
		
		do
		{
			if(searchBtn.exists())
			{
				UiDevice.getInstance().pressBack();
				common.Log("searchBtn is exists, press back !!");
			}
			else
				break;
			
			common.sleep(1000);
		}while(true);

		return true;
	}

	public boolean search(String arg)
	{
		try {
			searchEdit.setText(Utf7ImeHelper.e(arg));
			
			return searchBtn.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
