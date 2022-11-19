$(document).ready(function() {
	var idCheck = false;	// 아이디 중복 검사 통과 여부
	var useridValid = false;	// 아이디 유효성 검사 결과
	var passwordValid = false;	// 비밀번호 유효성 검사 결과
	var password2Valid = false;	// 비밀번호 확인 유효성 검사 결과
	var emailValid = false;	// 이메일 유효성 검사 결과
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	var formObj = $(".form-join");
	
	// 아이디 유효성 검사
	$("input[name='userid']").on("keyup", function(e) {
		var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
		if( !regExp.test($("input[name='userid']").val()) ) {
			$("input[name='userid']").css("color", "#EE5656");
			useridValid = false;
		}
		else {
			$("input[name='userid']").css("color", "blue");
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
	
	// 비밀번호 확인 유효성 검사
	$("input[name='password2']").on("keyup", function(e) {
		var regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{5,15}$/;
		if( !regExp.test($("input[name='password2']").val()) ) {
			$("input[name='password2']").css("color", "#EE5656");
			password2Valid = false;
		}
		else {
			$("input[name='password2']").css("color", "blue");
			password2Valid = true;
		}
	});
	
	// 이메일 확인 유효성 검사
	$("input[name='email']").on("keyup", function(e) {
		var regExp = /^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{2,5}$/;
		if( !regExp.test($("input[name='email']").val()) ) {
			$("input[name='email']").css("color", "#EE5656");
			emailValid = false;
		}
		else {
			$("input[name='email']").css("color", "blue");
			emailValid = true;
		}
	});
	
	// 아이디 중복 확인
	$(".btn-secondary").on("click", function(e) {
		var userid = $("input[name='userid']").val();
		if(userid == null || userid.length == 0) {
			alert("아이디를 입력하세요");
			return;
		}
		if(useridValid == false) {
			alert("영문으로 시작하여 숫자 가능. 6자 이상 20자 이하를 사용하세요.");
			return;
		}
		// 서버로 중복 확인 요청
		$.ajax({
		  url: '/member/idCheck',
		  data: {userid: userid},
		  type: 'POST',
		  beforeSend: function(xhr) {
	          xhr.setRequestHeader(header, token);
	      },
		  success: function(result){
			if(result == "ok") {
				idCheck = true;
				alert("사용할 수 있는 아이디 입니다.");
			} else {
				idCheck = false;
				alert("사용할 수 없는 아이디 입니다.");
			}
		  }
		}); //$.ajax
	});
	
	$(".btn-success").on("click", function(e) {
		e.preventDefault();
		var userid = $("input[name='userid']").val();
		if(userid == null || userid.length == 0) {
			alert("아이디를 입력하세요");
			userid.focus();
			return;
		}
		
		if(idCheck == false) {
			alert("아이디 중복확인을 하세요");
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
		
		var password2 = $("input[name='password2']").val();
		if(password2 == null || password2.length == 0) {
			alert("암호 확인을 입력하세요");
			password2.focus();
			return;
		}
		
		if(password2Valid == false) {
			alert("특수문자, 영문, 숫자의 조합으로 8자 이상 15자 이하를 사용하세요.");
			return;
		}
		
		if(password != password2) {
			alert("암호 확인을 입력하세요");
			password2.focus();
			return;
		}
		
		var name = $("input[name='name']").val();
		if(name == null || name.length == 0) {
			alert("이름을 입력하세요");
			name.focus();
			return;
		}
		
		var email = $("input[name='email']").val();
		if(email == null || email.length == 0) {
			alert("이메일을 입력하세요");
			email.focus();
			return;
		}
		
		if(emailValid == false) {
			alert("올바른 이메일 형식을 사용하세요.");
			return;
		}

		console.log("submit clicked");
		formObj.submit();
	});
});