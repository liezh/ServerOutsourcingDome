package com.domain.task;

public class CompleteTask {

	private int task_id;
	private int user_id;

	public CompleteTask() {
		// TODO 自动生成的构造函数存根
	}

	public CompleteTask(int task_id, int user_id) {
		this.task_id = task_id;
		this.user_id = user_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "CompateTask [task_id=" + task_id + ", user_id=" + user_id + "]";
	}

}
