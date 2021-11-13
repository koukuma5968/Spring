package com.model.sql.dto;

public class ArticleMNG {

	private int id;
	private String name;
	private String title;
	private String REG_DATE;
	private String UP_DATE;
	private int category_id;
	private String category_name;
	private String category_dir;
	private int language_id;
	private String language_name;

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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getUP_DATE() {
		return UP_DATE;
	}
	public void setUP_DATE(String uP_DATE) {
		UP_DATE = uP_DATE;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getLanguage_name() {
		return language_name;
	}
	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}
	public String getCategory_dir() {
		return category_dir;
	}
	public void setCategory_dir(String category_dir) {
		this.category_dir = category_dir;
	}

}
