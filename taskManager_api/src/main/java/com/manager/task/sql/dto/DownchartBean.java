package com.manager.task.sql.dto;

public class DownchartBean {

	private String day;
	private int taskSum;
	private int expectedNum;
	private int resultNum;

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getTaskSum() {
		return taskSum;
	}
	public void setTaskSum(int taskSum) {
		this.taskSum = taskSum;
	}
	public int getExpectedNum() {
		return expectedNum;
	}
	public void setExpectedNum(int expectedNum) {
		this.expectedNum = expectedNum;
	}
	public int getResultNum() {
		return resultNum;
	}
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}

}
