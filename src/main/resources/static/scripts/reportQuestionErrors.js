function openReportErrorsForm() {
	modalReportQuestionErrors.style.display = "block";
}

function closeReportErrorsForm() {
	modalReportQuestionErrors.style.display = "none";
}

function reportQuestionError() {
	var playerId = document.getElementsByName("playerId")[0].value;
	var quizId = document.getElementsByName("quizId")[0].value;
	var questionNum = document.getElementsByName("questionNum")[0].value;
	var reportErrorsDesc = document.getElementsByName("reportErrorsDesc")[0].value;

	var request = new XMLHttpRequest();

	request.open("POST", "/reportQuestionError", true);
	request.setRequestHeader("Content-Type", "application/json");

	request.onreadystatechange = function () {
		if (request.readyState === 4 && request.status === 200) {
			var json = JSON.parse(request.responseText);

			closeReportErrorsForm();
			alert(json.html);
		}
	};

	var out = JSON.stringify({
		"playerId": playerId,
		"quizId": quizId,
		"questionNum": questionNum,
		"reportErrorsDesc": reportErrorsDesc
	});

	request.send(JSON.stringify(out));
}
