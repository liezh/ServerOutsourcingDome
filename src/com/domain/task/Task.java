package com.domain.task;

import java.util.Date;

import android.provider.ContactsContract.Contacts.Data;

public class Task {

	private int id;
	private String title;
	private int reward;
	private String synopsis;
	private Date beginTime;
	private Date endTime;
	private int inttegtation;
	private int urgent;

	
	
	
	public Task(int id, String title, int reward, String synopsis) {
		this.id = id;
		this.title = title;
		this.reward = reward;
		this.synopsis = synopsis;
	}


	public Task() {
		// TODO 自动生成的构造函数存根
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getReward() {
		return reward;
	}


	public void setReward(int reward) {
		this.reward = reward;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", reward=" + reward
				+ ", synopsis=" + synopsis + "]";
	}

	
}
