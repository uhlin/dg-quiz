function openReportContentsForm() {
	modalReportQuestionContents.style.display = "block";
}

function closeReportContentsForm() {
	modalReportQuestionContents.style.display = "none";
}

function reportQuestionContent() {
	var playerId = document.getElementsByName("playerId")[0].value;
	var quizId = document.getElementsByName("quizId")[0].value;
	var questionNum = document.getElementsByName("questionNum")[0].value;

	var rwList = document.getElementsByName("reportWhat");
	var reportSubject = 0;

	for (var i = 0; i < rwList.length; i ++) {
		if (rwList[i].checked) {
			reportSubject = rwList[i].value;
			break;
		}
	}

	var reportDesc = document.getElementsByName("reportDesc")[0].value;
	var request = new XMLHttpRequest();

	request.open("POST", "/reportQuestionContent", true);
	request.setRequestHeader("Content-Type", "application/json");

	request.onreadystatechange = function () {
		if (request.readyState === 4 && request.status === 200) {
			var json = JSON.parse(request.responseText);

			closeReportContentsForm();
			alert(json.html);
		}
	};

	var out = JSON.stringify({
		"playerId": playerId,
		"quizId": quizId,
		"questionNum": questionNum,
		"reportSubject": reportSubject,
		"reportDesc": reportDesc
	});

	request.send(JSON.stringify(out));
}
