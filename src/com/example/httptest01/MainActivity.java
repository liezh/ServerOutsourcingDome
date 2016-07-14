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
			// TODO �Զ����ɵķ������
			
			String url = baseURL + "?action_flag=listmap";
			System.out.println("NetworkThread:"
					+ Thread.currentThread().getName());
			ServiceJsonInfo serJsonInfo = new ServiceJsonInfo();
			//String jsonString = serJsonInfo.getJsonString(url);
			String jsonString = serJsonInfo.postJsonInfoToService(baseURL);
			System.out.println(jsonString);
			//����ҵ���������
			List<Map<String, Object>> list = JsonTools.listKeyMaps("listmap", jsonString);
			System.out.println("-----test------>>>" + list.toString());
			//�������ݵĻ�ȡ��ʹ�����
			Iterator<Map<String, Object>> iter = list.iterator();
			while(iter.hasNext()){
				Map<String,Object> map = iter.next();
				//���԰�ֵ���䵽�ؼ�����ʹ��app���Զ�̬�Ļ�ȡ��̨��Ϣ
				System.out.println("id-->"+map.get("id") + "    name-->" + map.get("name") + "   address-->" + map.get("address"));
			}
		}

	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO �Զ����ɵķ������

			System.out.println("MyHandler:" + Thread.currentThread().getName());
			
		}
	}
	
}
