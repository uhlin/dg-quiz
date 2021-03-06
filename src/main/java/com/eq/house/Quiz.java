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
	private Integer numQuestionsGoal;
	private Integer numQuestions;
	private Boolean completedAddingQuestions;

	public Quiz() {
		/* null */;
	}

	public Quiz(String _title, Integer _topic, Integer _lang) {
		this.uniqueId = UUID.randomUUID().toString();
		this.title = _title;
		this.topic = Utilities.intToTopic(_topic);
		this.lang = Utilities.intToLanguage(_lang);
		this.numQuestionsGoal = 0;
		this.numQuestions = 0;
		this.completedAddingQuestions = false;
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


	/*************************************************
	 *
	 * getNumQuestionsGoal() and setNumQuestionsGoal()
	 *
	 */
	public Integer getNumQuestionsGoal() {
		return numQuestionsGoal;
	}
	public void setNumQuestionsGoal(Integer numQuestionsGoal) {
		this.numQuestionsGoal = numQuestionsGoal;
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


	/*****************************************************************
	 *
	 * getCompletedAddingQuestions() and setCompletedAddingQuestions()
	 *
	 */
	public Boolean getCompletedAddingQuestions() {
		return completedAddingQuestions;
	}
	public void setCompletedAddingQuestions(Boolean completedAddingQuestions) {
		this.completedAddingQuestions = completedAddingQuestions;
	}
}
