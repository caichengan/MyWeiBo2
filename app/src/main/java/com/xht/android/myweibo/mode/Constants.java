package com.xht.android.myweibo.mode;

import android.os.Environment;

public class Constants {


	public static final String App_Key ="954912445";
	public static final String App_Secret = "1ee30049b2dbc7eaf93b19d2a3daae72";
	/*

	App Key：
		954912445
redirect_url=http://open.weibo.com/apps/3564760100/954912445/oauth
	App Secret：
		1ee30049b2dbc7eaf93b19d2a3daae72
     */
	/*
	 * 外部存储器的根目录
	 */
	public static final String ROOT_PATH_EXT = Environment.getExternalStorageDirectory() + "/ManagerHelp/";
	//办证步骤里，上传图片
	public static final String BZ_PIC_PATH = ROOT_PATH_EXT + "bzbz/img";
	public static final String BZ_CAM_PATH = ROOT_PATH_EXT + "bzbz/camera";
	/**
	 * 屏幕分辨率（可用作填充内容的区域）
	 */
	public static int DESIRED_WIDTH;
	/**
	 * 屏幕分辨率（可用作填充内容的区域）
	 */
	public static int DESIRED_HEIGHT;
	/**
	 * 设备density
	 */
	public static float DENSITY;
	/**
	 * 屏幕分辨率
	 */
	public static int SCREEN_WIDTH;
	/**
	 * 屏幕分辨率
	 */
	public static int SCREEN_HEIGHT;
	/**
	 * 状态栏高度
	 */
	public static int STATUSBAR_HEIGHT;
	/**
	 * actionBar高度
	 */
	public static int ACTIONBAR_HEIGHT;
	
	static {
		
	}

}
