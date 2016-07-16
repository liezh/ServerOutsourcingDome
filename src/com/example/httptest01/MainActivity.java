package com.example.httptest01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.domain.task.Task;
import com.domain.task.TaskList;
import com.http.httpTool.EncapsulatePostBady;
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

	Button getButton, postButton;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getButton = (Button) findViewById(R.id.getButton);
		postButton = (Button) findViewById(R.id.postButton);

		handler = new MyHandler();

		getButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Thread t = new GetNetworkThread();
				t.start();

			}

		});

		postButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Thread t = new PostNetwordThread();
				t.start();
			}
		});

	}

	String baseURL = "http://192.168.90.227:8080/jsonProject/servlet/JsonAction";

	class GetNetworkThread extends Thread {

		@Override
		public void run() {
			// TODO �Զ����ɵķ������

			String url = baseURL + "?action_flag=taskList";
			System.out.println("getNetworkThread:"
					+ Thread.currentThread().getName());
			ServiceJsonInfo serJsonInfo = new ServiceJsonInfo();
			String jsonString = serJsonInfo.getJsonString(url);
			System.out.println(jsonString);
			// ����ҵ��������� Ҫ��action_flag��ֵ��Ӧ�ĸı�

			// ����ȡtask��Ϣ�ĵĻ�ȡ�ͽ���
			// Task task = JsonTools.getTask("task", jsonString);
			// System.out.println("--task Json������� ����--->" + task.toString());

			// ����ȡtaskList����Ϣ�Ļ�ȡ�ͽ���
			List<TaskList> list = JsonTools.getTaskListInfo("taskList",
					jsonString);
			System.out.println("--taskList Json������� ����--->" + list.toString());

			// ���Ի�ȡ�ͽ���mapList����Ϣ
			// List<Map<String, Object>> list = JsonTools.listKeyMaps("mapList",
			// jsonString);
			// System.out.println("-----test------>>>" + list.toString());
			// �������ݵĻ�ȡ��ʹ�����
			// Iterator<Map<String, Object>> iter = list.iterator();
			// while(iter.hasNext()){
			// Map<String,Object> map = iter.next();
			// //���԰�ֵ���䵽�ؼ�����ʹ��app���Զ�̬�Ļ�ȡ��̨��Ϣ
			// System.out.println("id-->"+map.get("id") + "    name-->" +
			// map.get("name") + "   address-->" + map.get("address"));
			// }
		}

	}

	class PostNetwordThread extends Thread {

		@Override
		public void run() {
			System.out.println("postNetworkThread:"
					+ Thread.currentThread().getName());
			ServiceJsonInfo serJsonInfo = new ServiceJsonInfo();

			// ��Ҫ��������ݷ�װ��nameValuePair���в�����Ӵ������д����ServiceJsonInfo���post�����ϴ�
			
			List<NameValuePair> nameValuePairs = EncapsulatePostBady.setCreateTaskPostBady(
					"111111", "�����ύ����������Ϣ", "���Բ��Բ����ٲ���", "20Ԫ", "16.7.18");
			
			
			
			
			String postUrl = "http://192.168.90.227:8080/jsonProject/servlet/TestPostInfo";
			String jsonString = serJsonInfo.postJsonInfoToService(postUrl,
					nameValuePairs);
			System.out.println(jsonString);
			// ����ҵ���������
//			List<Map<String, Object>> list = JsonTools.listKeyMaps("listmap",
//					jsonString);
//			System.out.println("-----test------>>>" + list.toString());
//			// �������ݵĻ�ȡ��ʹ�����
//			Iterator<Map<String, Object>> iter = list.iterator();
//			while (iter.hasNext()) {
//				Map<String, Object> map = iter.next();
//				// ���԰�ֵ���䵽�ؼ�����ʹ��app���Զ�̬�Ļ�ȡ��̨��Ϣ
//				System.out.println("id-->" + map.get("id") + "    name-->"
//						+ map.get("name") + "   address-->"
//						+ map.get("address"));
//			}
		}
	}

	private List<NameValuePair> setCreateTaskPostBady(String user_id,
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
	
	
	/**
	 * �ѳ������ɵ�json�����뵽�����嵱��
	 * @param key ��
	 * @param jsonString json���
	 * @return
	 */
	private List<NameValuePair> setJsonStringToPostBody(String key,String jsonString){
		
		NameValuePair nameValuePair = new BasicNameValuePair(key, jsonString);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(nameValuePair);
		
		return nameValuePairs;
	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO �Զ����ɵķ������

			System.out.println("MyHandler:" + Thread.currentThread().getName());

		}
	}

}
