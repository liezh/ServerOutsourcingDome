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
		// TODO �Զ����ɵĹ��캯�����
	}

	
	public String getJsonString(String url) {
		// ����һ���������
		HttpGet httpGet = new HttpGet(url);
		// ����һ��Http�ͻ��˶���
		HttpClient httpClient = new DefaultHttpClient();
		// ʹ��Http�ͻ��˷����������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * ���ǰ����ݴ��䵽ָ����url�ķ���
	 * @param baseURL ���ݵ�URL
	 * @param nameValuePairs  ��װ�õ����ݼ�
	 * @return
	 */
	public String postJsonInfoToService(String baseURL,List<NameValuePair> nameValuePairs){
		
		HttpClient httpClient = new DefaultHttpClient();
		

		try {
			//����������
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
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "";
	}
	
}
