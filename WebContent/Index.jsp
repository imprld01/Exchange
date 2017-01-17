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

		<!-- Header -->
			<section id="header" class="dark">
				<header>
					<h1>E x c h a n g e</h1>
					<p>超讚的技能交換平台</p>
				</header>
				<footer>
					<a href="#login" class="button scrolly">Log In</a>
					<a href="#Sign up" class="button scrolly">Sign Up</a>
				</footer>
			</section>

		<!-- First -->
			<section id="first" class="main">
				<header>
					<div class="container">
						<h2>\Exchange/</h2>
						<p>\Exchange/\Exchange/\Exchange/\Exchange/\Exchange/\Exchange/\Exchange/\Exchange/</p>
					</div>
				</header>
				<div class="content dark style1 featured">
					<div class="container">
						<div class="row">
							<div class="4u 12u(narrow)">
								<section>
									<span class="feature-icon"><span class="icon fa-clock-o"></span></span>
									<header>
										<h3>即時性	</h3>
									</header>
									<p>能快速</p>
								</section>
							</div>
							<div class="4u 12u(narrow)">
								<section>
									<span class="feature-icon"><span class="icon fa-bolt"></span></span>
									<header>
										<h3>交流性</h3>
									</header>
									<p>無</p>
								</section>
							</div>
							<div class="4u 12u(narrow)">
								<section>
									<span class="feature-icon"><span class="icon fa-cloud"></span></span>
									<header>
										<h3>無</h3>
									</header>
									<p>無</p>
								</section>
							</div>
						</div>
						<div class="row">
							<div class="12u">
								<footer>
								 <br> <br> <br>
								</footer>
							</div>
						</div>
					</div>
				</div>
			</section>


		<!-- Log In -->
			<section id="login" class="main">
				<header>
					<div class="container">
						<h2>Login in</h2>

					</div>
				</header>

				<div class="content style4 fea-tured" >
					<div class="container 75%" align="center">
						<form method="post" action="Login.do">
							<div class="12u">
								<div class="6u 12u(mobile)"><input name="id" type="text" placeholder="Account" /></div>
							</div>
							<br>
							<div class="12u" >
								<div class="6u 12u(mobile)"><input name="pwd" type="password" placeholder="Password" /></div>
							</div>
								<br>
							<div class="row">
								<div class="12u">
									<ul class="actions">
										<li><input type="submit" class="button alt" value="登入" /></li>

									</ul>
								</div>
							</div>
						</form>
					</div>
				</div>
			</section>

			<hr>
			<!-- Sign Up -->
				<section id="Sign up" class="main">
					<header>
						<div class="container">
							<h2>Sign Up</h2>

						</div>
					</header>

					<div class="content style4 fea-tured" >
						<div class="container 75%" align="center">
							<form method="post" action="Signup.do">
								<div class="12u">
									<div class="6u 12u(mobile)"><input id="user" name="user" type="text" placeholder="姓名" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="nick" type="text" placeholder="暱稱" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="birth" type="text" id="date" placeholder="生日 例如：1111-11-11	" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="gender" type="text" placeholder="性別" /></div>
								</div>
								<br>
								<div class="12u">
									<div class="6u 12u(mobile)"><input name="region" type="text" placeholder="地區" /></div>
								</div>
								<br>
								<div class="12u" >
									<div class="6u 12u(mobile)"><input name="id" type="text" placeholder="帳號" /></div>
								</div>
								<br>
								<div class="12u" >
									<div class="6u 12u(mobile)"><input name="pwd" type="password" placeholder="密碼" /></div>
								</div>
								<br>
								<div class="12u" >
									<div class="6u 12u(mobile)"><input name="re_pwd" type="password" placeholder="密碼確認" /></div>
								</div>
								<br>

								<div class="12u" >
									<div class="6u 12u(mobile)"><input name="email" type="text" placeholder="信箱" /></div>
								</div>
								<br>
								<div class="row">
									<div class="12u">
										<ul class="actions">
											<li><input type="submit" class="button alt" value="提交" /></li>

										</ul>
									</div>
								</div>
							</form>
						</div>
					</div>
				</section>

						<div id="popup_Idduplicate" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">帳號重複！
								<p>
							</div>
						</div>
							
						<div id="popup_notsame" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">輸入密碼不相同！
								<p>
							</div>
						</div>
						
						<div id="popup_signsuccess" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>通知</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">帳號註冊成功！
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
			<script>

			</script>
	</body>
</html>