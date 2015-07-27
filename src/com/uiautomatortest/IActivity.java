package com.uiautomatortest;

/*
 *		测试所有流程都是一基于一个个的activity 
 *		每个activity可以包装一个测试大流程
 *		每个activity首先要完成的步骤就是检测当前界面是否是期望的activity
 *		每个activity只提供数据——在activity上显示的分析出来的数据 而不应该包含操作
 */
public interface IActivity {
	public long timeout = 10 * 1000;
	
	public boolean isThisActivityRight();
}
