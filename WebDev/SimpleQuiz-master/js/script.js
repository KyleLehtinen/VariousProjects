function submitAnswers(){
	var qTotal = 5;
	var score = 0;
	
	//Get User Input
	var q1 = document.forms["quizForm"]["q1"].value;
	var q2 = document.forms["quizForm"]["q2"].value;
	var q3 = document.forms["quizForm"]["q3"].value;
	var q4 = document.forms["quizForm"]["q4"].value;
	var q5 = document.forms["quizForm"]["q5"].value;
	
	//Validate user input
	for(i = 1; i <= qTotal; i++){
		if(eval('q'+i) == null || eval('q'+i) == ''){
			alert("You missed question " + i);
			return false;
		}
	}
	
	//Set Correct Answers for submission
	var answers = ["b","a","d","b","d"];
	
	//Loop to checks user given answers
	for(i = 1; i <= qTotal; i++){
		if(eval('q'+i) == answers[i-1]){
			score++;
		}
	}
	
	//Display alert box and header with final score
	var results = document.getElementById('results');
	results.innerHTML = '<h3>You scored <span>' + score + '</span> out of <span>' + qTotal + '</span></h3>';
	if(score == qTotal){
		alert('Congratulations! You scored a perfect 5 out of 5!');
	} else{
		alert('You scored ' + score + ' out of ' + qTotal);
	}
	
	return false;
}