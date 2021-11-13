package com.manager.task.sql.dto;

public class UserMng implements DataTransferObject {

	private String gate_key;
	private String user_id;
	private String email_add;
	private String family_name;
	private String first_name;
	private String pass_enc;
	private String reg_date;

	public String getGate_key() {
		return gate_key;
	}
	public void setGate_key(String gate_key) {
		this.gate_key = gate_key;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmail_add() {
		return email_add;
	}
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPass_enc() {
		return pass_enc;
	}
	public void setPass_enc(String pass_enc) {
		this.pass_enc = pass_enc;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

}
