package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.ContentMng;
import com.param.ParamBean;

public class ContentDao extends DaoBase {

	public static List<ContentMng> getContentList() {
		return articleMapper.SEL_CONTENT_MNG();
	}

	public static ContentMng getContentName(ParamBean param) {
		return articleMapper.SEL_CONTENT_NAME(param);
	}
}
