var stompClient = null;
var socket = null;
// var messageList = null;

var aline;
$(function() {

	var messageList = $("#messages");
	 socket = new SockJS('/stomp');
	 stompClient = Stomp.over(socket);

	// 연결시점
	stompClient.connect({}, function(frame) {
		console.log("연결되었음");

		stompClient.subscribe("/topic/message", function(data) {
			var message = data.body;
			console.log("message값 : " + message);
			var notibell = "<img src='https://previews.123rf.com/images/hanaschwarz/hanaschwarz1211/hanaschwarz121100002/16260237-%ED%9D%B0%EC%83%89-%EB%B0%B0%EA%B2%BD%EC%97%90-%EA%B3%A0%EB%A6%BD-%EB%B9%A8%EA%B0%84-%EB%A6%AC%EB%B3%B8%EA%B3%BC-%EC%A2%85.jpg'/>"; 
		 
			//기존
			//messageList.prepend("<li>" +notibell +message+ "</li>");
			
			//console.log("66666=== "+"<li>" + "<a href='" + ada + "'>" + notibell +message+ "</a>"+"</li>");
			
			//a태그 추가 시
			if(!(typeof aline === 'undefined'||aline===null)){
			console.log("연결안의 aline경로 마지막나올애: " + aline)
			//messageList.prepend("<li>" + "<a href='" + aline + "'>" + notibell +message+ "</a>"+"</li>");
			messageList.prepend("<li>" +"<a href='" + aline + "'>" +message+ "</a>"+"</li>");
			}
		});

	});
 
});


//댓글 추가시
	function reply() {
		aline = window.location.href;
	
		$.ajax({
			type : 'get',
			url : '/send/message',
			data : {
				'qq' : "댓글보세요",
				'aline' : aline
			},

			success : function() {
				console.log("reply()작동 ok");
				
				//aline = window.location.href;
				console.log("atag()실행해서 경로 가지고 옴" +aline);
			}
		});
	}//reply()닫음
	
	
//회원가입시 알림발송
	function join() {
		$.ajax({
			type : 'get',
			url : '/send/message',
			data : {
				'qq' : "가입을 축하합니다!!!"
			},

			success : function() {
			}
		});
	}
	
//모두 삭제
	function allDelete() {
		$( "#messages" ).empty();
	}

//읽음삭제
	function readDelete() {
		//
	}
	
	
