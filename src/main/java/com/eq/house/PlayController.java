package com.eq.house;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {
	@Autowired
	QuizRepo repo;

	@Autowired
	QuestionTextRepo questionTextRepo;

	@Autowired
	QuestionSoundRepo questionSoundRepo;

	@Autowired
	QuestionImageRepo questionImageRepo;

	public Quiz getQuizByUniqueId(final String id) {
		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getUniqueId().equals(id))
				return quiz;
		}

		return null;
	}

	/* -------------------- Play functionality -------------------- */

	public Question getQuestion(final String quizId, final Integer questionNum) {
		Question question = null;
		Iterable<QuestionText> allText = questionTextRepo.findAll();
		Iterable<QuestionSound> allSound = questionSoundRepo.findAll();
		Iterable<QuestionImage> allImage = questionImageRepo.findAll();

		for (QuestionText qText : allText) {
			if (qText.getQuestionNum() == questionNum) {
				question = new Question(QuestionType.Text);
				question.setqText(qText);
				return question;
			}
		}

		for (QuestionSound qSound : allSound) {
			if (qSound.getQuestionNum() == questionNum) {
				question = new Question(QuestionType.Sound);
				question.setqSound(qSound);
				return question;
			}
		}

		for (QuestionImage qImage : allImage) {
			if (qImage.getQuestionNum() == questionNum) {
				question = new Question(QuestionType.Image);
				question.setqImage(qImage);
				return question;
			}
		}

		/* not found! */
		System.err.println("----- getQuestion: -----");
		System.err.println("quizId:      " + quizId);
		System.err.println("questionNum: " + questionNum);
		System.err.println("CANNOT TO FIND QUESTION!!!");
		return null;
	}

	public List<Question> getAllQuestionsForQuiz(final String quizId) {
		Quiz quiz = getQuizByUniqueId(quizId);
		List<Question> questions = new LinkedList<Question>();

		for (Integer i = 1; i <= quiz.getNumQuestions(); i ++) {
			Question q = getQuestion(quizId, i);
			if (q != null)
				questions.add(q);
		}

		if (questions.size() != quiz.getNumQuestions()) {
			System.err.println("----- getAllQuestionsForQuiz -----");
			System.err.println("fatal: size mismatch!");
			return null;
		}

		return questions;
	}

	@GetMapping("/playQuiz")
	public String playQuiz(
			Model model,
			@RequestParam(name = "id", required = true) String id) {
		Quiz quiz = getQuizByUniqueId(id);

		if (quiz == null) {
			model.addAttribute("errorMsg", "Cannot find quiz!");
			return "error";
		} else if (!quiz.getCompletedAddingQuestions()) {
			model.addAttribute("errorMsg", "Did not complete adding questions!");
			return "error";
		} else if (quiz.getNumQuestions() == 0) {
			model.addAttribute("errorMsg", "Zero questions!");
			return "error";
		}

		model.addAttribute("quiz", quiz);
		model.addAttribute("questionNum", 1);

		return "playQuiz";
	}

	@GetMapping("/askQuestion")
	public String askQuestion(
			Model model,
			@RequestParam(name = "quizId", required = true) String quizId,
			@RequestParam(name = "questionNum", required = true) Integer questionNum) {
		Quiz quiz = null;
		Question question = null;

		if ((quiz = getQuizByUniqueId(quizId)) == null) {
			model.addAttribute("errorMsg", "askQuestion: fatal: cannot find quiz");
			return "error";
		} else if ((question = getQuestion(quizId, questionNum)) == null) {
			model.addAttribute("errorMsg", "askQuestion: fatal: cannot find question");
			return "error";
		}

		model.addAttribute("quiz", quiz);
		model.addAttribute("question", question);
		//model.addAttribute("questionNum", questionNum);

		switch (question.getqType()) {
		case Text:
			return "askTextQuestion";
		case Sound:
			return "askSoundQuestion";
		case Image:
			return "askImageQuestion";
		case None:
			break;
		}

		model.addAttribute("errorMsg", "askQuestion: fatal: cannot determine question type");
		return "error";
	}
}
