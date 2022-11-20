$(function() {	
	$('#datePicker').datepicker({
	    format: "yyyy-mm-dd",	//데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
	    startDate: '-30d',	//달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
	    endDate: '+0d',	//달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
	    autoclose : true,	//사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
	    daysOfWeekDisabled : [0,6],	//선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
	    language : "ko"	//달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
	});//datepicker end
	if($("#datePicker").val() == null || $("#datePicker").val().length == 0) {
		$("#datePicker").datepicker("setDate", "0");
	}
});//ready end