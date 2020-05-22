package com.eq.house;

public class Question {
	private QuestionType qType;

	private QuestionText qText;
	private QuestionSound qSound;
	private QuestionImage qImage;

	public Question() {
		this.qType = QuestionType.None;
		this.qText = null;
		this.qSound = null;
		this.qImage = null;
		System.out.println("** Question class: default constructor called **");
	}

	public Question(QuestionType qType) {
		this.qType = qType;
		this.qText = null;
		this.qSound = null;
		this.qImage = null;
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public QuestionType getqType() {
		return qType;
	}
	public void setqType(QuestionType qType) {
		this.qType = qType;
	}


	public QuestionText getqText() {
		return qText;
	}
	public void setqText(QuestionText qText) {
		this.qText = qText;
	}


	public QuestionSound getqSound() {
		return qSound;
	}
	public void setqSound(QuestionSound qSound) {
		this.qSound = qSound;
	}


	public QuestionImage getqImage() {
		return qImage;
	}
	public void setqImage(QuestionImage qImage) {
		this.qImage = qImage;
	}
}
