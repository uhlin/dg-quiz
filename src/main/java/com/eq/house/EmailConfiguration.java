package com.eq.house;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfiguration {
	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private Integer port;

	@Value("${spring.mail.username}")
	private String username;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${questionErrorsTo}")
	private String questionErrorsTo;

	@Value("${questionErrorsFrom}")
	private String questionErrorsFrom;

	@Value("${questionContentsTo}")
	private String questionContentsTo;

	@Value("${questionContentsFrom}")
	private String questionContentsFrom;

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

/***************************************************
 *
 * More getters/setters
 *
 ***************************************************/

	public String getQuestionErrorsTo() {
		return questionErrorsTo;
	}
	public void setQuestionErrorsTo(String questionErrorsTo) {
		this.questionErrorsTo = questionErrorsTo;
	}
	public String getQuestionErrorsFrom() {
		return questionErrorsFrom;
	}
	public void setQuestionErrorsFrom(String questionErrorsFrom) {
		this.questionErrorsFrom = questionErrorsFrom;
	}


	public String getQuestionContentsTo() {
		return questionContentsTo;
	}
	public void setQuestionContentsTo(String questionContentsTo) {
		this.questionContentsTo = questionContentsTo;
	}
	public String getQuestionContentsFrom() {
		return questionContentsFrom;
	}
	public void setQuestionContentsFrom(String questionContentsFrom) {
		this.questionContentsFrom = questionContentsFrom;
	}
}
