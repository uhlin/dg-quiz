package com.eq.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController {
	@Autowired
	private PlayController pc;

	@Autowired
	private EmailConfiguration emailConfiguration;

	@Autowired
	private EmailService emailService;

	private static final String reportSentJson = "{\"html\": \"Report Sent!\"}";
	private static final String failedJson = "{\"html\": \"An error occurred. Could not send report.\"}";

	private final String _getPlayerId(final String playerId) {
		StringBuilder sb = new StringBuilder("Player ID: ");
		sb.append(playerId);
		sb.append("\r\n");
		return sb.toString();
	}

	private final String _getQuizId(final String quizId) {
		StringBuilder sb = new StringBuilder("Quiz ID: ");
		sb.append(quizId);
		sb.append("\r\n");
		return sb.toString();
	}

	private final String _getQuizTitle(final String quizTitle) {
		StringBuilder sb = new StringBuilder("Quiz title: ");
		sb.append(quizTitle);
		sb.append("\r\n");
		return sb.toString();
	}

	private final String _getQuizTopic(final String quizTopic) {
		StringBuilder sb = new StringBuilder("Quiz topic: ");
		sb.append(quizTopic);
		sb.append("\r\n");
		return sb.toString();
	}

	private final String _getQuestionNumAndQuestion(Question question) {
		Integer questionNum = -1;
		String questionText = "";

		switch (question.getqType()) {
		case Text:
			questionNum = question.getqText().getQuestionNum();
			questionText = question.getqText().getQuestion();
			break;
		case Sound:
			questionNum = question.getqSound().getQuestionNum();
			questionText = question.getqSound().getQuestion();
			break;
		case Image:
			questionNum = question.getqImage().getQuestionNum();
			questionText = question.getqImage().getQuestion();
			break;
		default:
			questionNum = -1;
			questionText = "";
			break;
		}

		StringBuilder sb = new StringBuilder("Question num: ");

		sb.append(questionNum);
		sb.append("\r\n");
		sb.append("Question: ");
		sb.append(questionText);
		sb.append("\r\n\r\n");

		return sb.toString();
	}

	@PostMapping(path = "/reportQuestionError",
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public String reportQuestionErrors(@RequestBody ReportQuestionError report) {
		if (report == null)
			return failedJson;
		report.outputObject();

		Quiz quiz = pc.getQuizByUniqueId(report.getQuizId());
		Question question = pc.getQuestion(report.getQuizId(), report.getQuestionNum(), true);
		if (quiz == null || question == null)
			return failedJson;

		final String to = emailConfiguration.getQuestionErrorsTo();
		final String from = emailConfiguration.getQuestionErrorsFrom();

		StringBuilder subject = new StringBuilder("QUIZ REPORT: Question Error: ");

		subject.append(quiz.getTitle() + ' ');
		subject.append('(');
		subject.append(quiz.getTopicString());
		subject.append(')');

		StringBuilder text = new StringBuilder("QUESTION ERROR REPORT\r\n\r\n");

		text.append(_getPlayerId(report.getPlayerId()));
		text.append(_getQuizId(report.getQuizId()));
		text.append(_getQuizTitle(quiz.getTitle()));
		text.append(_getQuizTopic(quiz.getTopicString()));
		text.append(_getQuestionNumAndQuestion(question));
		text.append(report.getReportErrorsDesc());
		text.append("\r\n");

		emailService.sendSimpleMessage(to, from, subject.toString(), text.toString());

		return reportSentJson;
	}

	@PostMapping(path = "/reportQuestionContent",
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public String reportQuestionContents(@RequestBody ReportQuestionContent report) {
		if (report == null)
			return failedJson;
		report.outputObject();

		Quiz quiz = pc.getQuizByUniqueId(report.getQuizId());
		Question question = pc.getQuestion(report.getQuizId(), report.getQuestionNum(), true);
		if (quiz == null || question == null)
			return failedJson;

		final String to = emailConfiguration.getQuestionContentsTo();
		final String from = emailConfiguration.getQuestionContentsFrom();

		StringBuilder subject = new StringBuilder("QUIZ REPORT: QUESTION CONTENT ");

		subject.append('-');
		subject.append(Utilities.reportSubjectToString(report.getReportSubject()));
		subject.append('-');

		StringBuilder text = new StringBuilder("QUESTION CONTENT REPORT\r\n\r\n");

		text.append(_getPlayerId(report.getPlayerId()));
		text.append(_getQuizId(report.getQuizId()));
		text.append(_getQuizTitle(quiz.getTitle()));
		text.append(_getQuizTopic(quiz.getTopicString()));
		text.append(_getQuestionNumAndQuestion(question));
		text.append(report.getReportDesc());
		text.append("\r\n");

		emailService.sendSimpleMessage(to, from, subject.toString(), text.toString());

		return reportSentJson;
	}
}
