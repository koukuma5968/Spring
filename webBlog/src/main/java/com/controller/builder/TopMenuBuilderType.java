package com.controller.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.model.sql.dao.ArticleDao;
import com.model.sql.dto.ArticleMNG;
import com.param.ParamBean;
import com.util.RequestCipher;

public enum TopMenuBuilderType implements BuilderInterface {

	MENU("articleList") {

		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> articleList = new ArrayList<Object>();
			List<ArticleMNG> amngList = ArticleDao.getArticleMNG(param);
			Iterator<ArticleMNG> it = amngList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				ArticleMNG amng = it.next();

				ParamBean bean = new ParamBean();
				bean.setId(amng.getId());
				bean.setPath(amng.getCategory_dir() + "/"+ amng.getName());
				bean.setName(amng.getName());

				body.put("articleMNG", amng);

				articleList.add(body);
			}

			return articleList;
		}

	};

	private TopMenuBuilderType(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return this.key;
	}
}
