package com.domain.task;



public class Task {

	private int task_id; //����ID
	private String title; //�������
	private String beginTime; //��ʼʱ��
	private String endTime; //����ʱ��
	private String synopsis; //������
	private String  reward; //���ͽ��
	private int intergtation; //��ɿɻ�û���
	private int urgent;    //�����ȼ�
	

	public Task() {
	}
	
	public Task(int task_id, String title, String beginTime, String endTime,
			String synopsis, String reward, int intergtation, int urgent) {
		this.task_id = task_id;
		this.title = title;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.synopsis = synopsis;
		this.reward = reward;
		this.intergtation = intergtation;
		this.urgent = urgent;
	}
	
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public int getIntergtation() {
		return intergtation;
	}
	public void setIntergtation(int intergtation) {
		this.intergtation = intergtation;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", title=" + title + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", synopsis=" + synopsis
				+ ", reward=" + reward + ", intergtation=" + intergtation
				+ ", urgent=" + urgent + "]";
	}

	

}
