package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;

public class evaluationActivity implements IActivity {

	UiObject actionBar = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/detail_action_bar_titletext"));
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return actionBar.waitForExists(timeout);
	}
	
	public void showEvaluation()
	{
		common.scrollWindow(6);
	}
	
	public void showEvaluationWithTimeout(long to)
	{
		common.scrollWindowWithTimeout(to);
	}

	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		if(actionBar.exists())
			UiDevice.getInstance().pressBack();
		
		return true;
	}

}
