package com.eq.house;

public class OptionAndText {
	private String opt;
	private String text;

	public OptionAndText() {
		this.opt = "";
		this.text = "";
	}

	public OptionAndText(String opt, String text) {
		this.opt = opt;
		this.text = text;
	}

	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
