package com.domain.task;

public class CreateTask {

	private int user_id;
	private String title;
	private String synopsis;
	private String reward;
	private String endTime;
	
	
	public CreateTask() {
		// TODO 自动生成的构造函数存根
	}


	public CreateTask(int user_id, String title, String synopsis,
			String reward, String endTime) {
		this.user_id = user_id;
		this.title = title;
		this.synopsis = synopsis;
		this.reward = reward;
		this.endTime = endTime;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public String getReward() {
		return reward;
	}


	public void setReward(String reward) {
		this.reward = reward;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	@Override
	public String toString() {
		return "CreateTask [user_id=" + user_id + ", title=" + title
				+ ", synopsis=" + synopsis + ", reward=" + reward
				+ ", endTime=" + endTime + "]";
	}

	
	
	
}
