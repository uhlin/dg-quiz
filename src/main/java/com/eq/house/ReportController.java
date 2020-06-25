package com.eq.house;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController {
	private final String reportSentJson = "{\"html\": \"Report Sent!\"}";

	@PostMapping(path = "/reportQuestionError",
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public String reportQuestionErrors(@RequestBody ReportQuestionError report) {
		report.outputObject();
		return reportSentJson;
	}

	@PostMapping(path = "/reportQuestionContent",
			consumes = "application/json",
			produces = "application/json")
	@ResponseBody
	public String reportQuestionContents(@RequestBody ReportQuestionContent report) {
		report.outputObject();
		return reportSentJson;
	}
}