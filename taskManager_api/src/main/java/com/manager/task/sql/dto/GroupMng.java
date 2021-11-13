package com.manager.task.sql.dto;

public class GroupMng implements DataTransferObject {

	private int id;
	private String name;
	private String gate_key;
	private int scope;

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
	public String getGate_key() {
		return gate_key;
	}
	public void setGate_key(String gate_key) {
		this.gate_key = gate_key;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}

}
