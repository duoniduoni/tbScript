package com.uiautomatortest;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;

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
	
	
	public void testDemo3()
	{
		shopActivity sha = new shopActivity();
		if(!sha.isThisActivityRight())
			return ;
		
		if (sha.entryCommodityActivityRandomly()) {
			commodityActivity ca2 = new commodityActivity();
			//ca2.showCommodityDetial(16);
			ca2.exitActivity();
		}
		else
			Log.d(Tag, "entryCommodityActivityRandomly fail !");
	}
	
	public void testDemo2()
	{
		params = this.getParams();
		String SC, CD;
		boolean IEOC;
		long SCT, SET;
		String[] matchs;
		
		String paramString = params.getString(common.ARGS);
		if(paramString == null)
		{
			Log.d(Tag, "get args fail !");
			return ;
		}
		
		Log.d(Tag, "paramString is " + paramString);
		
		String[] args = common.splitParams(paramString);
		if(args.length != 5)
		{
			Log.d(Tag, "args number is wrong !   " + args.length);
			return ;
		}
		
		SC = args[common.index_SC];
		CD = args[common.index_CD];
		IEOC = args[common.index_IEOC].equals("TRUE");
		
		try {
			SCT = Long.parseLong(args[common.index_SCT]) * 1000;
			SET = Long.parseLong(args[common.index_SET]) * 1000;
		} catch (NumberFormatException e) {
			// TODO: handle exception
			Log.d(Tag, "parse timeout fail !!");
			SCT = SET = 20 * 1000;
		}
		
		if(SC.length() <= 0 || CD.length() <= 0)
		{
			Log.d(Tag, "param SC|CD is wrong !!");
			return ;
		}
		
		matchs = CD.split("#");
		if(matchs.length < 1)
		{
			Log.d(Tag, "split CD wrong !!");
			return ;
		}
		
		for(String tmp:matchs)
		{
			Log.d(Tag, "match : " + tmp);
		}
		
		Log.d(Tag, "arg is " + SC + "  |  " + CD + "  |  " + IEOC  + "  |  " + SCT + "  |  " + SET);
		
		UiDevice.getInstance().pressHome();
		
		Log.d(Tag, "try to start taobao");
		if(!startActivity("com.taobao.taobao"))
		{
			Log.d(Tag, "start taobao fail !");
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
						
						ca.showCommodityDetialWithTimeout(30 * 1000);
						
						ca.entryEvaluationActivity();
						evaluationActivity ea = new evaluationActivity();
						ea.showEvaluationWithTimeout(20 * 1000);
						ea.exitActivity();
						
						if (IEOC) {
							ca.entryShopActivity();
							shopActivity sha = new shopActivity();
							if (sha.entryCommodityActivityRandomly()) {
								commodityActivity ca2 = new commodityActivity();
								ca2.showCommodityDetialWithTimeout(10 * 1000);
								ca2.exitActivity();
							} else
								Log.d(Tag,
										"entryCommodityActivityRandomly fail !");

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