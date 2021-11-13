package com.controller;

import java.util.HashMap;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.builder.ApiPageBuilder;
import com.param.ParamBean;
import com.util.RequestCipher;

@CrossOrigin(origins = "http://localhost:8091")
@RestController
@RequestMapping(path = "api/", method = RequestMethod.GET)
public class ApiController extends ApiPageBuilder {

	@GetMapping("main")
	public HashMap<String, Object> main(Model model, @RequestHeader("User-Agent") String userAgent) {

		return super.executeApi(userAgent);
	}

	@GetMapping("header")
	public HashMap<String, Object> header(Model model, @RequestHeader("User-Agent") String userAgent) {

		return super.executeApiHeaders(userAgent);
	}

	@GetMapping("article")
	public HashMap<String, Object> article(Model model, @RequestParam("get") String param, @RequestHeader("User-Agent") String userAgent) {

		RequestCipher cipr = new RequestCipher();
		ParamBean bean = cipr.decode(param);

		return super.executeApiArticle(bean, userAgent);
	}

}
