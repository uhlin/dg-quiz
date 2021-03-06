package com.eq.house;

import java.util.LinkedList;
import java.util.List;

public class QuestionAndAnswer {
	private Question question;
	private Answer answer;

	public QuestionAndAnswer() {
		this.question = null;
		this.answer = null;
	}

	public QuestionAndAnswer(Question question, Answer answer) {
		this.question = question;
		this.answer = answer;
	}

	public List<String> getListOfAnswers() {
		List<String> list = new LinkedList<String>();

		if (answer.getOpt1Checked() == true)
			list.add(new String("A"));
		if (answer.getOpt2Checked() == true)
			list.add(new String("B"));
		if (answer.getOpt3Checked() == true)
			list.add(new String("C"));
		if (answer.getOpt4Checked() == true)
			list.add(new String("D"));

		if (answer.getAnsAlt() == 6) {
			if (answer.getOpt5Checked() == true)
				list.add(new String("E"));
			if (answer.getOpt6Checked() == true)
				list.add(new String("F"));
		}

		return list;
	}

	public List<OptionAndText> getListOfRightAnswers() {
		List<OptionAndText> list = new LinkedList<OptionAndText>();

		switch (question.getqType()) {
		case Text: {
			if (question.getqText().getOpt1Right() == 1)
				list.add(new OptionAndText("A", question.getqText().getOpt1Text()));
			if (question.getqText().getOpt2Right() == 1)
				list.add(new OptionAndText("B", question.getqText().getOpt2Text()));
			if (question.getqText().getOpt3Right() == 1)
				list.add(new OptionAndText("C", question.getqText().getOpt3Text()));
			if (question.getqText().getOpt4Right() == 1)
				list.add(new OptionAndText("D", question.getqText().getOpt4Text()));


			if (question.getqText().haveSixOptions()) {
				if (question.getqText().getOpt5Right() == 1)
					list.add(new OptionAndText("E", question.getqText().getOpt5Text()));
				if (question.getqText().getOpt6Right() == 1)
					list.add(new OptionAndText("F", question.getqText().getOpt6Text()));
			}
			return list;
		} /* ----- Text ----- */
		case Sound: {
			if (question.getqSound().getOpt1Right() == 1)
				list.add(new OptionAndText("A", question.getqSound().getOpt1Text()));
			if (question.getqSound().getOpt2Right() == 1)
				list.add(new OptionAndText("B", question.getqSound().getOpt2Text()));
			if (question.getqSound().getOpt3Right() == 1)
				list.add(new OptionAndText("C", question.getqSound().getOpt3Text()));
			if (question.getqSound().getOpt4Right() == 1)
				list.add(new OptionAndText("D", question.getqSound().getOpt4Text()));


			if (question.getqSound().haveSixOptions()) {
				if (question.getqSound().getOpt5Right() == 1)
					list.add(new OptionAndText("E", question.getqSound().getOpt5Text()));
				if (question.getqSound().getOpt6Right() == 1)
					list.add(new OptionAndText("F", question.getqSound().getOpt6Text()));
			}
			return list;
		} /* ----- Sound ----- */
		case Image: {
			if (question.getqImage().getOpt1Right() == 1)
				list.add(new OptionAndText("A", question.getqImage().getOpt1Text()));
			if (question.getqImage().getOpt2Right() == 1)
				list.add(new OptionAndText("B", question.getqImage().getOpt2Text()));
			if (question.getqImage().getOpt3Right() == 1)
				list.add(new OptionAndText("C", question.getqImage().getOpt3Text()));
			if (question.getqImage().getOpt4Right() == 1)
				list.add(new OptionAndText("D", question.getqImage().getOpt4Text()));


			if (question.getqImage().haveSixOptions()) {
				if (question.getqImage().getOpt5Right() == 1)
					list.add(new OptionAndText("E", question.getqImage().getOpt5Text()));
				if (question.getqImage().getOpt6Right() == 1)
					list.add(new OptionAndText("F", question.getqImage().getOpt6Text()));
			}
			return list;
		} /* ----- Image ----- */
		default:
			break;
		} /* switch block */

		System.err.println("getListOfRightAnswers: error: " +
				"cannot determine question type");
		return list;
	}

	public Integer getQuestionNum() {
		switch (question.getqType()) {
		case Text:
			return question.getqText().getQuestionNum();
		case Sound:
			return question.getqSound().getQuestionNum();
		case Image:
			return question.getqImage().getQuestionNum();
		default:
			break;
		}

		System.err.println("getQuestionNum: error: " +
				"cannot determine question type");
		return (-1);
	}

	public String getQuestionText() {
		switch (question.getqType()) {
		case Text:
			return question.getqText().getQuestion();
		case Sound:
			return question.getqSound().getQuestion();
		case Image:
			return question.getqImage().getQuestion();
		default:
			break;
		}

		System.err.println("getQuestionText: error: " +
				"cannot determine question type");
		return "";
	}

	public Boolean hasTwoOrMoreAnswers() {
		return (getListOfRightAnswers().size() >= 2 ? true : false);
	}

	/*
	 * BEHAVIOR: If a question has multiple answers that are
	 * correct, ALL of them must be checked in order to get right
	 * on the question.
	 */
	public Boolean isRightAnswer() {
		List<OptionAndText> rightList = getListOfRightAnswers();
		List<String> playerAnswers = getListOfAnswers();

		if (rightList.size() != playerAnswers.size()) /* mismatch */
			return false;

		for (int i = 0; i < rightList.size(); i ++) {
			final String str1 = rightList.get(i).getOpt();
			final String str2 = playerAnswers.get(i);

			if (! str1.equals(str2))
				return false;
		}

		return true;
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}


	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
