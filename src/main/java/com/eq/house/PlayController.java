package com.eq.house;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Autowired
	AnswerRepo answerRepo;

	private Quiz getQuizByUniqueId(final String id) {
		if (id == null)
			return null;

		Iterable<Quiz> all = repo.findAll();

		for (Quiz quiz : all) {
			if (quiz.getUniqueId().equals(id))
				return quiz;
		}

		return null;
	}

	@RequestMapping("/quizzes/{quizId}/{binaryName}")
	@ResponseBody
	public final byte[] getBinary(
			@PathVariable(name = "quizId") String quizId,
			@PathVariable(name = "binaryName") String binaryName) {
		if (quizId == null || binaryName == null || quizId.equals("") || binaryName.equals("")) {
			System.err.println("getBinary: error: invalid arguments");
			return "".getBytes();
		} else if (!binaryName.startsWith("question")) {
			System.err.println("getBinary: error: unexpected start of binaryName");
			return "".getBytes();
		}

		File file = new File("quizzes/" + quizId + "/" + binaryName);

		try {
			return Files.readAllBytes(file.toPath());
		} catch (Exception ex) {
			System.err.println("getBinary: error: " + ex.getMessage());
		}

		return "".getBytes();
	}

	/* -------------------- Play functionality -------------------- */

	private Question getQuestion(final String quizId, final Integer questionNum) {
		if (quizId == null || questionNum == null || quizId.equals("")) {
			System.err.println("getQuestion: error: invalid arguments");
			return null;
		}

		Question question = null;
		Iterable<QuestionText> allText = questionTextRepo.findAll();
		Iterable<QuestionSound> allSound = questionSoundRepo.findAll();
		Iterable<QuestionImage> allImage = questionImageRepo.findAll();

		for (QuestionText qText : allText) {
			if (qText.getQuestionNum() == questionNum && qText.getQuizId().equals(quizId)) {
				question = new Question(QuestionType.Text);
				question.setqText(qText);
				return question;
			}
		}

		for (QuestionSound qSound : allSound) {
			if (qSound.getQuestionNum() == questionNum && qSound.getQuizId().equals(quizId)) {
				question = new Question(QuestionType.Sound);
				question.setqSound(qSound);
				return question;
			}
		}

		for (QuestionImage qImage : allImage) {
			if (qImage.getQuestionNum() == questionNum && qImage.getQuizId().equals(quizId)) {
				question = new Question(QuestionType.Image);
				question.setqImage(qImage);
				return question;
			}
		}

		/* not found! */
		System.err.println("----- getQuestion: -----");
		System.err.println("quizId:      " + quizId);
		System.err.println("questionNum: " + questionNum);
		System.err.println("CANNOT FIND QUESTION!!!");
		return null;
	}

	private Answer getAnswer(
			final String playerId,
			final String quizId,
			final Integer questionNum) {
		List<Answer> allAnswers = answerRepo.findByPlayerId(playerId);

		for (Answer a : allAnswers) {
			if (a.getPlayerId().equals(playerId) &&
					a.getQuizId().equals(quizId) &&
					a.getQuestionNum() == questionNum)
				return a;
		}

		System.err.println("getAnswer: error: cannot find answer");
		return null;
	}

	public List<Question> getAllQuestionsForQuiz(final String quizId) {
		/* FIXME: Investigate null checking */
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

	private List<QuestionAndAnswer> getAllQna(
			final String quizId,
			final String playerId) {
		Quiz quiz = null;

		if (quizId == null || playerId == null || quizId.isEmpty() || playerId.isEmpty()) {
			System.err.println("getAllQna: error: invalid arguments");
			return null;
		} else if ((quiz = getQuizByUniqueId(quizId)) == null) {
			System.err.println("getAllQna: error: cannot find quiz");
			return null;
		}

		List<QuestionAndAnswer> qnaList = new LinkedList<QuestionAndAnswer>();

		for (Integer i = 1; i <= quiz.getNumQuestions(); i ++) {
			Question question = getQuestion(quizId, i);
			Answer   answer   = getAnswer(playerId, quizId, i);

			if (question == null || answer == null)
				return null;

			QuestionAndAnswer qna = new QuestionAndAnswer(question, answer);
			qnaList.add(qna);
		}

		return qnaList;
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

		model.addAttribute("playerId", UUID.randomUUID().toString());
		model.addAttribute("quiz", quiz);
		model.addAttribute("questionNum", 1);

		return "playQuiz";
	}

	@RequestMapping("/askQuestion")
	public String askQuestion(
			Model model,
			@RequestParam(name = "playerId", required = true) String playerId,
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

		final String src1 = "quizzes/" + quizId + "/question" + questionNum;
		final String src2 = "/quizzes/" + quizId + "/question" + questionNum;

		model.addAttribute("playerId", playerId);
		model.addAttribute("quiz", quiz);
		model.addAttribute("binary", src2);

		File file = null;
		FileOutputStream stream = null;

		switch (question.getqType()) {
		case Text:
			model.addAttribute("question", question.getqText());
			return "askTextQuestion";
		case Sound: {
			try {
				file = new File(src1);
				stream = new FileOutputStream(file);
				stream.getChannel().truncate(0);
				stream.write(question.getqSound().getSoundFileDecompressed());
			} catch (Exception ex) {
				System.err.println("askQuestion: Sound: warning: " + ex.getMessage());
			} finally {
				try {
					if (stream != null)
						stream.close();
				} catch (Exception ex) {
					System.err.println("** cannot close stream");
					System.err.println("askQuestion: Sound: warning: " + ex.getMessage());
				}
			}

			model.addAttribute("question", question.getqSound());
			return "askSoundQuestion";
		} /* ===== Sound ===== */
		case Image: {
			try {
				file = new File(src1);
				stream = new FileOutputStream(file);
				stream.getChannel().truncate(0);
				stream.write(question.getqImage().getImageFileDecompressed());
			} catch (Exception ex) {
				System.err.println("askQuestion: Image: warning: " + ex.getMessage());
			} finally {
				try {
					if (stream != null)
						stream.close();
				} catch (Exception ex) {
					System.err.println("** cannot close stream");
					System.err.println("askQuestion: Image: warning: " + ex.getMessage());
				}
			}

			model.addAttribute("question", question.getqImage());
			return "askImageQuestion";
		} /* ===== Image ===== */
		case None:
			break;
		}

		model.addAttribute("errorMsg", "askQuestion: fatal: cannot determine question type");
		return "error";
	}

	@PostMapping("/answerQuestion")
	public String answerQuestion(
			@Valid Answer answer,
			RedirectAttributes attr,
			BindingResult bindingResult,
			@RequestParam(name = "playerId", required = true) String playerId,
			@RequestParam(name = "quizId", required = true) String quizId,
			@RequestParam(name = "questionNum", required = true) Integer questionNum) {
		Quiz quiz = null;
		Question question = null;

		if ((quiz = getQuizByUniqueId(quizId)) == null) {
			attr.addAttribute("errorMsg", "answerQuestion: fatal: cannot find quiz");
			return "redirect:/error";
		} else if ((question = getQuestion(quizId, questionNum)) == null) {
			attr.addAttribute("errorMsg", "answerQuestion: fatal: cannot find question");
			return "redirect:/error";
		} else if (questionNum < 1 || questionNum > quiz.getNumQuestions()) {
			attr.addAttribute("errorMsg", "answerQuestion: fatal: question number out of range!");
			return "redirect:/error";
		}

		switch (question.getqType()) {
		case Text:
			answer.setAnsAlt(question.getqText().haveSixOptions() ? 6 : 4);
			break;
		case Sound:
			answer.setAnsAlt(question.getqSound().haveSixOptions() ? 6 : 4);
			break;
		case Image:
			answer.setAnsAlt(question.getqImage().haveSixOptions() ? 6 : 4);
			break;
		default:
			attr.addAttribute("errorMsg", "answerQuestion: fatal: cannot determine question type");
			return "redirect:/error";
		}

		answer.setPlayerId(playerId);
		answer.setQuizId(quizId);
		answer.setQuestionNum(questionNum);
		answer.outputObject();
		System.out.println("Saving answer...");
		answerRepo.save(answer);

		attr.addAttribute("playerId", playerId);
		attr.addAttribute("quizId", quizId);

		if (questionNum == quiz.getNumQuestions()) {
			return "redirect:/finishedQuiz";
		}

		/* Next question */
		attr.addAttribute("questionNum", (questionNum + 1));
		return "redirect:/askQuestion";
	}

	@RequestMapping("/exitQuiz")
	@Transactional
	public String exitQuiz(
			Model model,
			@RequestParam(name = "playerId", required = true) String playerId,
			@RequestParam(name = "quizId", required = true) String quizId) {
		List<Answer> answers = answerRepo.findByPlayerId(playerId);
		Quiz quiz = null;

		if (answers == null) {
			model.addAttribute("errorMsg", "exitQuiz: fatal: cannot find answers");
			return "error";
		} else if (answers.size() == 0) {
			model.addAttribute("errorMsg", "exitQuiz: fatal: zero answers");
			return "error";
		} else if ((quiz = getQuizByUniqueId(quizId)) == null) {
			model.addAttribute("errorMsg", "exitQuiz: fatal: no such quiz");
			return "error";
		}

		System.out.println("----- exitQuiz: -----");
		System.out.println("answers:    " + answers.size());
		System.out.println("pre count:  " + answerRepo.count());
		answerRepo.deleteByPlayerId(playerId);
		System.out.println("post count: " + answerRepo.count());

		model.addAttribute("quiz", quiz);
		return "exitQuiz";
	}

	@RequestMapping("/finishedQuiz")
	public String finishedQuiz(
			Model model,
			@RequestParam(name = "playerId", required = true) String playerId,
			@RequestParam(name = "quizId", required = true) String quizId) {
		Quiz quiz = null;
		List<QuestionAndAnswer> qna = null;

		if (playerId == null || quizId == null || playerId.isEmpty() || quizId.isEmpty()) {
			model.addAttribute("errorMsg", "finishedQuiz: fatal: invalid arguments");
			return "error";
		} else if ((quiz = getQuizByUniqueId(quizId)) == null) {
			model.addAttribute("errorMsg", "finishedQuiz: fatal: no such quiz");
			return "error";
		} else if ((qna = getAllQna(quizId, playerId)) == null) {
			model.addAttribute("errorMsg", "finishedQuiz: fatal: " +
					"cannot retrieve questions and answers");
			return "error";
		}

		model.addAttribute("quiz", quiz);
		model.addAttribute("qna", qna);

		return "finishedQuiz";
	}
}
