package com.xht.android.myweibo.net;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.xht.android.myweibo.App;
import com.xht.android.myweibo.utils.LogHelper;

import org.json.JSONObject;

import java.util.LinkedHashMap;

public class VolleyHelpApi extends BaseApi{
	private static final String TAG = "VolleyHelpApi";
	
	private static VolleyHelpApi sVolleyHelpApi;
	public static synchronized VolleyHelpApi getInstance() {
		if (sVolleyHelpApi == null) {
			sVolleyHelpApi = new VolleyHelpApi();
		}
		return sVolleyHelpApi;
	}
	private VolleyHelpApi() {}
	/**
	 * 检查版本更新 getCheckVersion
	 * @param apiListener 回调监听器
	 */
	public void getCheckVersion(final APIListener apiListener) {
		String urlString = MakeURL(CHECK_VERSION_URL, new LinkedHashMap<String, Object>() {{
		}});
		JsonObjectRequest req = new JsonObjectRequest(urlString, null, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				LogHelper.i(TAG,"-----------"+ response.toString());
					JSONObject jsonObject = response.optJSONObject("entity");
					apiListener.onResult(response);

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				int type = VolleyErrorHelper.getErrType(error);
				switch (type) {
					case 1:
						LogHelper.i(TAG, "超时");
						break;
					case 2:
						LogHelper.i(TAG, "服务器问题");
						break;
					case 3:
						LogHelper.i(TAG, "网络问题");
						break;
					default:
						LogHelper.i(TAG, "未知错误");
				}
				apiListener.onError("服务器繁忙，请稍后再试");
			}
		});
		App.getInstance().addToRequestQueue(req, TAG);
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

	/**
	 * 保存修改的公司名字
	 * @param jsonObject
	 * @param apiListener
     */
	public void postSaveCompanyName(JSONObject jsonObject, final APIListener apiListener) {

		JsonObjectRequest req = new JsonObjectRequest(SAVE_COPANY_URL, jsonObject, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				LogHelper.i(TAG, response.toString());

					apiListener.onResult(response);

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				int type = VolleyErrorHelper.getErrType(error);
				switch (type) {
					case 1:
						LogHelper.i(TAG, "超时");
						break;
					case 2:
						LogHelper.i(TAG, "服务器问题");
						break;
					case 3:
						LogHelper.i(TAG, "网络问题");
						break;
					default:
						LogHelper.i(TAG, "未知错误");
				}
				apiListener.onError("服务器繁忙，请稍后再试...");

			}
		});
		App.getInstance().addToRequestQueue(req, TAG);
	}


}
