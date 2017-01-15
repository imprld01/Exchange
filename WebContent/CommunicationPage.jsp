<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
  <meta charset="UTF-8">
  <title>Messaging</title>

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans'>
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.min.css'>

      <link rel="stylesheet" href="_communication/css/style.css">


</head>

<body>
  <!--

Follow me on
Dribbble: https://dribbble.com/supahfunk
Twitter: https://twitter.com/supahfunk
Codepen: http://codepen.io/supah/

It's just a concept, a fake chat to design a new daily UI for direct messaging.
Hope you like it :)

-->

<div class="chat">
  <div class="chat-title">
    <h1>${othersNickName}</h1>
    <h2>${skill.type.typeName}</h2>
    <input type="hidden" class="id" value="${id}">
    <figure class="avatar">
      <img src="_communication/images/cartoon.png" /></figure>
  </div>
  <div class="messages">
    <div class="messages-content"></div>
  </div>
  <div class="message-box">
    <textarea type="text" class="message-input" placeholder="Type message..."></textarea>
    <button type="submit" class="message-submit" id="submit">Send</button>
  </div>
  <button type="submit" class="message-submit" onclick="window.location.href='Home.do'">back</button>
</div>
<div class="bg"></div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.3/jquery.mCustomScrollbar.concat.min.js'></script>

    <script type="application/javascript">
		
    "use strict";
    var id;
    var Chat = {};
    Chat.socket = null;

    Chat.connect = (function(host) {
        if ('WebSocket' in window) {
            Chat.socket = new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            Chat.socket = new MozWebSocket(host);
        } else {
            //Console.log('Error: WebSocket is not supported by this browser.');
            return;
        }

        Chat.socket.onopen = function () {
            //Console.log('Info: WebSocket connection opened.');
            /*document.getElementById('chat').onkeydown = function(event) {
                if (event.keyCode == 13) {
                    Chat.sendMessage();
                }
            };*/
        };

        Chat.socket.onclose = function () {
           // document.getElementById('chat').onkeydown = null;
            //Console.log('Info: WebSocket closed.');
        };

        Chat.socket.onmessage = function (jsonString) {
            console.log(jsonString.data);
            var jsonMsg = JSON.parse(jsonString.data);
            if(jsonMsg.sdr == id)
            	showMyMessage(jsonMsg.content);
            else
            	fakeMessage(jsonMsg.content);
            
        };
    });

    Chat.initialize = function() {
        if (window.location.protocol == 'http:') {
            Chat.connect('ws://192.168.0.7:8080/Exchange/websocket/chat/${id}');
        } else {
            Chat.connect('wss://192.168.0.7:8080/Exchange/websocket/chat/${id}');
        }
    };

    Chat.sendMessage = (function(msg) {
        //var message = document.getElementById('chat').value;
     /*   if (message != '') {
            Chat.socket.send(message);
           // document.getElementById('chat').value = '';
        }*/
    	Chat.socket.send(msg);
    });

    /*var Console = {};

    Console.log = (function(message) {
        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.innerHTML = message;
        console.appendChild(p);
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    });*/

  
    
		var $messages = $('.messages-content'),d, h, m;


		$(window).load(function() {
		  $messages.mCustomScrollbar();
		  Chat.initialize();
      id = $('.id').val();
      //$('.message-input').val(id);
		 /* setTimeout(function() {
			fakeMessage();
		  }, 100);*/
		});

		function updateScrollbar() {
		  $messages.mCustomScrollbar("update").mCustomScrollbar('scrollTo', 'bottom', {
			scrollInertia: 10,
			timeout: 0
		  });
		}

		function setDate(){
		  d = new Date()
		  if (m != d.getMinutes()) {
			m = d.getMinutes();
			$('<div class="timestamp">' + d.getHours() + ':' + m + '</div>').appendTo($('.message:last'));
		  }
		}

		function insertMessage() {
		  var msg = $('.message-input').val();
		  if ($.trim(msg) == '') {
			return false;
		  }
		  Chat.sendMessage(msg);
		  $('.message-input').val(null);
		 /* setTimeout(function() {
			fakeMessage();
		  }, 1000 + (Math.random() * 20) * 100);*/
		}
		function showMyMessage(msg){
			  $('<div class="message message-personal">' + msg + '</div>').appendTo($('.mCSB_container')).addClass('new');
			  setDate();
			  updateScrollbar();
		}
		$('.message-submit').click(function() {
		  insertMessage();
		});

		$(window).on('keydown', function(e) {
		  if (e.which == 13) {
			insertMessage();
			return false;
		  }
		})

		

		function fakeMessage(msg) {
		  /*if ($('.message-input').val() != '') {
			return false;
		  }*/
		  $('<div class="message loading new"><figure class="avatar"><img src="_communication/images/cartoon.png" /></figure><span></span></div>').appendTo($('.mCSB_container'));
		  updateScrollbar();

		  
			$('.message.loading').remove();
			$('<div class="message new"><figure class="avatar"><img src="_communication/images/cartoon.png" /></figure>' + msg + '</div>').appendTo($('.mCSB_container')).addClass('new');
			setDate();
			updateScrollbar();
		}
			
	</script>
</html>