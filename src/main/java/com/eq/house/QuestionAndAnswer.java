package com.eq.house;

public class QuestionAndAnswer {
	private Question question;
	private Answer answer;

	public QuestionAndAnswer() {
		this.question = null;
		this.answer = null;
	}

	public QuestionAndAnswer(Question question, Answer answer) {
		this.question = question;
		this.answer = answer;
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}


	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
