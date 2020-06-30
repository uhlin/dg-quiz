function answerQuestion() {
	var answers = document.getElementsByName("answer");

	if (answers.length != 0) {
		document.getElementById("opt1Checked").value = answers[0].checked;
		document.getElementById("opt2Checked").value = answers[1].checked;
		document.getElementById("opt3Checked").value = answers[2].checked;
		document.getElementById("opt4Checked").value = answers[3].checked;

		if (answers.length == 6) {
			document.getElementById("opt5Checked").value = answers[4].checked;
			document.getElementById("opt6Checked").value = answers[5].checked;
		}
	}

	document.getElementById("ansQuestionForm").action = "/answerQuestion";
	document.getElementById("ansQuestionForm").onsubmit = "";
	document.getElementById("ansQuestionForm").submit();
}
