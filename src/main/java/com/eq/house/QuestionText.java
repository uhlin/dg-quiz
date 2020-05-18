package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionText {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	public QuestionText() {
		;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
