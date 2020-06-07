function answerQuestion() {
	var playerId = document.getElementsByName("playerId")[0].value;
	var quizId = document.getElementsByName("quizId")[0].value;
	var questionNum = document.getElementsByName("questionNum")[0].value;

	var opt1 = document.getElementsByName("opt1Checked")[0].checked;
	var opt2 = document.getElementsByName("opt2Checked")[0].checked;
	var opt3 = document.getElementsByName("opt3Checked")[0].checked;
	var opt4 = document.getElementsByName("opt4Checked")[0].checked;

	var ansAlt = "4";
	var opt5 = false;
	var opt6 = false;

	if (document.getElementsByName("opt5Checked").length != 0 &&
	    document.getElementsByName("opt6Checked").length != 0) {
		ansAlt = "6";
		opt5 = document.getElementsByName("opt5Checked")[0].checked;
		opt6 = document.getElementsByName("opt6Checked")[0].checked;
	}

	var out = JSON.stringify({
		"playerId": playerId,
		"quizId": quizId,
		"questionNum": questionNum,
		"ansAlt": ansAlt,
		"opt1": opt1.toString(),
		"opt2": opt2.toString(),
		"opt3": opt3.toString(),
		"opt4": opt4.toString(),
		"opt5": opt5.toString(),
		"opt6": opt6.toString()
	});

	var request = new XMLHttpRequest();
	request.open("POST", "/answerQuestion", true);
	request.send(JSON.stringify(out));
}
