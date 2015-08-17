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
	public UiObject listView = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/mainpages").className("com.taobao.tao.detail.page.main.ui.VerticalViewPager"));
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		boolean ret = listView.waitForExists(timeout);
		
		common.Log("listView exists : " + ret);
		
		return ret;
	}
	
	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		common.Log("try to exit commodityActivity");
		do
		{
			if(listView.exists())
			{
				UiDevice.getInstance().pressBack();
				common.Log("listView is exists, press back !!");
			}
			else
				break;
			
			common.sleep(1000);
		}while(true);
		
		return true;
	}
	
	public void showCommodityDetialWithTimeout(long to)
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
	
	public boolean entryEvaluationActivity() {
		try {
			if(btn_gotoTop.exists())
				btn_gotoTop.click();
			
			if(!Evaluation.exists())
				return false;
			
			return Evaluation.clickAndWaitForNewWindow();
	
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
