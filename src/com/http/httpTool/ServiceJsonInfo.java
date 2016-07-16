package com.http.httpTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class ServiceJsonInfo {

	HttpResponse httpResponse;
	HttpEntity httpEntity;

	public ServiceJsonInfo() {
		// TODO 自动生成的构造函数存根
	}

	
	public String getJsonString(String url) {
		// 生成一个请求对象
		HttpGet httpGet = new HttpGet(url);
		// 申请一个Http客户端对象
		HttpClient httpClient = new DefaultHttpClient();
		// 使用Http客户端发送请求对象
		InputStream inputStream = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));
			String result = "";
			String line = "";
			while ((line = reader.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			return result;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 这是把数据传输到指定的url的方法
	 * @param baseURL 传递的URL
	 * @param nameValuePairs  封装好的数据集
	 * @return
	 */
	public String postJsonInfoToService(String baseURL,List<NameValuePair> nameValuePairs){
		
		HttpClient httpClient = new DefaultHttpClient();
		

		try {
			//设置请求体
			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(nameValuePairs);
			HttpPost httpPost = new HttpPost(baseURL);
			httpPost.setEntity(requestHttpEntity);
			InputStream inputStream = null;
			try {
				httpResponse = httpClient.execute(httpPost);
				httpEntity = httpResponse.getEntity();
				inputStream = httpEntity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						inputStream));
				String result = "";
				String line = "";
				while ((line = reader.readLine()) != null) {
					result += line;
				}
				System.out.println("POST---->"+result);
				return result;
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}
	
}
