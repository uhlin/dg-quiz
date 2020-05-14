package com.eq.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	QuizRepo repo;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/createQuiz")
	public String createQuiz() {
		return "createQuiz";
	}

	@PostMapping("/createQuiz")
	public String createQuizBegin(
			@RequestParam(name = "quizTitle") String  title,
			@RequestParam(name = "quizTopic") Integer topic,
			@RequestParam(name = "quizLang")  Integer lang) {
		System.out.println("quizTitle: " + title);
		System.out.println("quizTopic: " + topic);
		System.out.println("quizLang:  " + lang);
		repo.save(new Quiz(title, topic, lang));
		return "addQuestion";
	}
}
