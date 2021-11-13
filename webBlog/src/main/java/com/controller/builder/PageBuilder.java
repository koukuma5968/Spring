package com.controller.builder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.param.ParamBean;
import com.util.FileUtil;
import com.util.Historylog;

public class PageBuilder {

	protected void execute(Model model, String userAgent) {

		Historylog.setAccessHistory(userAgent, "top");

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(null));
		}

		for (TopMenuBuilderType page : TopMenuBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(new ParamBean()));
		}

	}

	public void executeArticle(Model model, ParamBean bean, String userAgent) {

		Historylog.setAccessHistory(userAgent, bean.getName());

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(bean));
		}

		for (PageBuilderType page : PageBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}

	}

	public void executeCategory(Model model, ParamBean bean, String userAgent) {

		Historylog.setAccessHistory(userAgent, bean.getName());

		for (CommonBuilderType common : CommonBuilderType.values()) {
			model.addAttribute(common.getKey(), common.createBody(bean));
		}

		for (TopMenuBuilderType page : TopMenuBuilderType.values()) {
			model.addAttribute(page.getKey(), page.createBody(bean));
		}

	}

	public void executeContent(Model model, ParamBean bean, String userAgent) {

		Historylog.setAccessHistory(userAgent, bean.getName());

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

}
