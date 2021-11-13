package com.manager.task.sql.dto;

public class GroupAuthority implements DataTransferObject {

	private int group_id;
	private String gate_key;
	private int authority_level;

	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getGate_key() {
		return gate_key;
	}
	public void setGate_key(String gate_key) {
		this.gate_key = gate_key;
	}
	public int getAuthority_level() {
		return authority_level;
	}
	public void setAuthority_level(int authority_level) {
		this.authority_level = authority_level;
	}

}
