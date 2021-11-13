package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.ArticleMNG;
import com.param.ParamBean;

public class ArticleDao extends DaoBase {

	public static List<ArticleMNG> getArticleMNG(ParamBean param) {
		return articleMapper.SEL_ARTILCE_MNG(param);
	}

	public static ArticleMNG getArticleMNGName(ParamBean param) {
		return articleMapper.SEL_ARTILCE_MNG_NAME(param);
	}

	public static List<ArticleMNG> getArchiveList() {
		return articleMapper.SEL_ARCHIVE_LIST();
	}
}
