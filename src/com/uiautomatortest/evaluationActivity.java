package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class evaluationActivity implements IActivity {

	UiObject viewFlipper = new UiObject(new UiSelector().className("android.widget.ViewFlipper").resourceId("com.taobao.taobao:id/comment_flipper"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		
		return viewFlipper.waitForExists(timeout);
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
		common.Log("try to exit evaluationActivity");
		
		do
		{
			if(viewFlipper.exists())
			{
				UiDevice.getInstance().pressBack();
				common.Log("actionBar is exists, press back !!");
			}
			else
				break;
			
			common.sleep(1000);
		}while(true);
		
		return true;
	}

}
