package com.eq.house;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReportQuestionContent {
	private String playerId;
	private String quizId;
	private Integer questionNum;
	private ReportSubject reportSubject;
	private String reportDesc;

	public ReportQuestionContent() {
		this.playerId = null;
		this.quizId = null;
		this.questionNum = -1;
		this.reportSubject = ReportSubject.Abuse;
		this.reportDesc = "";
	}

	public ReportQuestionContent(final String json) {
		JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

		this.playerId = obj.get("playerId").getAsString();
		this.quizId = obj.get("quizId").getAsString();
		this.questionNum = obj.get("questionNum").getAsInt();
		this.reportSubject =
		    Utilities.intToReportSubject(obj.get("reportSubject").getAsInt());
		this.reportDesc = obj.get("reportDesc").getAsString();
	}

	public void outputObject() {
		final String rs = Utilities.reportSubjectToString(this.reportSubject);

		System.out.println("----- Report Question Content -----");
		System.out.println("playerId:      " + (this.playerId == null ? "(null)" : this.playerId));
		System.out.println("quizId:        " + (this.quizId == null ? "(null)" : this.quizId));
		System.out.println("questionNum:   " + this.questionNum);
		System.out.println("reportSubject: " + rs);

		System.out.println("DESC:");
		System.out.println(this.reportDesc);
		System.out.println("----------");
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

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


	public ReportSubject getReportSubject() {
		return reportSubject;
	}
	public void setReportSubject(ReportSubject reportSubject) {
		this.reportSubject = reportSubject;
	}


	public String getReportDesc() {
		return reportDesc;
	}
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}
}
