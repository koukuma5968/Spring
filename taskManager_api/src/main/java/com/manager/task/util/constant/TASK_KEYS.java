package com.manager.task.util.constant;

public enum TASK_KEYS {

	INCOMPATIBLE(0),
	WORKING(1),
	CONFIRMING(2),
	COMPLETED(3);

	private TASK_KEYS(int status) {
		this.status = status;
	}

	private int status;

	public int getStatus() {
		return this.status;
	}
}
