package com.manager.task.sql.dto;

import java.util.LinkedHashMap;
import java.util.List;

public class ProjectMng implements DataTransferObject {

	private int id;
	private String name;
	private String startDay;
	private String endDay;
	private int group_id;
	private String reg_date;
	private String up_date;
	private int process_count;
	private int task_count;
	private int task_remain_count;
	private LinkedHashMap<String, List<DataTransferObject>> task;
	private List<DownchartBean> downchartList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUp_date() {
		return up_date;
	}
	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}
	public int getProcess_count() {
		return process_count;
	}
	public void setProcess_count(int process_count) {
		this.process_count = process_count;
	}
	public int getTask_count() {
		return task_count;
	}
	public void setTask_count(int task_count) {
		this.task_count = task_count;
	}
	public int getTask_remain_count() {
		return task_remain_count;
	}
	public void setTask_remain_count(int task_remain_count) {
		this.task_remain_count = task_remain_count;
	}
	public LinkedHashMap<String, List<DataTransferObject>> getTask() {
		return task;
	}
	public void setTask(LinkedHashMap<String, List<DataTransferObject>> task) {
		this.task = task;
	}
	public List<DownchartBean> getDownchartList() {
		return downchartList;
	}
	public void setDownchartList(List<DownchartBean> downchartList) {
		this.downchartList = downchartList;
	}

}
