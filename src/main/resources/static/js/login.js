$(document).ready(function() {
	var useridValid = false;	// 아이디 유효성 검사 결과
	var passwordValid = false;	// 비밀번호 유효성 검사 결과
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var formObj = $(".form-signin");
	
	// 아이디 유효성 검사
	$("input[name='username']").on("keyup", function(e) {
		var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
		if( !regExp.test($("input[name='username']").val()) ) {
			$("input[name='username']").css("color", "#EE5656");
			useridValid = false;
		}
		else {
			$("input[name='username']").css("color", "blue");
			useridValid = true;
		}
	});
	
	// 비밀번호 유효성 검사
	$("input[name='password']").on("keyup", function(e) {
		var regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{5,15}$/;
		if( !regExp.test($("input[name='password']").val()) ) {
			$("input[name='password']").css("color", "#EE5656");
			passwordValid = false;
		}
		else {
			$("input[name='password']").css("color", "blue");
			passwordValid = true;
		}
	});

	$(".btn-primary").on("click", function(e) {
		e.preventDefault();
		var userid = $("input[name='username']").val();
		if(userid == null || userid.length == 0) {
			alert("아이디를 입력하세요");
			userid.focus();
			return;
		}

		if(useridValid == false) {
			alert("영문으로 시작하여 숫자 가능. 6자 이상 20자 이하를 사용하세요.");
			return;
		}
		
		var password = $("input[name='password']").val();
		if(password == null || password.length == 0) {
			alert("암호를 입력하세요");
			password.focus();
			return;
		}
		
		if(passwordValid == false) {
			alert("특수문자, 영문, 숫자의 조합으로 8자 이상 15자 이하를 사용하세요.");
			return;
		}

		console.log("submit clicked");
		formObj.submit();
	});
});