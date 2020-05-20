package com.eq.house;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	@Autowired
	QuizRepo repo;

	@Autowired
	QuestionTextRepo questionTextRepo;

	@Autowired
	QuestionSoundRepo questionSoundRepo;

	@Autowired
	QuestionImageRepo questionImageRepo;

	public QuizRepo quizRepoExported() {
		return repo;
	}

	@RequestMapping("/")
	public String index(
			Model model,
			@RequestParam(name = "quizTopic", required = false, defaultValue = "-1") Integer topic,
			@RequestParam(name = "quizLang",  required = false, defaultValue = "-1") Integer lang) {
		String s1 = "";
		String s2 = "";

		model.addAttribute("completeNumberOfQuizzes", repo.count());

		if (repo.count() > 0) {
			if (topic != -1 && lang != -1) {
				s1 = Utilities.topicToString(Utilities.intToTopic(topic));
				s2 = Utilities.languageToString(Utilities.intToLanguage(lang));

				System.out.println("MainController: index: topic = " + s1);
				System.out.println("MainController: index: lang  = " + s2);

				List<Quiz> f =
						getFilteredQuizzes(Utilities.intToTopic(topic), Utilities.intToLanguage(lang));

				model.addAttribute("db", f);
				model.addAttribute("dbCount", f.size());
			} else {
				model.addAttribute("db", repo.findAll());
				model.addAttribute("dbCount", repo.count());
			}
		} else {
			model.addAttribute("db", null);
			model.addAttribute("dbCount", 0);
		}

		return "index";
	}

	public List<Quiz> getFilteredQuizzes(Topic byTopic, Language byLang) {
		Iterable<Quiz> all = repo.findAll();
		List<Quiz> f = new LinkedList<Quiz>();

		for (Quiz quiz : all) {
			if (quiz.getTopic() == byTopic && quiz.getLang() == byLang)
				f.add(quiz);
		}

		return f;
	}

	/*
	 * Get quiz id (by title, topic and language)
	 */
	public Long getQuizId(String title, Integer topic, Integer lang) {
		Topic _topic = Utilities.intToTopic(topic);
		Language _lang = Utilities.intToLanguage(lang);

		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getTitle().equalsIgnoreCase(title) &&
					quiz.getTopic() == _topic &&
					quiz.getLang() == _lang)
				return quiz.getId();
		}

		return Long.valueOf(-1);
	}

	public String getQuizUniqueId(String title, Integer topic, Integer lang) {
		Topic _topic = Utilities.intToTopic(topic);
		Language _lang = Utilities.intToLanguage(lang);

		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getTitle().equalsIgnoreCase(title) &&
					quiz.getTopic() == _topic &&
					quiz.getLang() == _lang)
				return quiz.getUniqueId();
		}

		return "";
	}

	public Quiz getQuiz(String title, Integer topic, Integer lang) {
		Topic _topic = Utilities.intToTopic(topic);
		Language _lang = Utilities.intToLanguage(lang);

		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getTitle().equalsIgnoreCase(title) &&
					quiz.getTopic() == _topic &&
					quiz.getLang() == _lang)
				return quiz;
		}

		return null;
	}

	public Quiz getQuizByUniqueId(final String id) {
		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getUniqueId().equals(id))
				return quiz;
		}

		return null;
	}

	@GetMapping("/createQuiz")
	public String createQuiz() {
		return "createQuiz";
	}

	@PostMapping("/createQuiz")
	public String createQuizBegin(
			Model model,
			@RequestParam(name = "quizTitle") String  title,
			@RequestParam(name = "quizTopic") Integer topic,
			@RequestParam(name = "quizLang")  Integer lang) {
		System.out.println("quizTitle: " + title);
		System.out.println("quizTopic: " + topic);
		System.out.println("quizLang:  " + lang);

		if (getQuiz(title, topic, lang) != null) {
			System.err.println("createQuizBegin: error: quiz already exists");
			return "quizAlreadyExists";
		} else {
			repo.save(new Quiz(title, topic, lang));
		}

		Quiz quiz = null;

		if ((quiz = getQuiz(title, topic, lang)) == null) {
			System.err.println("createQuizBegin: fatal: cannot find saved quiz");
			return "error";
		} else {
			model.addAttribute("quiz", quiz);
		}

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		return "addQuestion";
	}

	@PostMapping("/addQuestion")
	public String addQuestion(
			Model model,
			@RequestParam(name = "questionType", required = true) Integer type,
			@RequestParam(name = "quizTitle",    required = true) String  title,
			@RequestParam(name = "quizTopic",    required = true) Integer topic,
			@RequestParam(name = "quizLang",     required = true) Integer lang,
			@RequestParam(name = "numAnswers",   required = true) Integer num) {
		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("numAnswers", num);

		Quiz quiz = getQuiz(title, topic, lang);
		final Integer questionNum = quiz.getNumQuestions() + 1;
		model.addAttribute("questionNum", questionNum);

		System.out.println("---------- addQuestion: ----------");
		System.out.println("questionType: " + type);
		System.out.println("quizTitle:    " + title);
		System.out.println("quizTopic:    " + topic);
		System.out.println("quizLang:     " + lang);
		System.out.println("numAnswers:   " + num);

		switch (Utilities.intToQuestionType(type)) {
		case Text:
			return "addTextQuestion";
		case Sound:
			return "addSoundQuestion";
		case Image:
			return "addImageQuestion";
		}

		System.err.println("addQuestion: fatal: cannot determine question type!");
		return "error";
	}


	@PostMapping("/addTextQuestion")
	public String addTextQuestion(
			Model model,
			@RequestParam(name = "quizTitle", required = true) String  title,
			@RequestParam(name = "quizTopic", required = true) Integer topic,
			@RequestParam(name = "quizLang",  required = true) Integer lang) {
		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		return "addQuestion";
	}


	@PostMapping("/addSoundQuestion")
	public String addSoundQuestion() {
		return "addQuestion";
	}


	@PostMapping("/addImageQuestion")
	public String addImageQuestion() {
		return "addQuestion";
	}


	@RequestMapping("/doneWithQuestions")
	public String doneWithQuestions() {
		/* TODO: Add code */
		return "doneWithQuestions";
	}
}
