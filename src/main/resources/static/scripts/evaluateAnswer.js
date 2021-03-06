function evaluateAnswer() {
	var playerId = document.getElementsByName("playerId")[0].value;
	var quizId = document.getElementsByName("quizId")[0].value;
	var questionNum = document.getElementsByName("questionNum")[0].value;

	var answers = document.getElementsByName("answer");

	var opt1 = false;
	var opt2 = false;
	var opt3 = false;
	var opt4 = false;

	var ansAlt = "4";
	var opt5 = false;
	var opt6 = false;

	if (answers.length == 0) {
		opt1 = document.getElementsByName("opt1Checked")[0].checked;
		opt2 = document.getElementsByName("opt2Checked")[0].checked;
		opt3 = document.getElementsByName("opt3Checked")[0].checked;
		opt4 = document.getElementsByName("opt4Checked")[0].checked;

		if (document.getElementsByName("opt5Checked").length != 0 &&
		    document.getElementsByName("opt6Checked").length != 0) {
			ansAlt = "6";
			opt5 = document.getElementsByName("opt5Checked")[0].checked;
			opt6 = document.getElementsByName("opt6Checked")[0].checked;
		}
	} else {
		opt1 = answers[0].checked;
		opt2 = answers[1].checked;
		opt3 = answers[2].checked;
		opt4 = answers[3].checked;

		if (answers.length == 6) {
			ansAlt = "6";
			opt5 = answers[4].checked;
			opt6 = answers[5].checked;
		}
	}

	var request = new XMLHttpRequest();

	request.open("POST", "/evaluateAnswer", true);
	request.setRequestHeader("Content-Type", "application/json");

	request.onreadystatechange = function () {
		if (request.readyState === 4 && request.status === 200) {
			var json = JSON.parse(request.responseText);

			document.getElementById("ansContentDynamic").innerHTML = json.html;

			// Display Modal
			modalOutputAnswer.style.display = "block";
		}
	};

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

	request.send(JSON.stringify(out));
}
