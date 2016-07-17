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
import com.http.httpTool.AsyncImageLoader;
import com.http.httpTool.CallBackImpl;
import com.http.httpTool.EncapsulatePostBady;
import com.http.httpTool.ServiceJsonInfo;
import com.json.tools.JsonTools;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	Button getButton, postButton;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	Handler handler;
	private AsyncImageLoader loader = new AsyncImageLoader();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getButton = (Button) findViewById(R.id.getButton);
		postButton = (Button) findViewById(R.id.postButton);

		loadImage("https://p1.ssl.qhimg.com/t0151320b1d0fc50be8.png",
				R.id.imageView1);
		loadImage(
				"http://img.manhua.weibo.com/cover/2014/06/09/1248536065_CTy52vFa_b.jpg",
				R.id.imageView2);
		loadImage(
				"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png",
				R.id.imageView3);

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
				// TODO 自动生成的方法存根
				Thread t = new PostNetwordThread();
				t.start();
			}
		});
	}

	private void loadImage(String url, int imagID) {
		// TODO 自动生成的方法存根
		ImageView imageView = (ImageView) findViewById(imagID);
		CallBackImpl callBackImpl = new CallBackImpl(imageView);
		Drawable cacheImage = loader.loadDrawable(url, callBackImpl);
		if (cacheImage != null) {
			imageView.setImageDrawable(cacheImage);
		}

	}

	String baseURL = "http://192.168.90.227:8080/jsonProject/servlet/JsonAction";

	class GetNetworkThread extends Thread {

		@Override
		public void run() {
			// TODO 自动生成的方法存根

			String url = baseURL + "?action_flag=taskList";
			System.out.println("getNetworkThread:"
					+ Thread.currentThread().getName());
			ServiceJsonInfo serJsonInfo = new ServiceJsonInfo();
			String jsonString = serJsonInfo.getJsonString(url);
			System.out.println(jsonString);
			// 根据业务解析数据 要把action_flag的值相应的改变

			// 检测获取task信息的的获取和解析
			// Task task = JsonTools.getTask("task", jsonString);
			// System.out.println("--task Json语句生成 测试--->" + task.toString());

			// 检测获取taskList的信息的获取和解析
			List<TaskList> list = JsonTools.getTaskListInfo("taskList",
					jsonString);
			System.out.println("--taskList Json语句生成 测试--->" + list.toString());

			// 测试获取和解析mapList的信息
			// List<Map<String, Object>> list = JsonTools.listKeyMaps("mapList",
			// jsonString);
			// System.out.println("-----test------>>>" + list.toString());
			// 测试数据的获取的使用情况
			// Iterator<Map<String, Object>> iter = list.iterator();
			// while(iter.hasNext()){
			// Map<String,Object> map = iter.next();
			// //可以把值传输到控件当中使得app可以动态的获取后台信息
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

			// 把要传输的数据封装到nameValuePair当中并且添加带链表当中传入给ServiceJsonInfo类的post方法上传

			// 测试封装postBody的语句
			// List<NameValuePair> nameValuePairs =
			// EncapsulatePostBady.setCreateTaskPostBady(
			// "111111", "测试提交创建任务信息", "测试测试测试再测试", "20元", "16.7.18");

			// 测试封装jsonString到postBody当中并传输
			List<TaskList> list = new ArrayList<TaskList>();
			TaskList t1 = new TaskList(1234, "测试传递jsonString到后台",
					"就是用来测试就是用来测试！！！", "3元", 3);
			TaskList t2 = new TaskList(4321, "测试传递jsonString到后台",
					"就是用来测试就是用来测试！！！", "20元", 20);
			list.add(t1);
			list.add(t2);
			List<NameValuePair> nameValuePairs = EncapsulatePostBady
					.setJsonStringToPostBody("jsonString",
							JsonTools.toJsonString("jsonString", list));

			String postUrl = "http://192.168.90.227:8080/jsonProject/servlet/TestPostInfo";
			String jsonString = serJsonInfo.postJsonInfoToService(postUrl,
					nameValuePairs);
			System.out.println(jsonString);
			// 根据业务解析数据
			// List<Map<String, Object>> list = JsonTools.listKeyMaps("listmap",
			// jsonString);
			// System.out.println("-----test------>>>" + list.toString());
			// // 测试数据的获取的使用情况
			// Iterator<Map<String, Object>> iter = list.iterator();
			// while (iter.hasNext()) {
			// Map<String, Object> map = iter.next();
			// // 可以把值传输到控件当中使得app可以动态的获取后台信息
			// System.out.println("id-->" + map.get("id") + "    name-->"
			// + map.get("name") + "   address-->"
			// + map.get("address"));
			// }
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
	 * 把程序生成的json语句加入到请求体当中
	 * 
	 * @param key
	 *            键
	 * @param jsonString
	 *            json语句
	 * @return
	 */
	private List<NameValuePair> setJsonStringToPostBody(String key,
			String jsonString) {

		NameValuePair nameValuePair = new BasicNameValuePair(key, jsonString);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(nameValuePair);

		return nameValuePairs;
	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO 自动生成的方法存根

			System.out.println("MyHandler:" + Thread.currentThread().getName());

		}
	}

}
