package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.LanguageMng;
import com.param.ParamBean;

public class LanguageDao extends DaoBase {

	public static List<LanguageMng> SEL_LANGUAGE_MNG() {
		return articleMapper.SEL_LANGUAGE_MNG();
	}

	public static LanguageMng SEL_LANGUAGE_MNG_NAME(ParamBean param) {
		return articleMapper.SEL_LANGUAGE_MNG_NAME(param);
	}
}
