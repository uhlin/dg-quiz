package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String playerId;
	private String quizId;
	private Integer questionNum;
	private Integer ansAlt;

	private Boolean opt1Checked;
	private Boolean opt2Checked;
	private Boolean opt3Checked;
	private Boolean opt4Checked;
	private Boolean opt5Checked;
	private Boolean opt6Checked;

	public Answer() {
		this.playerId = null;
		this.quizId = null;
		this.questionNum = -1;
		this.ansAlt = 4;

		this.opt1Checked = false;
		this.opt2Checked = false;
		this.opt3Checked = false;
		this.opt4Checked = false;
		this.opt5Checked = false;
		this.opt6Checked = false;
	}

	public Answer(final String json) {
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

		this.playerId = obj.get("playerId").getAsString();
		this.quizId = obj.get("quizId").getAsString();
		this.questionNum = obj.get("questionNum").getAsInt();
		this.ansAlt = obj.get("ansAlt").getAsInt();

		this.opt1Checked = obj.get("opt1").getAsBoolean();
		this.opt2Checked = obj.get("opt2").getAsBoolean();
		this.opt3Checked = obj.get("opt3").getAsBoolean();
		this.opt4Checked = obj.get("opt4").getAsBoolean();
		this.opt5Checked = obj.get("opt5").getAsBoolean();
		this.opt6Checked = obj.get("opt6").getAsBoolean();
	}

	public void outputObject() {
		System.out.println("----- Answer -----");
		System.out.println("playerId:    " + (this.playerId == null ? "(null)" : this.playerId));
		System.out.println("quizId:      " + (this.quizId == null ? "(null)" : this.quizId));
		System.out.println("questionNum: " + this.questionNum);
		System.out.println("ansAlt:      " + this.ansAlt);

		System.out.println("opt1: " + this.opt1Checked.toString());
		System.out.println("opt2: " + this.opt2Checked.toString());
		System.out.println("opt3: " + this.opt3Checked.toString());
		System.out.println("opt4: " + this.opt4Checked.toString());
		System.out.println("opt5: " + this.opt5Checked.toString());
		System.out.println("opt6: " + this.opt6Checked.toString());
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}


	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}


	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}


	public Integer getAnsAlt() {
		return ansAlt;
	}
	public void setAnsAlt(Integer ansAlt) {
		this.ansAlt = ansAlt;
	}

/***************************************************
 *
 * More getters/setters
 *
 ***************************************************/

	public Boolean getOpt1Checked() {
		return opt1Checked;
	}
	public Boolean getOpt2Checked() {
		return opt2Checked;
	}
	public Boolean getOpt3Checked() {
		return opt3Checked;
	}
	public Boolean getOpt4Checked() {
		return opt4Checked;
	}
	public Boolean getOpt5Checked() {
		return opt5Checked;
	}
	public Boolean getOpt6Checked() {
		return opt6Checked;
	}

	public void setOpt1Checked(Boolean opt1Checked) {
		this.opt1Checked = opt1Checked;
	}
	public void setOpt2Checked(Boolean opt2Checked) {
		this.opt2Checked = opt2Checked;
	}
	public void setOpt3Checked(Boolean opt3Checked) {
		this.opt3Checked = opt3Checked;
	}
	public void setOpt4Checked(Boolean opt4Checked) {
		this.opt4Checked = opt4Checked;
	}
	public void setOpt5Checked(Boolean opt5Checked) {
		this.opt5Checked = opt5Checked;
	}
	public void setOpt6Checked(Boolean opt6Checked) {
		this.opt6Checked = opt6Checked;
	}
}
