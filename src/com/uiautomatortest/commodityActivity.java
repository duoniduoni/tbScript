package com.uiautomatortest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class commodityActivity implements IActivity {
	public UiObject Evaluation = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/detail_main_comment_count"));
	public UiObject commodityDescribe = new UiObject(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/detail_main_title_sellingpoint"));
	public UiObject btn_gotoTop = new UiObject(new UiSelector().className("android.widget.Button").resourceId("com.taobao.taobao:id/detail_main_bottom_gotop"));
	public UiObject entryShop = new UiObject(new UiSelector().text("삊  进店逛逛").className("android.widget.TextView").packageName("com.taobao.taobao"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		return  Evaluation.waitForExists(timeout) && commodityDescribe.waitForExists(timeout);
	}
	
	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
			try {
				if(btn_gotoTop.exists())
					btn_gotoTop.click();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		if(Evaluation.exists() || commodityDescribe.exists())
			UiDevice.getInstance().pressBack();
		
		return true;
	}
	
	public void showCommodityDetial(int times)
	{
		if(times < 10)
			times = 10;
		
		common.scrollWindow(times);
	}
	
	public void showCommodityDetialWithTimeout(int to)
	{
		common.scrollWindowWithTimeout(to);
	}

	public void entryShopActivity()
	{
		try {
			if(btn_gotoTop.exists())
				btn_gotoTop.click();
			
				do
				{
					if(entryShop.exists())
					{
						entryShop.clickAndWaitForNewWindow();
						break;
					}
					
					common.scrollWindow(1);
				}while(true);
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void entryEvaluationActivity() {
		try {
			if(btn_gotoTop.exists())
				btn_gotoTop.click();
			
			if(!Evaluation.exists())
				return ;
			
			Evaluation.clickAndWaitForNewWindow();
	
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
