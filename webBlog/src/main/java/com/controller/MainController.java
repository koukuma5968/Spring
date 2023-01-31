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
import com.util.UserAgentType;

@Controller
public class MainController extends PageBuilder {

	public MainController(ArticleMapper mapper) {
		DaoBase.articleMapper = mapper;
	}

	@RequestMapping(name="home", path="/")
	public String home(Model model, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.execute(model, userAgent);

		String ret = "main/home";
		if (UserAgentType.checkMobile(userAgent)) {
			ret = "mobile/mhome";
		}

		return ret;
	}

	@RequestMapping(name="category", path="/category")
	public String category(Model model, @RequestParam("pageid") String id, @RequestParam("type") String type, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeCategory(model, id, type, userAgent);
		String ret = "main/category";
		if (UserAgentType.checkMobile(userAgent)) {
			ret = "mobile/category";
		}

		return ret;
	}

	@RequestMapping(name="article", path="/article")
	public String article(Model model, @RequestParam("pageid") String id, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeArticle(model, id, userAgent);
		String ret = "main/article";
		if (UserAgentType.checkMobile(userAgent)) {
			ret = "mobile/article";
		}

		return ret;
	}

	@RequestMapping(name="content", path="/content")
	public String content(Model model, @RequestParam("pageid") String id, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeContent(model, id, userAgent);

		String ret = "main/content";
		if (UserAgentType.checkMobile(userAgent)) {
			ret = "mobile/content";
		}

		return ret;
	}

	@RequestMapping(name="exercises", path="/exercises")
	public String exercises(Model model, @RequestParam("lang") String lang, @RequestParam("pageid") String id, 
			@RequestParam("type") String type, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeExercises(model, lang, id, type, userAgent);

		String ret = "main/exercises";
		if (UserAgentType.checkMobile(userAgent)) {
			ret = "mobile/exercises";
		}
		return ret;
	}

	@RequestMapping(name="download", path="/download")
	public String download(Model model, @RequestParam("path") String path, 
			@RequestParam("name") String name, @RequestHeader("User-Agent") String userAgent, 
			HttpServletResponse response, HttpServletRequest request) {

		super.executeDownload(path, name, response, userAgent);

		return null;
	}
}
