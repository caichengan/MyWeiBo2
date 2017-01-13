package com.xht.android.myweibo.net;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

import org.json.JSONObject;

import java.util.LinkedHashMap;

public class VolleyHelpApi  {
	private static final String TAG = "VolleyHelpApi";

	String httpMethod="GET";
	private static VolleyHelpApi sVolleyHelpApi;
	public static synchronized VolleyHelpApi getInstance() {
		if (sVolleyHelpApi == null) {
			sVolleyHelpApi = new VolleyHelpApi();
		}
		return sVolleyHelpApi;
	}
	private VolleyHelpApi() {}

	public void getDatasNews(AsyncWeiboRunner asyncWeiboRunner, WeiboParameters weiboParameters, final APIListener apiListener){
		String urlString = BaseURL.DATAS_ORDER_URL;

		asyncWeiboRunner.requestAsync(urlString, weiboParameters, httpMethod, new RequestListener() {
			@Override
			public void onComplete(String s) {

				apiListener.onResult(s);
			}

			@Override
			public void onWeiboException(WeiboException e) {

				apiListener.onError(e.toString());
			}
		});

	}





	
	public static String MakeURL(String p_url, LinkedHashMap<String, Object> params) {
		StringBuilder url = new StringBuilder(p_url);
		if(url.indexOf("?")<0)
			url.append('?');

		for(String name : params.keySet()){
			url.append('&');
			url.append(name);
			url.append('=');
			url.append(String.valueOf(params.get(name)));
		}
		String temStr = url.toString().replace("?&", "?");
		
		return temStr;
	}


	private boolean isResponseError(JSONObject jb){
		String errorCode = jb.optString("code","0");

		if(errorCode.equals("1")){
			return false;
		}
		return true;
	}


	public void getMainData(int uid, APIListener apiListener) {

	}
}
