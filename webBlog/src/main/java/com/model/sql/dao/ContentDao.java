package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.ContentMng;

public class ContentDao extends DaoBase {

	public static List<ContentMng> getContentList() {
		return articleMapper.SEL_CONTENT_MNG();
	}

}
