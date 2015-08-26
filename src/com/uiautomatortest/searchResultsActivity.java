package com.uiautomatortest;

import java.util.ArrayList;

import android.graphics.Point;
import android.graphics.Rect;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

public class searchResultsActivity implements IActivity {
	public String Tag = "searchResultsActivity";
	public UiObject resultsView = new UiObject(new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("com.taobao.taobao:id/search_listview"));
	public UiObject btn_style = new UiObject(new UiSelector().className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/btn_style"));
	
	class searchItem
	{
		public searchItem(UiObject arg1, String arg2, Rect arg3) {
			obj = arg1;
			title = arg2;
			bound = arg3;
			address = "unknow";
			postfee = price = 0;
		}
		
		public searchItem(UiObject arg1, String arg2, Rect arg3, String arg4, float arg5, float arg6) {
			obj = arg1;
			title = arg2;
			bound = arg3;
			address = arg4;
			price = arg5;
			postfee = arg6;
		}
		
		public float postfee;
		public float price;
		public String address;
		public String title;
		public Rect bound;
		public UiObject obj;
	}
	
	public ArrayList<searchItem> contents = new ArrayList<searchItem>();
	
	@Override
	public boolean isThisActivityRight() {
		// TODO Auto-generated method stub
		boolean ret = resultsView.waitForExists(timeout);
		common.Log("isThisActivityRight :: resultsView waitForExists : " + ret);
		
		return ret; 
	}
	
	@Override
	public boolean exitActivity() {
		// TODO Auto-generated method stub
		common.Log("try to exit searchResultsActivity");
		
		do
		{
			if(resultsView.exists())
			{
				UiDevice.getInstance().pressBack();
				common.Log("resultsView is exists, press back !!");
			}
			else
				break;
			
			common.sleep(1000);
		}while(true);
		
		return true;
	}
	
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
				UiObject RelativeLayout = resultsView.getChild(new UiSelector().index(i).className("android.widget.LinearLayout").resourceId("com.taobao.taobao:layout/tbsearch_item_list_improve"));
				if(!RelativeLayout.exists())
					continue;
				
				UiObject RelativeLayout2 = RelativeLayout.getChild(new UiSelector().index(1).className("android.widget.RelativeLayout").resourceId("com.taobao.taobao:id/auc_maininfo_ext"));
				if(!RelativeLayout2.exists())
					continue;
				
				UiObject item = RelativeLayout2.getChild(new UiSelector().className("android.widget.TextView").resourceId("com.taobao.taobao:id/title"));
				if(!item.exists())
					continue;
				
				UiObject LinearLayout = RelativeLayout2.getChild(new UiSelector().index(1).resourceId("com.taobao.taobao:id/sales_area"));
				if(!LinearLayout.exists())
					continue;
				
				UiObject address = LinearLayout.getChild(new UiSelector().resourceId("com.taobao.taobao:id/area").index(2));
				if(!address.exists())
					continue;
				
				UiObject postfee = LinearLayout.getChild(new UiSelector().resourceId("com.taobao.taobao:id/postfee").index(1));
				if(!postfee.exists())
					continue;
				
				UiObject LinearLayout2 = RelativeLayout2.getChild(new UiSelector().index(3).resourceId("com.taobao.taobao:id/price_area"));
				if(!LinearLayout2.exists())
					continue;
				
				UiObject price = LinearLayout2.getChild(new UiSelector().resourceId("com.taobao.taobao:id/price").index(1));
				if(!price.exists())
					continue;
				
				//将float处理出来
				float f_price = Float.parseFloat(price.getText());
				float f_postfee = Float.parseFloat(postfee.getText().split("￥")[1]);
				
				contents.add(new searchItem(item, item.getText(), RelativeLayout.getBounds(), address.getText(), f_price, f_postfee));
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
		
		/*
		//从搜索结果的最下方开始向上拖拽 这里不使用scroll是因为无法精确控制
		Point begin, end;
		searchItem tmp =  contents.get(contents.size() - 1);
		begin = new Point(tmp.bound.centerX(), tmp.bound.centerY());
		tmp = contents.get(0);
		end = new Point(tmp.bound.centerX(), tmp.bound.centerY());
		
		UiDevice.getInstance().drag(begin.x, begin.y, end.x, end.y, 100);
		*/
		common.scrollWindow(1);
		
		return true;
	}
	
	private boolean isItemMatch(String describe, String[] matchs)
	{
		common.Log("---------------------------------------");
		common.Log("describe : " + describe);
		
		for(String tmp:matchs)
		{
			common.Log("match : " + tmp);
			if(!describe.contains(tmp))
			{
				common.Log("\t false");
				return false;
			}
			else
				common.Log("\t true");
		}
		
		return true;
	}
	
	public boolean findAndEntryCommodity(String[] matchs, String address, float price, float postfee, int scrollTimes)
	{
		boolean isFind = false;
		UiObject target = null;
		
		if(!btn_style.waitForExists(timeout))
		{
			return false;
		}
		
		try {
			while(true)
			{
				String des = btn_style.getContentDescription();
				
				common.Log("search reslut show style is " + des);
				
				if(des.equals("当前为列表模式"))
					break;
				
				btn_style.click();
				common.sleep(500);
			}
		} catch (UiObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int t = 0; t < scrollTimes; t++) {
			analysisResultItems();
			for (int i = 0; i < contents.size(); i++) {
				common.Log("the " + i + "th contehnt is : \n\t"
						+ contents.get(i).title + "  \n\t "
						+ contents.get(i).bound.toString() + "  \n\t "
						+ contents.get(i).address + "  \n\t "
						+ "price = " + contents.get(i).price + "  \n\t "
						+ "postfee=" + contents.get(i).postfee);
				
				//首先匹配地址、价格、运费（如果有的话）
				if(!address.isEmpty())
				{
					if(contents.get(i).address.equals(address))
					{
						common.Log("address match success !");
					}
					else
					{
						common.Log("address match fail !");
						continue;
					}
				}
				
				if(price >= 0)
				{
					if(contents.get(i).price == price)
					{
						common.Log("price match success !");
					}
					else
					{
						common.Log("price match fail !");
						continue;
					}
				}
				
				if(postfee >= 0)
				{
					if(contents.get(i).postfee == postfee)
					{
						common.Log("postfee match success !");
					}
					else
					{
						common.Log("postfee match fail !");
						continue;
					}
				}
				
				//最后一步是描述匹配
				if (isItemMatch(contents.get(i).title, matchs)) 
				{
					common.Log("*******  Find the target Item !!!! ********");
					target = contents.get(i).obj;
					isFind = true;
					break;
				}
			}

			if (isFind)
				break;

			scrollResults();
		}
		
		if(target == null)
			return false;
		
		try {
			return target.clickAndWaitForNewWindow();
		} catch (UiObjectNotFoundException e) {
			// TODO Auto-generated catch block
			common.Log(e.toString());
		}
		
		return false;
	}
}
