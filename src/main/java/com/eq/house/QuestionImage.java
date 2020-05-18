package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class QuestionImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	public QuestionImage() {
		;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
