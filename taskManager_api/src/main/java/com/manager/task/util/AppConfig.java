package com.manager.task.util;

import java.io.File;
import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Value("${tomcat.ajp.port}")
	int ajpPort;

	@Value("${tomcat.ajp.remoteauthentication}")
	String remoteAuthentication;

	@Value("${tomcat.document-root}")
	String documentroot;

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
		return getFactory();
	}

	private Connector redirectConnector() {
		Connector ajpConnector = new Connector("AJP/1.3");
		ajpConnector.setPort(ajpPort);
		ajpConnector.setSecure(false);
		ajpConnector.setAllowTrace(false);
		ajpConnector.setScheme("https");
		((AbstractAjpProtocol) ajpConnector.getProtocolHandler()).setSecretRequired(false);

		return ajpConnector;
	}

	private WebServerFactoryCustomizer<TomcatServletWebServerFactory> getFactory() {

		return server -> {
			server.setDocumentRoot(new File(documentroot));
			Optional.ofNullable(server)
			.ifPresent(s -> s.addAdditionalTomcatConnectors(redirectConnector()));
		};
	}

}