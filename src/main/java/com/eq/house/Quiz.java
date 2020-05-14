package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String title;
	Topic topic;
	Language lang;

	Quiz() {
		System.out.println("** Quiz class: default constructor called **");
	}

	Quiz(String _title, Integer _topic, Integer _lang) {
		this.title = _title;
		this.topic = Utilities.intToTopic(_topic);
		this.lang = Utilities.intToLanguage(_lang);
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
	public Language getLang() {
		return lang;
	}
	public void setLang(Language lang) {
		this.lang = lang;
	}
}
