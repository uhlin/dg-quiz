package com.eq.house;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class QuestionImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String quizId;
	private String question;
	private Integer questionNum;

	@Lob
	private byte[] imageFile;

	private String opt1Text;
	private String opt2Text;
	private String opt3Text;
	private String opt4Text;
	private String opt5Text;
	private String opt6Text;

	/*
	 * Option right, yes/no?
	 *   - yes = 1
	 *   - no = 0
	 */
	private Integer opt1Right;
	private Integer opt2Right;
	private Integer opt3Right;
	private Integer opt4Right;
	private Integer opt5Right;
	private Integer opt6Right;

	public QuestionImage() {
		this.quizId = "";
		this.question = "";
		this.questionNum = -1;
		this.imageFile = null;

		this.opt1Text = null;
		this.opt2Text = null;
		this.opt3Text = null;
		this.opt4Text = null;
		this.opt5Text = "";
		this.opt6Text = "";

		this.opt1Right = 0;
		this.opt2Right = 0;
		this.opt3Right = 0;
		this.opt4Right = 0;
		this.opt5Right = -1;
		this.opt6Right = -1;
	}

	public void outputObject() {
		System.out.println("---------- QuestionImage ----------");
		System.out.println("quizId:      " + this.quizId);
		System.out.println("question:    " + this.question);
		System.out.println("questionNum: " + this.questionNum);
		System.out.println("imageFile:   " + this.imageFile.length);

		System.out.println("opt1Text:    " + this.opt1Text);
		System.out.println("opt2Text:    " + this.opt2Text);
		System.out.println("opt3Text:    " + this.opt3Text);
		System.out.println("opt4Text:    " + this.opt4Text);
		System.out.println("opt5Text:    " + this.opt5Text);
		System.out.println("opt6Text:    " + this.opt6Text);

		System.out.println("opt1Right:   " + this.opt1Right);
		System.out.println("opt2Right:   " + this.opt2Right);
		System.out.println("opt3Right:   " + this.opt3Right);
		System.out.println("opt4Right:   " + this.opt4Right);
		System.out.println("opt5Right:   " + this.opt5Right);
		System.out.println("opt6Right:   " + this.opt6Right);
	}

/***************************************************
 *
 * Getters/setters
 *
 ***************************************************/

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}


	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}


	public Integer getQuestionNum() {
		return questionNum;
	}
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}


	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

/***************************************************
 *
 * More getters/setters
 *
 ***************************************************/

	public String getOpt1Text() {
		return opt1Text;
	}
	public String getOpt2Text() {
		return opt2Text;
	}
	public String getOpt3Text() {
		return opt3Text;
	}
	public String getOpt4Text() {
		return opt4Text;
	}
	public String getOpt5Text() {
		return opt5Text;
	}
	public String getOpt6Text() {
		return opt6Text;
	}


	public Integer getOpt1Right() {
		return opt1Right;
	}
	public Integer getOpt2Right() {
		return opt2Right;
	}
	public Integer getOpt3Right() {
		return opt3Right;
	}
	public Integer getOpt4Right() {
		return opt4Right;
	}
	public Integer getOpt5Right() {
		return opt5Right;
	}
	public Integer getOpt6Right() {
		return opt6Right;
	}


	public void setOpt1Text(String opt1Text) {
		this.opt1Text = opt1Text;
	}
	public void setOpt2Text(String opt2Text) {
		this.opt2Text = opt2Text;
	}
	public void setOpt3Text(String opt3Text) {
		this.opt3Text = opt3Text;
	}
	public void setOpt4Text(String opt4Text) {
		this.opt4Text = opt4Text;
	}
	public void setOpt5Text(String opt5Text) {
		this.opt5Text = opt5Text;
	}
	public void setOpt6Text(String opt6Text) {
		this.opt6Text = opt6Text;
	}


	public void setOpt1Right(Integer opt1Right) {
		this.opt1Right = opt1Right;
	}
	public void setOpt2Right(Integer opt2Right) {
		this.opt2Right = opt2Right;
	}
	public void setOpt3Right(Integer opt3Right) {
		this.opt3Right = opt3Right;
	}
	public void setOpt4Right(Integer opt4Right) {
		this.opt4Right = opt4Right;
	}
	public void setOpt5Right(Integer opt5Right) {
		this.opt5Right = opt5Right;
	}
	public void setOpt6Right(Integer opt6Right) {
		this.opt6Right = opt6Right;
	}
}
