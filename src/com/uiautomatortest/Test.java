package com.uiautomatortest;

import java.io.IOException;

import android.util.Log;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Test extends UiAutomatorTestCase {
	String Tag = "taobaotest";
	
	private boolean startActivity(String pkgName) {
	    try {
	        Runtime.getRuntime().exec(
	                "am start -n " + pkgName + "/com.taobao.tao.welcome.Welcome");
	        sleep(1000);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    for (int i = 0; i < 5; i++) {
	        sleep(1000);
	        if (getUiDevice().getCurrentPackageName().contains(pkgName)){
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean entrySearchPage()
	{
		UiObject searchBtn = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/home_searchedit"));
		try {
			searchBtn.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UiObject searchEdit = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchedit"));
		try {
			UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_D);
			UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_A);
			UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_O);
	
			UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UiObject searchBtn2 = new UiObject(new UiSelector().resourceId("com.taobao.taobao:id/searchbtn"));
		try {
			searchBtn2.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	private UiObject getSearchResults()
	{
		UiObject searchResults = new UiObject(new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("com.taobao.taobao:id/search_listview"));
		
		return searchResults;
	}
	
	private UiObject getSearchItem(UiObject Results, int index)
	{
		//index 从0开始，实际检索从2+index开始
		try {
			if(index + 2 >= Results.getChildCount())
				return null;
			
			UiObject RelativeLayout = Results.getChild(new UiSelector().index(2 + index).className("android.widget.LinearLayout").resourceId("com.taobao.taobao:layout/tbsearch_item_list_improve"));
			UiObject RelativeLayout2 = RelativeLayout.getChild(new UiSelector().index(1).className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/auc_maininfo_ext"));
			UiObject item = RelativeLayout2.getChild(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/title"));
			
			return item;
			
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public void testDemo() {
		
		UiDevice.getInstance().pressHome();
		
		Log.d(Tag, "try to start taobao");
		if(!startActivity("com.taobao.taobao"))
		{
			Log.d(Tag, "start taobao fail !");
			return ;
		}
		
		Log.d(Tag, "try to start search activity !");
		entrySearchPage();

		int count = 0;		
		//try to drag		
		try {
			UiObject results = getSearchResults();
			count = results.getChildCount();
			count -= 2;
			
			for(int i = 0; i < count; i++)
			{
				UiObject item = getSearchItem(results, i);
				Log.d(Tag, "the " + i + "th result is : " + item.getText());
			}
			UiObject begin = getSearchItem(results,  0);
			UiObject end = getSearchItem(results, count - 1);
			UiDevice.getInstance().drag(end.getBounds().centerX(), end.getBounds().centerY(), begin.getBounds().centerX(), begin.getBounds().centerY(), 30);
			
			results = getSearchResults();
			count = results.getChildCount();
			count -= 2;
			
			for(int i = 0; i < count; i++)
			{
				UiObject item = getSearchItem(results, i);
				Log.d(Tag, "the " + i + "th result is : " + item.getText());
			}
			begin = getSearchItem(results,  0);
			end = getSearchItem(results, count - 1);
			UiDevice.getInstance().drag(begin.getBounds().centerX(), begin.getBounds().centerY(), end.getBounds().centerX(), end.getBounds().centerY(), 30);
			
			begin.clickAndWaitForNewWindow();
			
			Log.d(Tag, "查看商品 上下拖动! ");
			int width = UiDevice.getInstance().getDisplayWidth();
			int height = UiDevice.getInstance().getDisplayHeight();
			
			for(int tmp = 0; tmp < 6; tmp ++)
				UiDevice.getInstance().drag(width / 2, height * 2 / 3, width / 2, height / 3, 30);
			
			for(int tmp = 0; tmp < 6; tmp ++)
				UiDevice.getInstance().drag(width / 2, height / 3, width / 2, height * 2 / 3, 30);
			
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
