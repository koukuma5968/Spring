package com.controller.builder;

import java.util.HashMap;

import com.model.sql.dao.SideLinkDao;
import com.param.ParamBean;

public enum PageBuilderType implements BuilderInterface {

	ARTICLE("articleBean") {
		@Override
		public Object createBody(ParamBean param) {

			String articleHtml = this.getHtmlPass().concat(param.getPath()).concat(this.getHtml());

			HashMap<String, String> articleBean = new HashMap<String, String>();

			articleBean.put("articleHtml", articleHtml);

			return articleBean;
		}

	},
	SIDE("sideLink") {
		@Override
		public Object createBody(ParamBean param) {
			return SideLinkDao.SEL_SIDE_LINK(param);
		}
	};

	private PageBuilderType(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return this.key;
	}

	private String htmlPass = "/article/";
	private String html = "/sheet001";

	public String getHtmlPass() {
		return htmlPass;
	}

	public String getHtml() {
		return html;
	}

}
