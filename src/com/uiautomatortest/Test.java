package com.uiautomatortest;

import java.io.IOException;

import android.os.Bundle;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Test extends UiAutomatorTestCase {
	String Tag = "taobaotest";
	Bundle params = null;
	
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
	
	public String testDemo3()
	{
		Bundle result = new Bundle();
		result.putCharSequence("key1", "this is first key");
		this.getAutomationSupport().sendStatus(88888, result);
		return "hahahah";
	}
	
	public void testDemo()
	{
		/*
		searchResultsActivity2 sra = new searchResultsActivity2();
		if (!sra.isThisActivityRight())
			return;

		String[] matchs = {"49LF5900", "49吋“， ”全高清“，”智能网络“， ”IPS"};
		sra.findAndEntryCommodity(matchs, 30);
		*/
		
		searchConditionActivity sa = new searchConditionActivity();
		
		common.Log( "is searchConditionActivity " + sa.isThisActivityRight());
		
		common.Log( "exit searchConditionActivity " + sa.exitActivity());
	}
	
	public void testDemo2()
	{
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
		ma.exitActivity();
	}
}