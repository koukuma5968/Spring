package com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

public class ResouceCash {

	@Value("${tomcat.cssfiles}")
	String cssfiles;

	@Bean
	public void setCssResouceCash(Model model) {
		
	}
}
