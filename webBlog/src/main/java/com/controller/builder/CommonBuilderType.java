package com.controller.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.model.html.footer.FooterBean;
import com.model.html.header.HeaderBean;
import com.model.sql.dao.ArticleDao;
import com.model.sql.dao.CategoryDao;
import com.model.sql.dao.ContentDao;
import com.model.sql.dao.ExerciesDao;
import com.model.sql.dao.LanguageDao;
import com.model.sql.dto.ArticleMNG;
import com.model.sql.dto.CategoryMng;
import com.model.sql.dto.ContentMng;
import com.model.sql.dto.ExercisesMng;
import com.model.sql.dto.LanguageMng;
import com.param.ParamBean;

public enum CommonBuilderType implements BuilderInterface {

	HEAD("headlist") {
		@Override
		public Object createBody(ParamBean param) {

			ArrayList<HeaderBean> headList = new ArrayList<HeaderBean>();
			HeaderBean head = new HeaderBean();

			head.setTitle("Home");
			head.setLink("/");

			headList.add(head);
//			RequestCipher cipr = new RequestCipher();
//			head = new HeaderBean();
//
//			if (param != null) {
//				if (param.getType() == 0) {
//					ArticleMNG amng = ArticleDao.getArticleMNGName(param);
//					head.setTitle(amng.getTitle());
//				} else if (param.getType() == 1) {
//					CategoryMng cate = CategoryDao.SEL_CATEGORY_MNG_NAME(param);
//					head.setTitle(cate.getName());
//				} else if (param.getType() == 2) {
//					LanguageMng lang = LanguageDao.SEL_LANGUAGE_MNG_NAME(param);
//					head.setTitle(lang.getName());
//				}
//				head.setLink(param.getName() + cipr.encode(param));
//			}
//
//			headList.add(head);

			return headList;
		}

	},
	CATEGORY("categoryList") {
		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> categoryList = new ArrayList<Object>();
			List<CategoryMng> cateList = CategoryDao.SEL_CATEGORY_MNG();
			Iterator<CategoryMng> it = cateList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				CategoryMng cate = it.next();

				param = new ParamBean();
				body.put("id", cate.getId());
				body.put("type", 1);
				body.put("name", cate.getName());

				categoryList.add(body);
			}

			return categoryList;
		}

	},
	LANGUAGE("languageList") {

		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> languageList = new ArrayList<Object>();
			List<LanguageMng> langList = LanguageDao.SEL_LANGUAGE_MNG();
			Iterator<LanguageMng> it = langList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				LanguageMng lang = it.next();

				body.put("id", lang.getId());
				body.put("type", 2);
				body.put("name", lang.getName());

				languageList.add(body);
			}

			return languageList;
		}
		
	},
	ARCHIVE("archiveList") {

		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> contentList = new ArrayList<Object>();
			List<ArticleMNG> langList = ArticleDao.getArchiveList();
			Iterator<ArticleMNG> it = langList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				ArticleMNG archive = it.next();

				body.put("id", archive.getId());
				body.put("name", archive.getName());

				contentList.add(body);
			}

			return contentList;
		}
		
	},
	CONTENT("contentList") {

		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> contentList = new ArrayList<Object>();
			List<ContentMng> conList = ContentDao.getContentList();
			Iterator<ContentMng> it = conList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				ContentMng content = it.next();

				body.put("id", content.getId());
				body.put("name", content.getName());

				contentList.add(body);
			}

			return contentList;
		}
		
	},
	EXERCISES("execisesList") {

		@Override
		public Object createBody(ParamBean param) {

			ArrayList<Object> execisesList = new ArrayList<Object>();
			List<ExercisesMng> exeList = ExerciesDao.getExercisesList();
			Iterator<ExercisesMng> it = exeList.iterator();
			while (it.hasNext()) {

				HashMap<String, Object> body = new HashMap<String, Object>();
				ExercisesMng execises = it.next();

				body.put("lang", execises.getLang());

				execisesList.add(body);
			}

			return execisesList;
		}
		
	},
	FOOT("foot") {
		@Override
		public Object createBody(ParamBean bean) {

			FooterBean foot = new FooterBean();

			return foot;
		}

	};

	private CommonBuilderType(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return this.key;
	}
}
