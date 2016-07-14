package com.example.httptest01;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import com.http.httpTool.ServiceJsonInfo;
import com.json.tools.JsonTools;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.testButton);
		
		handler = new MyHandler();
		
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Thread t = new NetworkThread();
				t.start();
			}

		});
	}
	
	String baseURL = "http://192.168.90.227:8080/jsonProject/servlet/JsonAction";
	class NetworkThread extends Thread {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			
			String url = baseURL + "?action_flag=listmap";
			System.out.println("NetworkThread:"
					+ Thread.currentThread().getName());
			ServiceJsonInfo serJsonInfo = new ServiceJsonInfo();
			//String jsonString = serJsonInfo.getJsonString(url);
			String jsonString = serJsonInfo.postJsonInfoToService(baseURL);
			System.out.println(jsonString);
			//根据业务解析数据
			List<Map<String, Object>> list = JsonTools.listKeyMaps("listmap", jsonString);
			System.out.println("-----test------>>>" + list.toString());
			//测试数据的获取的使用情况
			Iterator<Map<String, Object>> iter = list.iterator();
			while(iter.hasNext()){
				Map<String,Object> map = iter.next();
				//可以把值传输到控件当中使得app可以动态的获取后台信息
				System.out.println("id-->"+map.get("id") + "    name-->" + map.get("name") + "   address-->" + map.get("address"));
			}
		}

	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO 自动生成的方法存根

			System.out.println("MyHandler:" + Thread.currentThread().getName());
			
		}
	}
	
}
