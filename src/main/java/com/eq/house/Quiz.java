package com.eq.house;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String uniqueId;
	private String title;
	private Topic topic;
	private Language lang;
	private Integer numQuestions;

	public Quiz() {
		System.out.println("** Quiz class: default constructor called **");
	}

	public Quiz(String _title, Integer _topic, Integer _lang) {
		this.uniqueId = UUID.randomUUID().toString();
		this.title = _title;
		this.topic = Utilities.intToTopic(_topic);
		this.lang = Utilities.intToLanguage(_lang);
		this.numQuestions = 0;
	}


	/*********************
	 *
	 * getId() and setId()
	 *
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	/*********************************
	 *
	 * getUniqueId() and setUniqueId()
	 *
	 */
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	/***************************
	 *
	 * getTitle() and setTitle()
	 *
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	/***************************
	 *
	 * getTopic() and setTopic()
	 *
	 */
	public String getTopicString() {
		return Utilities.topicToString(topic);
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	/*************************
	 *
	 * getLang() and setLang()
	 *
	 */
	public String getLangString() {
		return Utilities.languageToString(lang);
	}
	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}


	/*****************************************
	 *
	 * getNumQuestions() and setNumQuestions()
	 *
	 */
	public Integer getNumQuestions() {
		return numQuestions;
	}
	public void setNumQuestions(Integer numQuestions) {
		this.numQuestions = numQuestions;
	}
	public Integer increaseNumQuestions() {
		return (++ this.numQuestions);
	}
	public Integer decreaseNumQuestion() {
		return (-- this.numQuestions);
	}
}
