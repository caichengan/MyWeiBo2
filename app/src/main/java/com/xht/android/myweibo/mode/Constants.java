package com.xht.android.myweibo.mode;

import android.os.Environment;

public class Constants {


	public static final String APP_KEY ="954912445";
	public static final String App_Secret ="1ee30049b2dbc7eaf93b19d2a3daae72";//签名文件6cfe75ceacd84b0f185dafae505bf24c
	public static final String REDIRECT_URL = "http://www.sina.com";// 应用的回调页
	public static final String SCOPE = 							   // 应用申请的高级权限
			"email,direct_messages_read,direct_messages_write,"
					+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
					+ "follow_app_official_microblog," + "invitation_write";
	/*

public interface Constants {
    public static final String APP_KEY      = "2045436852";		   // 应用的APP_KEY
    public static final String REDIRECT_URL = "http://www.sina.com";// 应用的回调页
    public static final String SCOPE = 							   // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
}


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
