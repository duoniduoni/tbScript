package com.uiautomatortest;

import java.util.ArrayList;

import android.graphics.Point;
import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class searchResults {
	private UiObject results = null;
	
	class searchItem
	{
		public searchItem(UiObject arg1, String arg2, Rect arg3) {
			obj = arg1;
			title = arg2;
			bound = arg3;
		}
		
		public String title;
		public Rect bound;
		public UiObject obj;
	}
	
	public ArrayList<searchItem> contents = new ArrayList<searchItem>();
	
	public searchResults(String resourceId, String className) {
		results = new UiObject(new UiSelector().className(className).resourceId(resourceId));
	}
	
	public boolean initialResults()
	{
		if(results == null)
			return false;
		
		if(!results.exists())
			return false;
		
		try {
			int count = results.getChildCount();
			contents.clear();
			
			for(int i = 0; i < count; i++)
			{
				//遍历搜索结果 分析出来每一个ITEM
				UiObject RelativeLayout = results.getChild(new UiSelector().index(i).className("android.widget.LinearLayout").resourceId("com.taobao.taobao:layout/tbsearch_item_list_improve"));
				if(!RelativeLayout.exists())
					continue;
				
				UiObject RelativeLayout2 = RelativeLayout.getChild(new UiSelector().index(1).className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/auc_maininfo_ext"));
				if(!RelativeLayout2.exists())
					continue;
				
				UiObject item = RelativeLayout2.getChild(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/title"));
				if(!item.exists())
					continue;
				
				contents.add(new searchItem(item, item.getText(), RelativeLayout.getBounds()));
			}
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public boolean scrollResults()
	{
		if(contents.size() <2 )
			return false;
		
		//从搜索结果的最下方开始向上拖拽 这里不使用scroll是因为无法精确控制
		Point begin, end;
		searchItem tmp =  contents.get(contents.size() - 1);
		begin = new Point(tmp.bound.centerX(), tmp.bound.centerY());
		tmp = contents.get(0);
		end = new Point(tmp.bound.centerX(), tmp.bound.centerY());
		
		UiDevice.getInstance().drag(begin.x, begin.y, end.x, end.y, 100);
		
		return true;
	}
}
