package com.uiautomatortest;

/*
 *		测试所有流程都是一基于一个个的activity 
 *		每个activity可以包装一个测试大流程
 *		每个activity首先要完成的步骤就是检测当前界面是否是期望的activity
 *		每个activity提供数据并且提供数据相关操作
 */
public interface IActivity {
	public long timeout = 2 * 60 * 1000;
	
	public boolean isThisActivityRight();
	public boolean exitActivity();
}
