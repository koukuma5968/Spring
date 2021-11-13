package com.controller.builder;

import java.util.HashMap;

import com.param.ParamBean;
import com.util.Historylog;

public class ApiPageBuilder {

	protected HashMap<String, Object> executeApi(String userAgent) {

		Historylog.setAccessHistory(userAgent, "mobietop");

		HashMap<String, Object> ret = new HashMap<String, Object>();
		for (TopMenuBuilderType page : TopMenuBuilderType.values()) {
			ret.put(page.getKey(), page.createBody(new ParamBean()));
		}

		return ret;
	}

	public HashMap<String, Object> executeApiArticle(ParamBean bean, String userAgent) {

		Historylog.setAccessHistory(userAgent, bean.getName());

		HashMap<String, Object> ret = new HashMap<String, Object>();
		for (PageBuilderType page : PageBuilderType.values()) {
			ret.put(page.getKey(), page.createBody(bean));
		}

		return ret;
	}

	public HashMap<String, Object> executeApiHeaders(String userAgent) {

		HashMap<String, Object> ret = new HashMap<String, Object>();
		for (CommonBuilderType common : CommonBuilderType.values()) {
			ret.put(common.getKey(), common.createBody(new ParamBean()));
		}

		return ret;
	}
}
