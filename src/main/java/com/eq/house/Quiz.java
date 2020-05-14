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

	Quiz(String _title, Integer _topic, Integer _lang) {
		this.title = _title;
		this.topic = Utilities.intToTopic(_topic);
		this.lang = Utilities.intToLanguage(_lang);
	}
}
