package com.eq.house;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
	public static final long soundFileMaxBytesUncompressed = 4194304; // 4 MB
	public static final long imageFileMaxBytesUncompressed = 2097152; // 2 MB

	private static final int quizTitleMaxLen = 200;
	private static final int questionMaxLen = 200;
	private static final int optTextMaxLen = 200;

	@Autowired
	PlayController pc;

	@Autowired
	QuizRepo repo;

	@Autowired
	QuestionTextRepo questionTextRepo;

	@Autowired
	QuestionSoundRepo questionSoundRepo;

	@Autowired
	QuestionImageRepo questionImageRepo;

	private List<Quiz> getFilteredQuizzes(Topic byTopic, Language byLang) {
		Iterable<Quiz> all = repo.findAll();
		List<Quiz> f = new LinkedList<Quiz>();

		for (Quiz quiz : all) {
			if (quiz.getTopic() == byTopic && quiz.getLang() == byLang)
				f.add(quiz);
		}

		return f;
	}

	private List<Quiz> getFilteredQuizzesByTopic(Topic byTopic) {
		Iterable<Quiz> all = repo.findAll();
		List<Quiz> f = new LinkedList<Quiz>();

		for (Quiz quiz : all) {
			if (quiz.getTopic() == byTopic)
				f.add(quiz);
		}

		return f;
	}

	private List<Quiz> getFilteredQuizzesByLang(Language byLang) {
		Iterable<Quiz> all = repo.findAll();
		List<Quiz> f = new LinkedList<Quiz>();

		for (Quiz quiz : all) {
			if (quiz.getLang() == byLang)
				f.add(quiz);
		}

		return f;
	}

	private Quiz getQuiz(String title, Integer topic, Integer lang) {
		if (title == null || topic == null || lang == null)
			return null;

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

	@RequestMapping("/")
	public String index(
			Model model,
			@RequestParam(name = "quizTopic", required = false, defaultValue = "255") Integer topic,
			@RequestParam(name = "quizLang",  required = false, defaultValue = "255") Integer lang) {
		String s1 = "";
		String s2 = "";

		model.addAttribute("completeNumberOfQuizzes", repo.count());

		if (repo.count() > 0) {
			final Integer allValue = 255;

			if (topic.equals(allValue) && lang.equals(allValue)) {
				model.addAttribute("db", repo.findAll());
				model.addAttribute("dbCount", repo.count());
			} else if (!topic.equals(allValue) && lang.equals(allValue)) {
				List<Quiz> f = getFilteredQuizzesByTopic(Utilities.intToTopic(topic));

				model.addAttribute("db", f);
				model.addAttribute("dbCount", f.size());
			} else if (topic.equals(allValue) && !lang.equals(allValue)) {
				List<Quiz> f = getFilteredQuizzesByLang(Utilities.intToLanguage(lang));

				model.addAttribute("db", f);
				model.addAttribute("dbCount", f.size());
			} else {
				s1 = Utilities.topicToString(Utilities.intToTopic(topic));
				s2 = Utilities.languageToString(Utilities.intToLanguage(lang));

				System.out.println("MainController: index: topic = " + s1);
				System.out.println("MainController: index: lang  = " + s2);

				List<Quiz> f = getFilteredQuizzes(
						Utilities.intToTopic(topic),
						Utilities.intToLanguage(lang));

				model.addAttribute("db", f);
				model.addAttribute("dbCount", f.size());
			}
		} else {
			model.addAttribute("db", null);
			model.addAttribute("dbCount", 0);
		}

		return "index";
	}

	@GetMapping("/createQuiz")
	public String createQuiz() {
		return "createQuiz";
	}

	@PostMapping("/createQuiz")
	public String createQuizBegin(
			Model model,
			@RequestParam(name = "quizTitle") String title,
			@RequestParam(name = "quizTopic") Integer topic,
			@RequestParam(name = "quizLang")  Integer lang,
			@RequestParam(name = "quizNumQuestionsGoal") Integer goal) {
		final Integer goalMinValue = 2;
		final Integer goalMaxValue = 30;

		if (title == null || topic == null || lang == null || goal == null) {
			model.addAttribute("errorMsg", "createQuizBegin: error: invalid arguments");
			return "error";
		} else if (title.isEmpty()) {
			model.addAttribute("errorMsg", "createQuizBegin: error: empty title");
			return "error";
		} else if (title.length() > quizTitleMaxLen) {
			model.addAttribute("errorMsg", "createQuizBegin: error: title too long");
			return "error";
		} else if (goal < goalMinValue || goal > goalMaxValue) {
			model.addAttribute("errorMsg", "createQuizBegin: error: " +
					"questions goal number out of range!");
			return "error";
		} else if (getQuiz(title, topic, lang) != null) {
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
			quiz.setNumQuestionsGoal(goal);
			repo.save(quiz);
		}

		System.out.println("quizTitle: " + title);
		System.out.println("quizTopic: " + topic);
		System.out.println("quizLang:  " + lang);
		System.out.println("quizNumQuestionsGoal: " + goal);

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("quiz", quiz);

		final Integer questionTypeDefault = 0;
		final Integer questionNum = quiz.getNumQuestions() + 1;
		final Integer numAnswersDefault = 4;

		model.addAttribute("questionType", questionTypeDefault);
		model.addAttribute("questionNum", questionNum);
		model.addAttribute("numAnswers", numAnswersDefault);

		return "addTextQuestion";
	}

	@PostMapping("/addQuestion")
	public String addQuestion(
			Model model,
			@RequestParam(name = "quizTitle",    required = true) String  title,
			@RequestParam(name = "quizTopic",    required = true) Integer topic,
			@RequestParam(name = "quizLang",     required = true) Integer lang,
			@RequestParam(name = "questionType", required = false) Integer type,
			@RequestParam(name = "questionNum",  required = false) Integer questionNum,
			@RequestParam(name = "numAnswers",   required = false) Integer num) {
		Quiz quiz = null;
		Question question = null;

		if (title == null || topic == null || lang == null) {
			model.addAttribute("errorMsg", "addQuestion: fatal: invalid arguments");
			return "error";
		} else if (title.isEmpty()) {
			model.addAttribute("errorMsg", "addQuestion: fatal: empty quiz title");
			return "error";
		} else if (title.length() > quizTitleMaxLen) {
			model.addAttribute("errorMsg", "addQuestion: fatal: title too long");
			return "error";
		} else if ((quiz = getQuiz(title, topic, lang)) == null) {
			model.addAttribute("errorMsg", "addQuestion: fatal: no such quiz");
			return "error";
		} else if (quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "addQuestion: fatal: " +
					"already completed adding questions for that quiz");
			return "error";
		} else if (questionNum != null) {
			question = pc.getQuestion(quiz.getUniqueId(), questionNum);
		}

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("quiz", quiz);

		if (question != null) {
			model.addAttribute("questionNum", questionNum);

			switch (question.getqType()) {
			case Text:
				type = 0;
				model.addAttribute("numAnswers", question.getqText().haveSixOptions() ? 6 : 4);
				break;
			case Sound:
				type = 1;
				model.addAttribute("numAnswers", question.getqSound().haveSixOptions() ? 6 : 4);
				break;
			case Image:
				type = 2;
				model.addAttribute("numAnswers", question.getqImage().haveSixOptions() ? 6 : 4);
				break;
			case None:
				type = 3;
				break;
			}
		} else {
			type = 0;
			model.addAttribute("questionNum", (quiz.getNumQuestions() + 1));
			model.addAttribute("numAnswers", (num != null ? num : 4));
		}

		System.out.println("---------- addQuestion: ----------");
		System.out.println("questionType: " + type);
		System.out.println("quizTitle:    " + title);
		System.out.println("quizTopic:    " + topic);
		System.out.println("quizLang:     " + lang);
		System.out.println("numAnswers:   " + num);

		switch (Utilities.intToQuestionType(type)) {
		case Text:
			if (question != null)
				model.addAttribute("question", question.getqText());
			return "addTextQuestion";
		case Sound:
			if (question != null)
				model.addAttribute("question", question.getqSound());
			return "addSoundQuestion";
		case Image:
			if (question != null)
				model.addAttribute("question", question.getqImage());
			return "addImageQuestion";
		case None:
			break;
		}

		System.err.println("addQuestion: fatal: cannot determine question type!");
		return "error";
	}


	@PostMapping("/addTextQuestion")
	@Transactional
	public String addTextQuestion(
			@Valid QuestionText qText,
			Model model,
			BindingResult bindingResult,
			@RequestParam(name = "quizTitle")  String title,
			@RequestParam(name = "quizTopic")  Integer topic,
			@RequestParam(name = "quizLang")   Integer lang,
			@RequestParam(name = "numAnswers") Integer num) {
		Quiz quiz = null;

		if (title == null || topic == null || lang == null || num == null) {
			model.addAttribute("errorMsg", "addTextQuestion: fatal: invalid arguments");
			return "error";
		} else if (title.isEmpty()) {
			model.addAttribute("errorMsg", "addTextQuestion: fatal: empty quiz title");
			return "error";
		} else if ((quiz = getQuiz(title, topic, lang)) == null) {
			model.addAttribute("errorMsg", "addTextQuestion: fatal: no such quiz");
			return "error";
		} else if (quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "addTextQuestion: fatal: " +
					"already completed adding questions for that quiz");
			return "error";
		}

		Integer questionNum = quiz.getNumQuestions() + 1;
		model.addAttribute("questionNum", questionNum);

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("numAnswers", num);

		if (qText.getQuestion().equals("")) {
			model.addAttribute("errorMsg", "Empty question");
			return "addTextQuestion";
		} else if (qText.getQuestion().length() > questionMaxLen) {
			model.addAttribute("errorMsg", "Question too long");
			return "addTextQuestion";
		} else if (qText.getOpt1Text().equals("") ||
				qText.getOpt2Text().equals("") ||
				qText.getOpt3Text().equals("") ||
				qText.getOpt4Text().equals("")) {
			model.addAttribute("errorMsg", "Empty option text A-D");
			return "addTextQuestion";
		} else if (num == 6 &&
				(qText.getOpt5Text().equals("") || qText.getOpt6Text().equals(""))) {
			model.addAttribute("errorMsg", "Empty option text E-F");
			return "addTextQuestion";
		} else if (qText.getOpt1Text().length() > optTextMaxLen ||
				qText.getOpt2Text().length() > optTextMaxLen ||
				qText.getOpt3Text().length() > optTextMaxLen ||
				qText.getOpt4Text().length() > optTextMaxLen ||
				qText.getOpt5Text().length() > optTextMaxLen ||
				qText.getOpt6Text().length() > optTextMaxLen) {
			model.addAttribute("errorMsg", "Option text too long");
			return "addTextQuestion";
		} else if (bindingResult.hasErrors()) {
			return "addTextQuestion";
		}

		qText.setQuizId(quiz.getUniqueId());
		qText.setQuestionNum(quiz.increaseNumQuestions());
		qText.outputObject();
		questionTextRepo.save(qText);

		if (quiz.getNumQuestions().equals(quiz.getNumQuestionsGoal()))
			return doneWithQuestions(model, title, topic, lang);

		questionNum ++;
		model.addAttribute("questionNum", questionNum);

		return "addTextQuestion";
	}


	@PostMapping("/addSoundQuestion")
	@Transactional
	public String addSoundQuestion(
			@Valid QuestionSound qSound,
			Model model,
			BindingResult bindingResult,
			@RequestParam(name = "soundFile")  MultipartFile mpFile,
			@RequestParam(name = "quizTitle")  String title,
			@RequestParam(name = "quizTopic")  Integer topic,
			@RequestParam(name = "quizLang")   Integer lang,
			@RequestParam(name = "numAnswers") Integer num) {
		Quiz quiz = null;

		if (title == null || topic == null || lang == null || num == null) {
			model.addAttribute("errorMsg", "addSoundQuestion: fatal: invalid arguments");
			return "error";
		} else if (title.isEmpty()) {
			model.addAttribute("errorMsg", "addSoundQuestion: fatal: empty quiz title");
			return "error";
		} else if ((quiz = getQuiz(title, topic, lang)) == null) {
			model.addAttribute("errorMsg", "addSoundQuestion: fatal: no such quiz");
			return "error";
		} else if (quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "addSoundQuestion: fatal: " +
					"already completed adding questions for that quiz");
			return "error";
		}

		Integer questionNum = quiz.getNumQuestions() + 1;
		model.addAttribute("questionNum", questionNum);

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("numAnswers", num);

		if (mpFile == null || mpFile.getSize() == 0) {
			model.addAttribute("errorMsg", "Sound file null or zero size");
			return "addSoundQuestion";
		} else if (mpFile != null && mpFile.getSize() > soundFileMaxBytesUncompressed) {
			model.addAttribute("errorMsg", "File too large! " + Utilities.getSoundFileMaxSizeString());
			return "addSoundQuestion";
		} else if (qSound.getQuestion().equals("")) {
			model.addAttribute("errorMsg", "Empty question");
			return "addSoundQuestion";
		} else if (qSound.getQuestion().length() > questionMaxLen) {
			model.addAttribute("errorMsg", "Question too long");
			return "addSoundQuestion";
		} else if (qSound.getOpt1Text().equals("") ||
				qSound.getOpt2Text().equals("") ||
				qSound.getOpt3Text().equals("") ||
				qSound.getOpt4Text().equals("")) {
			model.addAttribute("errorMsg", "Empty option text A-D");
			return "addSoundQuestion";
		} else if (num == 6 &&
				(qSound.getOpt5Text().equals("") || qSound.getOpt6Text().equals(""))) {
			model.addAttribute("errorMsg", "Empty option text E-F");
			return "addSoundQuestion";
		} else if (qSound.getOpt1Text().length() > optTextMaxLen ||
				qSound.getOpt2Text().length() > optTextMaxLen ||
				qSound.getOpt3Text().length() > optTextMaxLen ||
				qSound.getOpt4Text().length() > optTextMaxLen ||
				qSound.getOpt5Text().length() > optTextMaxLen ||
				qSound.getOpt6Text().length() > optTextMaxLen) {
			model.addAttribute("errorMsg", "Option text too long");
			return "addSoundQuestion";
		} else if (bindingResult.hasErrors()) {
			return "addSoundQuestion";
		}

		qSound.setQuizId(quiz.getUniqueId());
		qSound.setQuestionNum(quiz.increaseNumQuestions());
		qSound.outputObject();
		questionSoundRepo.save(qSound);

		if (quiz.getNumQuestions().equals(quiz.getNumQuestionsGoal()))
			return doneWithQuestions(model, title, topic, lang);

		questionNum ++;
		model.addAttribute("questionNum", questionNum);

		return "addTextQuestion";
	}


	@PostMapping("/addImageQuestion")
	@Transactional
	public String addImageQuestion(
			@Valid QuestionImage qImage,
			Model model,
			BindingResult bindingResult,
			@RequestParam(name = "imageFile")  MultipartFile mpFile,
			@RequestParam(name = "quizTitle")  String title,
			@RequestParam(name = "quizTopic")  Integer topic,
			@RequestParam(name = "quizLang")   Integer lang,
			@RequestParam(name = "numAnswers") Integer num) {
		Quiz quiz = null;

		if (title == null || topic == null || lang == null || num == null) {
			model.addAttribute("errorMsg", "addImageQuestion: fatal: invalid arguments");
			return "error";
		} else if (title.isEmpty()) {
			model.addAttribute("errorMsg", "addImageQuestion: fatal: empty quiz title");
			return "error";
		} else if ((quiz = getQuiz(title, topic, lang)) == null) {
			model.addAttribute("errorMsg", "addImageQuestion: fatal: no such quiz");
			return "error";
		} else if (quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "addImageQuestion: fatal: " +
					"already completed adding questions for that quiz");
			return "error";
		}

		Integer questionNum = quiz.getNumQuestions() + 1;
		model.addAttribute("questionNum", questionNum);

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", topic);
		model.addAttribute("quizLang", lang);
		model.addAttribute("numAnswers", num);

		if (mpFile == null || mpFile.getSize() == 0) {
			model.addAttribute("errorMsg", "Image file null or zero size");
			return "addImageQuestion";
		} else if (mpFile != null && mpFile.getSize() > imageFileMaxBytesUncompressed) {
			model.addAttribute("errorMsg", "File too large! " + Utilities.getImageFileMaxSizeString());
			return "addImageQuestion";
		} else if (qImage.getQuestion().equals("")) {
			model.addAttribute("errorMsg", "Empty question");
			return "addImageQuestion";
		} else if (qImage.getQuestion().length() > questionMaxLen) {
			model.addAttribute("errorMsg", "Question too long");
			return "addImageQuestion";
		} else if (qImage.getOpt1Text().equals("") ||
				qImage.getOpt2Text().equals("") ||
				qImage.getOpt3Text().equals("") ||
				qImage.getOpt4Text().equals("")) {
			model.addAttribute("errorMsg", "Empty option text A-D");
			return "addImageQuestion";
		} else if (num == 6 &&
				(qImage.getOpt5Text().equals("") || qImage.getOpt6Text().equals(""))) {
			model.addAttribute("errorMsg", "Empty option text E-F");
			return "addImageQuestion";
		} else if (qImage.getOpt1Text().length() > optTextMaxLen ||
				qImage.getOpt2Text().length() > optTextMaxLen ||
				qImage.getOpt3Text().length() > optTextMaxLen ||
				qImage.getOpt4Text().length() > optTextMaxLen ||
				qImage.getOpt5Text().length() > optTextMaxLen ||
				qImage.getOpt6Text().length() > optTextMaxLen) {
			model.addAttribute("errorMsg", "Option text too long");
			return "addImageQuestion";
		} else if (bindingResult.hasErrors()) {
			return "addImageQuestion";
		}

		qImage.setQuizId(quiz.getUniqueId());
		qImage.setQuestionNum(quiz.increaseNumQuestions());
		qImage.outputObject();
		questionImageRepo.save(qImage);

		if (quiz.getNumQuestions().equals(quiz.getNumQuestionsGoal()))
			return doneWithQuestions(model, title, topic, lang);

		questionNum ++;
		model.addAttribute("questionNum", questionNum);

		return "addTextQuestion";
	}


	@PostMapping("/doneWithQuestions")
	public String doneWithQuestions(
			Model model,
			@RequestParam(name = "quizTitle", required = true) String  title,
			@RequestParam(name = "quizTopic", required = true) Integer topic,
			@RequestParam(name = "quizLang",  required = true) Integer lang) {
		Quiz quiz = getQuiz(title, topic, lang);

		if (quiz == null) {
			model.addAttribute("errorMsg", "Cannot find quiz!");
			return "error";
		} else if (quiz.getNumQuestions() == 0) {
			repo.delete(quiz); // delete it
			model.addAttribute("errorMsg", "Zero questions!");
			return "error";
		}

		File file = new File("quizzes/" + quiz.getUniqueId());

		if (file.exists()) {
			repo.delete(quiz);
			model.addAttribute("errorMsg", "doneWithQuestions: fatal: " +
					"storage for quiz already exists");
			return "error";
		} else {
			if (!file.mkdirs()) {
				repo.delete(quiz);
				model.addAttribute("errorMsg", "doneWithQuestions: fatal: " +
						"cannot create harddisk storage for quiz");
				return "error";
			}
		}

		quiz.setCompletedAddingQuestions(true);
		repo.save(quiz);

		model.addAttribute("quizTitle", title);
		model.addAttribute("quizTopic", Utilities.topicToString(Utilities.intToTopic(topic)));
		model.addAttribute("quizLang", Utilities.languageToString(Utilities.intToLanguage(lang)));
		model.addAttribute("numQuestions", quiz.getNumQuestions());
		return "doneWithQuestions";
	}

	@PostMapping("/abortCreateQuiz")
	@Transactional
	public String abortCreateQuiz(
			Model model,
			@RequestParam(name = "quizTitle", required = true) String  title,
			@RequestParam(name = "quizTopic", required = true) Integer topic,
			@RequestParam(name = "quizLang",  required = true) Integer lang) {
		Quiz quiz = getQuiz(title, topic, lang);

		if (quiz == null) {
			model.addAttribute("errorMsg", "Cannot find quiz!");
			return "error";
		} else if (quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "Cannot cancel creation of THAT quiz!");
			return "error";
		}

		questionTextRepo.deleteByQuizId(quiz.getUniqueId());
		questionSoundRepo.deleteByQuizId(quiz.getUniqueId());
		questionImageRepo.deleteByQuizId(quiz.getUniqueId());

		repo.delete(quiz);

		return "abortCreateQuiz";
	}
}
