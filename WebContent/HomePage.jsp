<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="exchange.model.skill.Type,java.util.ArrayList"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
	Phantom by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>個人頁面</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="_homePage/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="_homePage/assets/css/main.css" />
<link rel="stylesheet" href="_homePage/assets/css/popup.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="_homePage/assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="_homePage/assets/css/ie8.css" /><![endif]-->
</head>
<body>
	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header">
		<div class="inner">

			<!-- Logo -->
			<a href="index.html" class="logo"> <span class="symbol"><img
					src="_homePage/images/logo.svg" alt="" /></span><span class="title">Exchange</span>
			</a>

			<!-- Nav -->
			<nav>
			<ul>
				<li><a href="#menu">Menu</a></li>
			</ul>
			</nav>

		</div>
		</header>

		<!-- Menu -->
		<nav id="menu">
		<h2>Menu</h2>
		<ul>
			<li><a href="#PROFILE">個人資料</a></li>
			<li><a href="#MINE">我的技能列表</a></li>
			<li><a href="#INTEREST">興趣技能列表</a></li>
			<li><a href="ExchangePage.jsp">交流列表 ></a></li>
			<li><a href="../首頁/cnangePwd.html">帳戶管理 ></a></li>
			<li><a href="Index.jsp">登出 </a></li>
		</ul>
		</nav>

		<!-- Main -->
		<div id="main">
			<div class="inner">
				<header>
				<h1>個人頁面</h1>
				</header>
				<hr>
				<br>
				<h3 id="PROFILE">個人資料</h3>
				<section class="tiles"> <article class="style0">
				<span class="image"> <a href="#popup87"> <img
						src="_homePage/images/pic04.jpg" alt="" />
				</a>
				</span>

				<h2 class="special">${profile.userName}</h2>
				<div id="popup87" class="overlay">
					<div class="popup" style="color: black; height: 70%">

						<h2>個人資料</h2>
						<br> <a class="close" href="#">&times;</a>
						<form>
							<select>
								<option value="Taipei">${profile.userName}</option>
							</select> <select>
								<option value="Taipei">${profile.nickName}</option>
							</select> <select>
								<option value="Taipei">${profile.birthday}</option>
							</select> <select>
								<option value="Taipei">${profile.gender}</option>
							</select> <select>
								<option value="Taipei">${profile.region}</option>
							</select> <select>
								<option value="Taipei">${profile.email}</option>
							</select>
						</form>

					</div>
				</div>
				<div>
					<h2 style="margin: 1em;">
						<c:choose>
							<c:when test="${profile.gender== true}">
								<c:out value="男" />
							</c:when>
							<c:when test="${profile.gender== false}">
								<c:out value="女" />
							</c:when>
						</c:choose>
						${age}歲 ${profile.region} <input src="_homePage/images/doc.png"
							type="image" onclick="window.location.href='#popup0'"
							style="position: absolute; right: 4%; bottom: 7%;">
						<div id="popup0" class="overlay">
							<div class="popup" style="color: black; height: 70%;">

								<h2>修改個人資料</h2>
								<br> <a class="close" href="#">&times;</a>
								<form>
									<input type="text" name="fname" placeholder="暱稱"><br>
									<input type="text" name="fname" placeholder="地區"><br>
									<input type="text" name="fname" placeholder="信箱"><br>
								</form>
								<a href="http://localhost:8080/Exchange/Account.do?mark=3" class="btn_more r5">確認修改</a>

							</div>
						</div>
					</h2>
				</div>
				</article> </section>
				<hr>
				<br>
				<h3 id="MINE">我的技能</h3>
				<section class="tiles"> <c:foreach var="skill"
					item="${skills}" varStatus="skillLoopCount">
					<article class="style${skillLoopCount.count/5+1}"> <span
						class="image"> <a
						href="http://localhost:8080/Exchange/Skill.do?mark=2"> <img
							src="_homePage/images/pic04.jpg" alt="" />
					</a>
					</span>

					<h2 class="special">${skill.type.typeName}</h2>

					<div>
						<h2 style="margin: 1em;">${skill}
							<input src="_homePage/images/chat.png" type="image"
								onclick="window.location.href='http://localhost:8080/Exchange/Communication.do.html'"
								style="position: absolute; right: 4%; bottom: 6%;"> <input
								src="_homePage/images/doc.png" type="image"
								onclick="window.location.href='../配對頁面/edit.html'"
								style="position: absolute; right: 16%; bottom: 6%;">
						</h2>
					</div>
					</article>
				</c:foreach> <!-- <article class="style4">
										<span class="image">
											<a href="generic.html">
												<img src="_homePage/images/pic04.jpg" alt="" />
											</a>
										</span>

										<h2 class = "special">歌唱</h2>

										<div>
											<h2 style="margin:1em;">正在養精蓄銳
													<input src="_homePage/images/pair.png" type="image" onclick="window.location.href='http://google.com'" style = "position: absolute; right:4%; bottom:6%;">
													<input src="_homePage/images/doc.png" type="image" onclick="window.location.href='http://google.com'" style = "position: absolute; right:16%; bottom:6%;">
											</h2>
										</div>
									</article> --> <article class="style0"> <span
					class="image"> <a
					href="
												<%boolean isSkillsFull = (boolean) request.getAttribute("isSkillsFull");
			if (isSkillsFull) {
				out.print("#popup666");
			} else {
				out.print("http://localhost:8080/Exchange/Skill.do?mark=0");
			}%>
											">
						<img src="_homePage/images/pic04.jpg" alt="" />
				</a>
				</span>

				<h2 class="special">＋</h2>

				<div>
					<h2 style="margin: 1em;">
						點選以新增技能
						<div id="popup666" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">技能卡新增已達上限！
								<p>
							</div>
						</div>
					</h2>
				</div>
				</article> </section>
				<hr>
				<br>
				<h3 id="INTEREST">興趣技能</h3>
				<section class="tiles"> <c:foreach var="favorite"
					item="${favorites}">
					<article class="style1"> <span class="image"> <a
						href="#INTEREST"> <img src="_homePage/images/pic04.jpg" alt="" />
					</a>
					</span>

					<h2 class="special">${favorite.typeName}</h2>

					<div>
						<h2 style="margin: 1em;">
							點選右側圖示以移除 <input src="_homePage/images/no.png" type="image"
								onclick="window.location.href='http://localhost:8080/Exchange/Skill.do?mark=4'"
								style="position: absolute; right: 4%; bottom: 6%;">
						</h2>
					</div>
					</article>
				</c:foreach> <article class="style0"> <span class="image"> <a
					class="button_popup" href="#popup1"> <img
						src="_homePage/images/pic04.jpg" alt="" />
				</a>
				</span>

				<h2 class="special">＋</h2>

				<div>
					<h2 style="margin: 1em;">
						點選以新增興趣技能

						<div id="popup1" class="overlay">
							<div class="popup" style="color: black;">

								<h2>新增興趣技能</h2>
								<br> <a class="close" href="#">&times;</a>
								<form>
									<select id="kind" name="類別">
										<c:foreach var="kind" item="${kinds}">
											<option value="${kind.kindCode}">${kind.kindName}</option>
										</c:foreach>
									</select> <select id="type" name="項目">
										<option value="Taipei">吉他</option>
										<option value="Taoyuan">小號</option>
										<option value="Hsinchu">薩克斯風</option>
										<option value="Miaoli">錫口笛</option>
									</select>
								</form>
								<a href="http://localhost:8080/Exchange/Skill.do?mark=3" class="btn_more r5">新增</a>

							</div>
						</div>
				</div>
				</article> </section>
			</div>
		</div>

		<!-- Footer -->
		<footer id="footer">
		<div class="inner">
			<section>
			<h2>Get in touch</h2>
			<form method="post" action="#">
				<div class="field half first">
					<input type="text" name="name" id="name" placeholder="Name" />
				</div>
				<div class="field half">
					<input type="email" name="email" id="email" placeholder="Email" />
				</div>
				<div class="field">
					<textarea name="message" id="message" placeholder="Message"></textarea>
				</div>
				<ul class="actions">
					<li><input type="submit" value="Send" class="special" /></li>
				</ul>
			</form>
			</section>
			<section>
			<h2>Follow</h2>
			<ul class="icons">
				<li><a href="#" class="icon style2 fa-twitter"><span
						class="label">Twitter</span></a></li>
				<li><a href="#" class="icon style2 fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon style2 fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon style2 fa-dribbble"><span
						class="label">Dribbble</span></a></li>
				<li><a href="#" class="icon style2 fa-github"><span
						class="label">GitHub</span></a></li>
				<li><a href="#" class="icon style2 fa-500px"><span
						class="label">500px</span></a></li>
				<li><a href="#" class="icon style2 fa-phone"><span
						class="label">Phone</span></a></li>
				<li><a href="#" class="icon style2 fa-envelope-o"><span
						class="label">Email</span></a></li>
			</ul>
			</section>
			<ul class="copyright">
				<li>&copy; Untitled. All rights reserved</li>
				<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="_homePage/assets/js/jquery.min.js"></script>
	<script src="_homePage/assets/js/skel.min.js"></script>
	<script src="_homePage/assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="_homePage/assets/js/main.js"></script>

	<script>
		function start() {
			document.getElementById("kind").addEventListener("change",
					addActivityItem, false);
		}

		function addActivityItem() {
			var kind = document.getElementById("kind");
			var type = document.getElementById("type");
			type.innerHTML = "";
	<%ArrayList<Type> typeList = (ArrayList<Type>) request.getAttribute("types");
			for (Type type : typeList) {

				out.println("if(kind.value == '" + type.getKindCode() + "')" + "type.innerHTML+='<option value="
						+ type.getTypeName() + ">" + type.getTypeName() + "</option>'");

			}%>
		}

		window.addEventListener("load", start, false);
	</script>
</body>
</html>
