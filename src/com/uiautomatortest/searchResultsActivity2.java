package com.uiautomatortest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.uiautomatortest.searchResultsActivity.searchItem;

public class searchResultsActivity2 extends searchResultsActivity {

	public boolean analysisResultItems()
	{
		if(!resultsView.exists())
			return false;
		
		try {
			int count = resultsView.getChildCount();
			contents.clear();
			
			for(int i = 0; i < count; i++)
			{
				//遍历搜索结果 分析出来每一个ITEM
				UiObject LinearLayout = resultsView.getChild(new UiSelector().index(i).className("android.widget.LinearLayout").resourceId("com.taobao.taobao:layout/tbsearch_item_list_improve"));
				if(!LinearLayout.exists())
					continue;
				
				UiObject RelativeLayout = LinearLayout.getChild(new UiSelector().index(0).className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/auction_layout"));
				if(!RelativeLayout.exists())
					continue;
				
				UiObject RelativeLayout2 = RelativeLayout.getChild(new UiSelector().index(1).className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/auc_maininfo_ext"));
				if(!RelativeLayout.exists())
					continue;
				
				UiObject item = RelativeLayout2.getChild(new UiSelector().index(0).className("android.widget.TextView").resourceId("com.taobao.taobao:id/title"));
				if(!item.exists())
					continue;
				
				UiObject LinearLayout2 = RelativeLayout2.getChild(new UiSelector().index(1).className("android.widget.LinearLayout").resourceId("com.taobao.taobao:id/sales_area"));
				if(!LinearLayout2.exists())
					continue;
				
				UiObject item2 = RelativeLayout2.getChild(new UiSelector().index(1).className("android.widget.TextView").resourceId("com.taobao.taobao:id/area"));
				if(!item.exists())
					continue;
				
				contents.add(new searchItem(item, item.getText(), RelativeLayout.getBounds(), item2.getText()));
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
