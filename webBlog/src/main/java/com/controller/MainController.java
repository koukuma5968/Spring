package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.controller.builder.PageBuilder;
import com.model.sql.dao.DaoBase;
import com.model.sql.mapper.ArticleMapper;
import com.param.ParamBean;
import com.util.RequestCipher;
import com.util.UserAgentType;

@Controller
public class MainController extends PageBuilder {

	public MainController(ArticleMapper mapper) {
		DaoBase.articleMapper = mapper;
	}

	@RequestMapping(name="home", path="/")
	public String home(Model model, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		String ret = "main/home";
//		if (UserAgentType.checkMobile(userAgent)) {
//			ret = "mobile/mhome";
//		} else {
			super.execute(model, userAgent);
//		}

		return ret;
	}

	@RequestMapping(name="category", path="/category")
	public String category(Model model, @RequestParam("get") String param, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		RequestCipher cipr = new RequestCipher();
		ParamBean bean = cipr.decode(param);

		String ret = "main/category";
//		if (UserAgentType.checkMobile(userAgent)) {
//			ret = "mobile/mhome";
//		} else {
			super.executeCategory(model, bean, userAgent);
//		}

		return ret;
	}

	@RequestMapping(name="article", path="/article")
	public String article(Model model, @RequestParam("get") String param, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		RequestCipher cipr = new RequestCipher();
		ParamBean bean = cipr.decode(param);

		String ret = "main/article";
//		if (UserAgentType.checkMobile(userAgent)) {
//			ret = "mobile/mhome";
//		} else {
			super.executeArticle(model, bean, userAgent);
//		}

		return ret;
	}

	@RequestMapping(name="content", path="/content")
	public String content(Model model, @RequestParam("get") String param, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		RequestCipher cipr = new RequestCipher();
		ParamBean bean = cipr.decode(param);

		super.executeContent(model, bean, userAgent);

		return "main/content";
	}

	@RequestMapping(name="download", path="/download")
	public String download(Model model, @RequestParam("path") String path, 
			@RequestParam("name") String name, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeDownload(path, name, response, userAgent);

		return null;
	}
}
