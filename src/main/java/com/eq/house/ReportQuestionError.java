package com.eq.house;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReportQuestionError {
	private String playerId;
	private String quizId;
	private Integer questionNum;
	private String reportErrorsDesc;

	public ReportQuestionError() {
		this.playerId = null;
		this.quizId = null;
		this.questionNum = -1;
		this.reportErrorsDesc = "";
	}

	public ReportQuestionError(final String json) {
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

		this.playerId = obj.get("playerId").getAsString();
		this.quizId = obj.get("quizId").getAsString();
		this.questionNum = obj.get("questionNum").getAsInt();
		this.reportErrorsDesc = obj.get("reportErrorsDesc").getAsString();
	}

	public void outputObject() {
		System.out.println("----- Report Question Error -----");
		System.out.println("playerId:    " + (this.playerId == null ? "(null)" : this.playerId));
		System.out.println("quizId:      " + (this.quizId == null ? "(null)" : this.quizId));
		System.out.println("questionNum: " + this.questionNum);

		System.out.println("DESC:");
		System.out.println(this.reportErrorsDesc);
		System.out.println("----------");
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


	public String getReportErrorsDesc() {
		return reportErrorsDesc;
	}
	public void setReportErrorsDesc(String reportErrorsDesc) {
		this.reportErrorsDesc = reportErrorsDesc;
	}
}
