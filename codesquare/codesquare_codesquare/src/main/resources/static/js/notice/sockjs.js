var stompClient = null;
var socket = null;
// var messageList = null;
var messages = $("[name=messages]").val();

var notibell = "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4uCFAx5yg3Qmx6XJOSMUiBg-o1RE1bOB3LQ4k-lyF68yFNXbz'/>"; 
var aline;
$(function() {

	//var messageList = $("#messages");
	 socket = new SockJS('/stomp');
	 stompClient = Stomp.over(socket);

	// 연결시점
	stompClient.connect({}, function(frame) {
		console.log("connect OK!");
	//	coints();
		//DB List메소드
		noticeviewList();
		
		stompClient.subscribe("/topic/message", function(data) {
			var message = data.body;
			//console.log("message값 : " + message);
			//console.log("aline값 : " + aline);
		 
			//기존
			//$('#messages').prepend("<li>" +message+ "</li>");
			//$("#messages").prepend("<li>" +notibell +message+ "</li>");
			//$('#messages').prepend("<li>" +"<a href='" + aline + "'>"+ "회원님의 게시판에 댓그리달렸습니다" +message+"</a>"+ "</li>");
			//console.log("66666=== "+"<li>" + "<a href='" + ada + "'>" + notibell +message+ "</a>"+"</li>");
			
			//a태그 추가 시
			//if(!(typeof aline === 'undefined' || aline===null)){
			//if(!(typeof aline === 'undefined')){
			//	console.log("연결안의 aline경로 마지막나올애: " + aline)
			//+이미지	messageList.prepend("<li>" + "<a href='" + aline + "'>" + notibell +message+ "</a>"+"</li>");
			//messageList.prepend("<li>" +"<a href='" + aline + "'>" +message+ "</a>"+"</li>");
			//}
			 
		}); //subscribe 닫음
	});//connect 닫음
}); //레디 function


//ajax 알림리스트 DB
function noticeviewList() {
	$.ajax({
		url:'noticeview',
		type:'GET',
		data:{"messages":messages}
	}).done(function(data){
		var noticeviewContent =""; 
		$.each(data, function(key, value){
			noticeviewContent += "<li>"+notibell+"<a href='" + value.noticeLink + "'>"+value.noticeContent+ "<p id=send>" +  value.sendDate + "</p>"+"</br>"+"</a>"+"</li>";
			//	 $("#messages").prepend("<li>"  +messages+ "</li>");
			// $(this).append(noticeviewContent);
		});
		console.log("noticeviewList나옴")
		// $(".review-container").append(reviewContent);
		$("#messages").html(noticeviewContent);
	}).fail(function(data){
		console.log("noticeviewContent실패");
	})
}

/*//ajax 알림리스트 DB뿌리기 2
function noticeviewList() {
	$.ajax({
		url:'noticeview',
		type:'GET',
		data:{"qq":qq, "aline":aline}
	}).done(function(data){
		var noticeviewContent =""; 
		$.each(data, function(key, value){
			noticeviewContent+="<p>"+"리스트가 나오는 곳입니다"+"</p>";
		});
		
		// $(".review-container").append(reviewContent);
		$("#messages").html(noticeviewContent);
	}).fail(function(data){
		alert("noticeviewContent 실패");
	})
}//noticeviewList() 닫음
*/

//댓글 추가시
	function reply() {
		
		aline = window.location.href;
		
		$.ajax({
			type : 'get',
			//dataType:"json", 
			url : '/send/message',
			data : {
				'qq' : "회원님의 게시글에 댓글이 달렸습니다.(DB)",
				'aline' : aline
			},

			success : function() {
				
				console.log("reply()작동 ok");
				 
				//aline = window.location.href;
				console.log("경로: " +aline);
				//a태그 성공$('#messages').prepend("<li>" +"<a href='" + aline + "'>"+ "댓글달림. 링크타고 가기 클릭</a>"+ "</li>");
				$('#messages').prepend("<li>" + notibell + "<a href='" + aline + "'>"+ "회원님의 게시글에 댓글이 달렸습니다.(js)" +"</a>" +"</li>");
				//console.log("data.body: " +message);
				
			}//success 닫음
		}); //ajax 닫음
	}//reply() 닫음

	
//회원가입시 알림발송
	function join() {
		aline = null;
		$.ajax({
			type : 'get',
			url : '/send/message',
			data : {
				'qq' : "코드스퀘어 가입을 환영합니다.^^*(DB) ",
				'aline' : aline
			},
			success : function(data) {
				//message = data.body; 
				console.log("가입햇다.");

				$('#messages').prepend("<li>" + notibell +"코드스퀘어 가입을 환영합니다^^*(js)"+ "</li>");
			}
		});
	}//join() 닫음
	
//모두 삭제
	function allDelete() {
		$( "#messages" ).empty();
	}

//읽음삭제
	function readDelete() {
		//
	}
	
//닫힘
	function onclose() {
		alret("연결이 끊겼습니다");
	}
	
//연결끊기
	function disconnect() {
	    if (stompClient !== null) {
	        stompClient.disconnect();
	    }
	    console.log("Disconnected");
	}
	
	/*//카운트 에젝
	function coints() {
		$.ajax({
			url:'coints1',
			type:'GET',
			data:{'cc' : cc}
		}).done(function(data){
			var noticeviewContent3 =""; 
			$.each(data, function(key, value){
				noticeviewContent3 += "<li>"+"cc"+"</li>";
				//	 $("#messages").prepend("<li>"  +messages+ "</li>");
				// $(this).append(noticeviewContent);
			});
			console.log("cc나옴")
			// $(".review-container").append(reviewContent);
			$("#messages").html(noticeviewContent3);
		}).fail(function(data){
			console.log("noticeviewContent실패");
		})
	}*/
	
