package com.xht.android.myweibo.mode;

public class UserInfo {
	
	private int mUid;
	private long mPhoneNum;
	private String mUserName;
	private String mContactUrl;
	private String mAcessToken;

	public String getmAcessToken() {
		return mAcessToken;
	}

	public void setmAcessToken(String mAcessToken) {
		this.mAcessToken = mAcessToken;
	}

	public String getmContactUrl() {
		return mContactUrl;
	}

	public void setmContactUrl(String mContactUrl) {
		this.mContactUrl = mContactUrl;
	}

	public UserInfo() {
		
	}
	
	public void setUid(int uid) {
		mUid = uid;
	}
	
	public int getUid() {
		return mUid;
	}
	
	public void setPhoneNum(long phoneNum) {
		mPhoneNum = phoneNum;
	}
	
	public long getPhoneNum() {
		return mPhoneNum;
	}
	
	public void setUserName(String userName) {
		mUserName = userName;
	}
	
	public String getUserName() {
		return mUserName;
	}

}
