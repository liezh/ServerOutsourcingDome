package com.http.httpTool;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class EncapsulatePostBady {

	public EncapsulatePostBady() {
		// TODO 自动生成的构造函数存根
	}
	
	/**
	 * 把请求体数据封装到一个 List<NameValuePair> 并返回
	 * @param user_id
	 * @param title
	 * @param synopsis
	 * @param reward
	 * @param endTime
	 * @return
	 */
	public static List<NameValuePair> setCreateTaskPostBady(String user_id,
			String title, String synopsis, String reward, String endTime) {
		NameValuePair nameValuePair1 = new BasicNameValuePair("user_id",
				user_id);
		NameValuePair nameValuePair2 = new BasicNameValuePair("title", title);
		NameValuePair nameValuePair3 = new BasicNameValuePair("synopsis",
				synopsis);
		NameValuePair nameValuePair4 = new BasicNameValuePair("reward", reward);
		NameValuePair nameValuePair5 = new BasicNameValuePair("endTime",
				endTime);

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(nameValuePair1);
		nameValuePairs.add(nameValuePair2);
		nameValuePairs.add(nameValuePair3);
		nameValuePairs.add(nameValuePair4);
		nameValuePairs.add(nameValuePair5);
		return nameValuePairs;
	}
	
	
	
	public static List<NameValuePair> setCompleteTaskPostBady(String task_id,String user_id){
		NameValuePair nameValuePair1 = new BasicNameValuePair("user_id",
				user_id);
		NameValuePair nameValuePair2 = new BasicNameValuePair("task_id", task_id);
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(nameValuePair1);
		nameValuePairs.add(nameValuePair2);
		
		return nameValuePairs;
	}
	
	
	/**
	 * 把程序生成的json语句加入到请求体当中
	 * @param key 键
	 * @param jsonString json语句
	 * @return
	 */
	public static List<NameValuePair> setJsonStringToPostBody(String key,String jsonString){
		
		NameValuePair nameValuePair = new BasicNameValuePair(key, jsonString);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(nameValuePair);
		
		return nameValuePairs;
	}

}
