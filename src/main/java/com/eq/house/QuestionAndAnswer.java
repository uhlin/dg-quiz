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

	public Boolean hasTwoOrMoreAnswers() {
		return (getListOfRightAnswers().size() >= 2 ? true : false);
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
