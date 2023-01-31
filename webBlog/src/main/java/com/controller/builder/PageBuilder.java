package com.controller.builder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.model.sql.dao.ArticleDao;
import com.model.sql.dao.CategoryDao;
import com.model.sql.dao.ContentDao;
import com.model.sql.dto.ArticleMNG;
import com.model.sql.dto.CategoryMng;
import com.model.sql.dto.ContentMng;
import com.param.ParamBean;
import com.util.FileUtil;
import com.util.Historylog;

public class PageBuilder {

	protected void execute(Model model, String userAgent) {

		Historylog.setAccessHistory(userAgent, "top");

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(new ParamBean()));
		}

		for (TopMenuBuilderType page : TopMenuBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(null));
		}

	}

	public void executeArticle(Model model, String id, String userAgent) {

		ParamBean bean = new ParamBean();
		bean.setId(Integer.parseInt(id));

		ArticleMNG article = ArticleDao.getArticleMNGName(bean);
		Historylog.setAccessHistory(userAgent, article.getTitle());

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(bean));
		}

		bean.setPath(article.getCategory_dir() + "/"+ article.getName());
		for (PageBuilderType page : PageBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}

	}

	public void executeCategory(Model model, String id, String type, String userAgent) {

		ParamBean bean = new ParamBean();
		bean.setId(Integer.parseInt(id));
		bean.setType(Integer.parseInt(type));

		CategoryMng cate = CategoryDao.SEL_CATEGORY_MNG_NAME(bean);

		Historylog.setAccessHistory(userAgent, cate.getName());

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(bean));
		}

		for (TopMenuBuilderType page : TopMenuBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}

	}

	public void executeContent(Model model, String id, String userAgent) {

		ParamBean bean = new ParamBean();
		bean.setId(Integer.parseInt(id));
		ContentMng content = ContentDao.getContentName(bean);
		bean.setName(content.getName());
		bean.setPath(content.getPath());
		bean.setFilename(content.getFilename());

		Historylog.setAccessHistory(userAgent, content.getName());

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(bean));
		}

		for (ContentDownloadBuilderType page : ContentDownloadBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}

	}

	public void executeDownload(String path, String name, HttpServletResponse response, String userAgent) {

		Historylog.setDownloadHistory(userAgent);
		FileUtil.downloadFile(response, path, name);
	}

	public void executeExercises(Model model, String lang, String id, String type, String userAgent) {

		Historylog.setAccessHistory(userAgent, "exercises_" + lang + id + type);

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(null));
		}

		ParamBean bean = new ParamBean();
		bean.setId(Integer.parseInt(id));
		bean.setName(lang);
		bean.setType(Integer.parseInt(type));

		for (ExercisesBuilderType page : ExercisesBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}


	}

}
