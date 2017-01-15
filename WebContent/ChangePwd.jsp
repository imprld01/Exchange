  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Tessellate by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Exchange</title>
		<meta charset="utf-8" />
		<meta name="viewport" con-tent="width=device-width, ini-tial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="_index/assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
		<link rel="stylesheet" href="_homePage/assets/css/popup.css" />
	</head>
	<body>

				<section id="Sign up" class="main">
					<header>
						<div class="container">
							<h2>修改密碼</h2>

						</div>
					</header>

					<div class="content style4 fea-tured" >
						<div class="container 75%" align="center">
							<form method="post" action="Account.do">
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="old_pwd" type="password" placeholder="目前密碼" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="pwd" type="password" placeholder="新密碼" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="re_pwd" type="password" placeholder="重新輸入新密碼" /></div>
								</div>
								<br>

								<div class="row">
									<div class="12u">
										<ul class="actions">
											<input type="hidden" value="3" name="mark"/>
											<li><input type="submit" class="button alt" value="修改" /></li>
											<li><input type="button" class="button alt" value="返回" onclick="window.location.href='Home.do'"/></li>
										</ul>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>
						<div id="popupNotMatch" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">原始密碼不符合！
								<p>
							</div>
						</div>
						<div id="popupNotSame" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">變更密碼不相同！
								<p>
							</div>
						</div>
						
		<!-- Scripts -->
			<script src="_index/assets/js/jquery.min.js"></script>
			<script src="_index/assets/js/jquery.scrolly.min.js"></script>
			<script src="_index/assets/js/skel.min.js"></script>
			<script src="_index/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="_index/assets/js/main.js"></script>

	</body>
</html>