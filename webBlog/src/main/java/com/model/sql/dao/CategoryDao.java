package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.CategoryMng;
import com.param.ParamBean;

public class CategoryDao extends DaoBase {

	public static List<CategoryMng> SEL_CATEGORY_MNG() {
		return articleMapper.SEL_CATEGORY_MNG();
	}

	public static CategoryMng SEL_CATEGORY_MNG_NAME(ParamBean param) {
		return articleMapper.SEL_CATEGORY_MNG_NAME(param);
	}
}
