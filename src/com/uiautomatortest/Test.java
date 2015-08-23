package com.uiautomatortest;

import java.io.IOException;

import android.R.integer;
import android.os.Bundle;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Test extends UiAutomatorTestCase {
	String Tag = "taobaotest";
	
	String resultKey = "resultKey";
	
	public void testParam()
	{
		Bundle params = this.getParams();
		String args  = params.getString(common.ARGS);
		
		common.Log("testParam : " + args);
		
		Bundle br = new Bundle();
		br.putString(resultKey, "good");
		this.getAutomationSupport().sendStatus(1, br);
	}
	
	public void startTaobao() {
	    try {
	        Runtime.getRuntime().exec(
	                "am start -n " + "com.taobao.taobao" + "/com.taobao.tao.welcome.Welcome");
	        sleep(1000);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    boolean ret = false;
	    for (int i = 0; i < 20; i++) {
	        sleep(1000);
	        if (getUiDevice().getCurrentPackageName().contains("com.taobao.taobao")){
	           ret = true;
	           break;
	        }
	    }
	    
	    Bundle br = new Bundle();
	    
	    if(ret == true)
	    	br.putString(resultKey, "good");
	    else
	    	br.putString(resultKey, "bad");
	    
	    this.getAutomationSupport().sendStatus(ret == true?0:-1, br);
	}
	
	public void stopTaobao() {
	    try {
	        Runtime.getRuntime().exec(
	                "am force-stop com.taobao.taobao");
	        sleep(1000);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    Bundle br = new Bundle();
	    br.putString(resultKey, "good");
	    
	    this.getAutomationSupport().sendStatus(1, br);
	}
	
	public void entryMainActivity()
	{
		String strResult = "";
		
		do {
			mainActivity ma = new mainActivity();
			if(!ma.isThisActivityRight())
			{
				strResult = "entry mainActivity fail";
				break;
			}

			if(!ma.entrySearchActivity())
			{
				strResult = "try to entry searchActivity fail";
				break;
			}
			
			strResult = "good";
		} while (false);

		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void entrySearchConditionActivity()
	{
		String strResult = "";
		
		Bundle params = this.getParams();
		String Condition = params.getString(common.ARGS);
		
		String splite_sc[] = Condition.split("#");
		Condition = "";
		for(int i = 0; i < splite_sc.length; i++)
		{
			Condition += splite_sc[i];
			
			if(i + 1 < splite_sc.length)
				Condition += "  ";
		}
		
		common.Log("Condition = " + Condition);
		
		searchConditionActivity sa = new searchConditionActivity();
		do {
			if(Condition == null)
			{
				strResult = "Condition is null";
				break;
			}
			
			if (!sa.isThisActivityRight())
			{
				strResult = "entry searchConditionActivity fail";
				break;
			}
			
			if(!sa.search(Condition))
			{
				strResult = "entry searchConditionActivity fail";
				break;
			}
			
			strResult = "good";
		}while(false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void entrySearchResultActivity()
	{
		String strResult = "";

		Bundle params = this.getParams();
		String args  = params.getString(common.ARGS);
		String[] matchs = args.split("#");
		
		searchResultsActivity sra = new searchResultsActivity();
		do {
			if(matchs.length < 1)
			{
				strResult = "split matchs  wrong !!";
				common.Log(strResult);
				break;
			}
			
			for(String tmp:matchs)
			{
				common.Log( "match : " + tmp);
			}
			
			if (!sra.isThisActivityRight())
			{
				strResult = "entrySearchResultActivity fail";
				break;
			}

			if (!sra.findAndEntryCommodity(matchs, 30))
			{
				strResult = "findAndEntryCommodity fail";
				break;
			}
			
			strResult = "good";
		} while (false);

		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void entryCommodityActivity()
	{
		String strResult = "";
		
		Bundle params = this.getParams();
		String args  = params.getString(common.ARGS);
		
		long time;
		try {
			time = Long.parseLong(args) * 1000;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			common.Log("parse showCommodityDetialWithTimeout param fail, set it to 10 seconds !!");
			time = 10;
		}
		
		common.Log("entryCommodityActivity param is " + time);
		
		commodityActivity ca = new commodityActivity();
		do {
			if (!ca.isThisActivityRight())
			{
				strResult = "entryCommodityActivity fail ";
				break;
			}
			
			ca.showCommodityDetialWithTimeout(time);
			
			strResult = "good";
		}while(false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void entryEvaluationActivity()
	{
		String strResult = "";

		Bundle params = this.getParams();
		String args = params.getString(common.ARGS);

		long time;
		try {
			time = Long.parseLong(args) * 1000;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			common.Log("parse showEvaluationWithTimeout param fail, set it to 10 seconds !!");
			time = 10;
		}

		common.Log("entryEvaluationActivity param is " + time);
		
		commodityActivity ca = new commodityActivity();
		evaluationActivity ea = new evaluationActivity();
		do {
			if (!ca.isThisActivityRight())
			{
				strResult = "no longer at CommodityActivity ";
				break;
			}
			
			if(!ca.entryEvaluationActivity())
			{
				strResult = "entryEvaluationActivity fail ";
				//break;
			}
			
			ea.showEvaluationWithTimeout(time);

			if(!ea.exitActivity())
			{
				strResult = "entryEvaluationActivity fail ";
				break;
			}
			
			strResult = "good";
		} while (false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void entryCommodityActivityRandomly()
	{
		String strResult = "";

		commodityActivity ca = new commodityActivity();
		do {
			if (!ca.isThisActivityRight()) {
				strResult = "no longer at CommodityActivity";
				break;
			}

			ca.entryShopActivity();
			
			shopActivity sha = new shopActivity();
			if (!sha.isThisActivityRight()) 
			{
				strResult = "no long at shopActivity !";
				break;
			} 
			
			if (!sha.entryCommodityActivityRandomly()) 
			{
				strResult = "entryCommodityActivityRandomly fail !";
				break;
			} 
			
			strResult = "good";
		} while (false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void exitCommodityActivity()
	{
		String strResult = "";
		
		commodityActivity ca = new commodityActivity();
		do {
			if (!ca.isThisActivityRight())
			{
				strResult = "entryCommodityActivity fail ";
				break;
			}
			
			if(!ca.exitActivity())
			{
				strResult = "exitCommodityActivity fail ";
				break;
			}
			
			strResult = "good";
		}while(false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void exitShopActivity()
	{
		String strResult = "";

		do {
			shopActivity sha = new shopActivity();
			if (!sha.isThisActivityRight()) 
			{
				strResult = "no long at shopActivity !";
				break;
			} 
			
			if(!sha.exitActivity())
			{
				strResult = "exit shopActivity fail !";
				break;
			}
			
			strResult = "good";
		} while (false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void exitSearchResultActivity()
	{
		String strResult = "";
		
		searchResultsActivity sra = new searchResultsActivity();
		do {
			if (!sra.isThisActivityRight())
			{
				strResult = "no longer at SearchResultActivity ";
				break;
			}

			if (!sra.exitActivity())
			{
				strResult = "exit searchResultActivity fail";
				break;
			}
			
			strResult = "good";
		} while (false);

		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void exitSearchConditionActivity()
	{
		String strResult = "";
				
		searchConditionActivity sa = new searchConditionActivity();
		do {
			if (!sa.isThisActivityRight())
			{
				strResult = "no longer at  searchConditionActivity ";
				break;
			}
			
			if(!sa.exitActivity())
			{
				strResult = "exit searchConditionActivity fail";
				break;
			}
			
			strResult = "good";
		}while(false);
		
		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	public void exitMainActivity()
	{
		String strResult = "";
		
		do {
			mainActivity ma = new mainActivity();
			if(!ma.isThisActivityRight())
			{
				strResult = "no longer at mainActivity ";
				break;
			}

			if(!ma.exitActivity())
			{
				strResult = "exit searchActivity fail";
				break;
			}
			
			strResult = "good";
		} while (false);

		Bundle br = new Bundle();
		br.putString(resultKey, strResult);
		this.getAutomationSupport().sendStatus(0, br);
	}
	
	
	
	public void testDemo2()
	{
		/*
		params = this.getParams();
		String SC = "", CD = "";
		boolean IEOC;
		long SCT, SET;
		String[] matchs;
		
		String paramString = params.getString(common.ARGS);
		if(paramString == null)
		{
			common.Log( "get args fail !");
			return ;
		}
		
		common.Log("paramString is " + paramString);
		
		String[] args = common.splitParams(paramString);
		if(args.length != 5)
		{
			common.Log("args number is wrong !   " + args.length);
			return ;
		}
		
		String old_sc = args[common.index_SC];
		String splite_sc[] = old_sc.split("#");
		for(int i = 0; i < splite_sc.length; i++)
		{
			SC += splite_sc[i];
			
			if(i + 1 < splite_sc.length)
				SC += "  ";
		}
		
		CD = args[common.index_CD];
		IEOC = args[common.index_IEOC].equals("TRUE");
		
		try {
			SCT = Long.parseLong(args[common.index_SCT]) * 1000;
			SET = Long.parseLong(args[common.index_SET]) * 1000;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			common.Log( "parse timeout fail !!");
			SCT = SET = 20 * 1000;
		}
		
		if(SC.length() <= 0 || CD.length() <= 0)
		{
			common.Log( "param SC|CD is wrong !!");
			return ;
		}
		
		matchs = CD.split("#");
		if(matchs.length < 1)
		{
			common.Log( "split CD wrong !!");
			return ;
		}
		
		for(String tmp:matchs)
		{
			common.Log( "match : " + tmp);
		}
		
		common.Log( "arg is " + SC + "  |  " + CD + "  |  " + IEOC  + "  |  " + SCT + "  |  " + SET);
		
		UiDevice.getInstance().pressHome();
		
		common.Log( "try to start taobao");
		if(!startActivity("com.taobao.taobao"))
		{
			common.Log( "start taobao fail !");
			return ;
		}
		
		mainActivity ma = new mainActivity();
		do {
			if (!ma.isThisActivityRight())
				break;
			
			ma.entrySearchActivity();

			searchConditionActivity sa = new searchConditionActivity();
			do {
				if (!sa.isThisActivityRight())
					break;
				
				sa.search(SC);
				
				searchResultsActivity sra = new searchResultsActivity();
				do{
					if(!sra.isThisActivityRight())
						break;
					
					if(!sra.findAndEntryCommodity(matchs, 30))
						break;

					commodityActivity ca = new commodityActivity();
					do {
						if (!ca.isThisActivityRight())
							break;
						
						ca.showCommodityDetialWithTimeout(10 * 1000);
						
						ca.entryEvaluationActivity();
						evaluationActivity ea = new evaluationActivity();
						ea.showEvaluationWithTimeout(10 * 1000);
						
						ea.exitActivity();
						
						if (IEOC) {
							common.sleep(1000);
							ca.entryShopActivity();
							shopActivity sha = new shopActivity();
							if (sha.entryCommodityActivityRandomly()) {
								commodityActivity ca2 = new commodityActivity();
								ca2.showCommodityDetialWithTimeout(10 * 1000);
								
								ca2.exitActivity();								
							} else
								common.Log("entryCommodityActivityRandomly fail !");

							sha.exitActivity();
						}
					} while (false);
					ca.exitActivity();
				}while(false);
				sra.exitActivity();
			} while (false);
			sa.exitActivity();
		} while (false);
		ma.exitActivity();*/
	}
}