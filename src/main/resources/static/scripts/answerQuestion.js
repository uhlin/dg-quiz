function answerQuestion() {
	document.getElementById("ansQuestionForm").action = "/answerQuestion";
	document.getElementById("ansQuestionForm").onsubmit = "";
	document.getElementById("ansQuestionForm").submit();
}
