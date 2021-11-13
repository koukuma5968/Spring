package com.controller.builder;

import java.util.HashMap;

import com.param.ParamBean;
import com.util.FileUtil;

public enum ContentDownloadBuilderType implements BuilderInterface {

	CONTENT("contentBean") {
		@Override
		public Object createBody(ParamBean param) {

			String content = FileUtil.contentPass + param.getPath() + param.getFilename();

			HashMap<String, String> contentBean = new HashMap<String, String>();

			contentBean.put("contentHtml", content);
			contentBean.put("contentname", param.getName());

			return contentBean;
		}
	};

	private ContentDownloadBuilderType(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return this.key;
	}

}
