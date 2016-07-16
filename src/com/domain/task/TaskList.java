package com.domain.task;

public class TaskList {

	private int task_id;   //�����id
	private String title;  //�������
	private String synopsis; //������
	private String reward;  //����ɻ�ó���
	private int intergration; //����ɻ�û���
	

	public TaskList() {
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public TaskList(int task_id, String title, String synopsis, String reward,
			int intergration) {
		this.task_id = task_id;
		this.title = title;
		this.synopsis = synopsis;
		this.reward = reward;
		this.intergration = intergration;
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


	public int getIntergration() {
		return intergration;
	}


	public void setIntergration(int intergration) {
		this.intergration = intergration;
	}

	@Override
	public String toString() {
		return "TaskList [task_id=" + task_id + ", title=" + title
				+ ", synopsis=" + synopsis + ", reward=" + reward
				+ ", intergration=" + intergration + "]";
	}

	
	
	
}
