package com.manager.task.sql.dto;

public class TaskMng implements DataTransferObject {

	private int id;
	private String name;
	private String startDay;
	private String endDay;
//	private int process_id;
	private int project_id;
	private int proc;

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
//	public int getProcess_id() {
//		return process_id;
//	}
//	public void setProcess_id(int process_id) {
//		this.process_id = process_id;
//	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getProc() {
		return proc;
	}
	public void setProc(int proc) {
		this.proc = proc;
	}

}
