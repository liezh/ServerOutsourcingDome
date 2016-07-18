package com.http.httpTool;

import java.io.File;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.os.Environment;

public class HttpUploadThread extends Thread {

	private String url = "";
	private String fileName = "";
	public HttpUploadThread(String url,String fileName) {
		this.url = url;
		this.fileName = fileName;
	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		httpUpload();
	}
	
	
	private String httpUpload(){
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		MultipartEntity muti = new MultipartEntity();
		File parent = Environment.getExternalStorageDirectory();
		File filAbs = new File(parent,fileName);
		
		FileBody fileBody = new FileBody(filAbs);
		muti.addPart("file",fileBody);
		post.setEntity(muti);
		try {
			HttpResponse httpResponse = httpClient.execute(post);
			System.out.println("-code----->>>"+httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				System.out.println(EntityUtils.toString(httpResponse.getEntity()));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	


}
