package com.json.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.task.Task;
import com.domain.task.TaskList;

/**
 * 完成对json数据的解析
 * 
 * @author jack
 * 
 */
public class JsonTools {

	public JsonTools() {
		// TODO Auto-generated constructor stub
	}

	public static Task getTask(String key, String jsonString) {
		Task task = new Task();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONObject taskObject = jsonObject.getJSONObject("task");
			task.setTask_id(taskObject.getInt("task_id"));
			task.setTitle(taskObject.getString("title"));
			task.setSynopsis(taskObject.getString("synopsis"));
			task.setBeginTime(taskObject.getString("beginTime"));
			task.setEndTime(taskObject.getString("endTime"));
			task.setIntergtation(taskObject.getInt("intergtation"));
			task.setReward(taskObject.getString("reward"));
			task.setUrgent(taskObject.getInt("urgent"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return task;
	}
	

	public static List<String> getList(String key, String jsonString) {
		List<String> list = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				String msg = jsonArray.getString(i);
				list.add(msg);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public static List<Map<String, Object>> listKeyMaps(String key,
			String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> iterator = jsonObject2.keys();
				while (iterator.hasNext()) {
					String json_key = iterator.next();
					Object json_value = jsonObject2.get(json_key);
					if (json_value == null) {
						json_value = "";
					}
					map.put(json_key, json_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 把数据value转换成相对应的json语句并返回
	 * @param key
	 * @param value
	 * @return
	 */
	public static String toJsonString(String key, Object value) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(key, value);
		} catch (JSONException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	
	
	
	public static List<Task> getTasks(String key, String jsonString) {
		List<Task> list = new ArrayList<Task>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			// 返回json的数组
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Task task = new Task();
				task.setTask_id(jsonObject2.getInt("task_id"));
				task.setTitle(jsonObject2.getString("title"));
				task.setSynopsis(jsonObject2.getString("synopsis"));
				task.setBeginTime(jsonObject2.getString("beginTime"));
				task.setEndTime(jsonObject2.getString("endTime"));
				task.setIntergtation(jsonObject2.getInt("intergtation"));
				task.setReward(jsonObject2.getString("reward"));
				task.setUrgent(jsonObject2.getInt("urgent"));
				list.add(task);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<TaskList> getTaskListInfo(String key, String jsonString){
		List<TaskList> list = new ArrayList<TaskList>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			// 返回json的链表
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				TaskList taskList = new TaskList();
				taskList.setTask_id(jsonObject2.getInt("task_id"));
				taskList.setTitle(jsonObject2.getString("title"));
				taskList.setSynopsis(jsonObject2.getString("synopsis"));
				taskList.setIntergration(jsonObject2.getInt("intergration"));
				taskList.setReward(jsonObject2.getString("reward"));
				list.add(taskList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
