package com.uiautomatortest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import jp.jun_nama.test.utf7ime.helper.Utf7ImeHelper;

import android.os.Bundle;
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
			searchEdit.setText(Utf7ImeHelper.e("刀"));
	
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
	
	
	public void testDemo() {
		
		Bundle params = this.getParams();
		Set<String> keys = params.keySet();
		Iterator it = keys.iterator();  
        while(it.hasNext()){  
            Log.d(Tag, " key : " + it.next());
        }  
		
		UiDevice.getInstance().pressHome();
		
		Log.d(Tag, "try to start taobao");
		if(!startActivity("com.taobao.taobao"))
		{
			Log.d(Tag, "start taobao fail !");
			return ;
		}
		
		Log.d(Tag, "try to start search activity !");
		entrySearchPage();
		
		searchResults sr = new searchResults("com.taobao.taobao:id/search_listview", "android.support.v7.widget.RecyclerView");
		
		boolean isFind = false;
		UiObject target = null;
		for (int t = 0; t < 10; t++) {
			sr.initialResults();
			for (int i = 0; i < sr.contents.size(); i++) {
				Log.d(Tag, "the " + i + "th contehnt is "
						+ sr.contents.get(i).title + " | "
						+ sr.contents.get(i).bound.toString());

				if (sr.contents.get(i).title
						.equals("精灵王刀剑 霍比特人魔戒指环王剑工艺品装饰挂板影视包邮未开刃")) {
					
					Log.d(Tag, "*******  Find the target Item !!!! ********");
					target = sr.contents.get(i).obj;
					isFind = true;
					break;
				}
			}

			if (isFind)
				break;

			sr.scrollResults();
		}

		if (target != null && target.exists()) {
			Log.d(Tag, "查看商品 上下拖动! ");
			try {
				target.clickAndWaitForNewWindow();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			commodityViewer cv = new commodityViewer();
			cv.showCommodityDetial();
			cv.showEvaluation();
			
			cv.gotoShop();
			shopViewer sv = new shopViewer();
			sv.selectCommodityRandomly();
			UiDevice.getInstance().pressBack();
			UiDevice.getInstance().pressBack();
		}
		
		//退出淘宝
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
		UiDevice.getInstance().pressBack();
	}
}
