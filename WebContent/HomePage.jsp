<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="exchange.model.skill.Type,java.util.ArrayList"%>

<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

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
			<a href="#" class="logo"> <span class="symbol"><img
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
			<li><a href="#MYSKILL">我的技能列表</a></li>
			<li><a href="#INTEREST">興趣技能列表</a></li>
			<li><a href="Exchange.do">交流列表 ></a></li>
			<li><a href="ChangePwd.jsp">帳戶管理 ></a></li>
			<li><a href="Logout.do">登出 </a></li>
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
				<span class="image"> <a href="#popupPROFILE"> <img
						src="_homePage/images/pic04.jpg" alt="" />
				</a>
				</span>

				<h2 class="special">${profile.userName}</h2>
				<div id="popupPROFILE" class="overlay">
					<div class="popup" style="color: black; height: 70%">

						<h2>個人資料</h2>
						<br> <a class="close" href="#PROFILE">&times;</a>
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
								<br> <a class="close" href="#PROFILE">&times;</a>
								<form method="post" action="Account.do#PROFILE"> 
									<input type="text" name="nick" placeholder="暱稱"><br>
									<input type="text" name="region" placeholder="地區"><br>
									<input type="text" name="email" placeholder="信箱"><br>
								
									<input type="hidden" value="1" name="mark">
									<input type="submit" style="position:absolute;bottom:10%;" class="btn_more r5" value="確認">
								</form>
							</div>
						</div>
					</h2>
				</div>
				</article> </section>
				<hr>
				<br>
				<h3 id="MYSKILL">我的技能(${profile.skillNumber}/${profile.skillMax})</h3>
				<section class="tiles"> 
				<c:forEach var="skill"	items="${skills}" varStatus="skillLoopCount">
					<article class="style${skillLoopCount.count%5+1}"> 
						<span class="image"> 
							<a href="Skill.do?mark=2&id=${skill.mySkill.skillId}#MYSKILL"> 
								<img src="_homePage/images/pic04.jpg" alt="" />
							</a>
						</span>

						<h2 class="special">${skill.mySkill.type.typeName}</h2>
	
						 
						<div>

								<h2 style="margin: 1em;">${skill.mySkillStatus()}
								<input src="_homePage/images/doc.png" type="image" onclick="window.location.href='CreateSkill.do'" style="position: absolute; right:16%; bottom:6%;">
							<c:choose>
							 	<c:when test = "${skill.status == 1}">
									<input src="_homePage/images/chat.png" type="image"
										onclick="window.location.href='Communication.do?id=${skill.mySkill.getSkillId()}&othersID=${skill.othersSkill.getSkillId()}'"
										style="position: absolute; right: 4%; bottom: 6%;"> 
								</c:when>
								<c:when test = "${skill.status == 0}">
									<input
										src="_homePage/images/pair.png" type="image"
										onclick="window.location.href='Match.do?cardId=${skill.mySkill.getSkillId()}'"
										style="position: absolute; right: 4%; bottom: 6%;">
								</c:when>
								<c:when test = "${skill.status == 2}">
								<input src="_homePage/images/star.png" type="image" onclick="window.location.href='Skill.do?mark=5&myId=${skill.mySkill.skillId}&otherId=${skill.othersSkill.skillId}#popup2'" style = "position: absolute; right:4%; bottom:6%;">
								</c:when>
							</c:choose>
							</h2>
						</div>
						
					</article>
				</c:forEach> 
				
				<article class="style0">
					<span class="image"> 
						<a href="
							<%boolean isSkillsFull = (boolean) request.getAttribute("isSkillsFull");
								if (isSkillsFull) {
									out.print("#popupLimit");
								} else {
									out.print("CreateSkill.do");
								}
							%>">
							<img src="_homePage/images/pic04.jpg" alt="" />
						</a>
					</span>

				<h2 class="special">＋</h2>

				<div>
					<h2 style="margin: 1em;">
						點選以新增技能
						<div id="popupLimit" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">技能卡新增已達上限！
								<p>
							</div>
						</div>
						<div id="popupDuplicate" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>錯誤警告</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">重複新增相同類別技能卡！
								<p>
							</div>
						</div>
						<div id="popup_success_change" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>通知</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">更改密碼成功！
								<p>
							</div>
						</div>
						<div id="popup_succesEdit" class="overlay">
							<div class="popup" style="color: black; height: 40%">
								<h2>通知</h2>
								<br> <a class="close" href="#">&times;</a>
								<p style="color: red; float: center;">成功修改個人資料！
								<p>
							</div>
						</div>
					</h2>
				</div>
				</article> </section>
				<hr>
				<br>
				
				<h3 id="INTEREST">興趣技能</h3>
				<section class="tiles"> <c:forEach var="favorite" items="${favorites}" varStatus="favoritesLoop">
					<article class="style${favoritesLoop.count%5+1}"> <span class="image"> <a
						href="#INTEREST"> <img src="_homePage/images/pic04.jpg" alt="" />
					</a>
					</span>

					<h2 class="special">${favorite.typeName}</h2>

					<div>
						<h2 style="margin: 1em;">
							點選右側圖示以移除
							<form method="get" action="Skill.do#INTEREST"> 
							<input type="hidden" value="4" name="mark">
							<input type="hidden" value="${favorite.typeName}" name="type">
							<input type="image" src="_homePage/images/no.png" alt="Submit Form" 
								style="position: absolute; right: 4%; bottom: 6%;"/>
								</form>
						</h2>
					</div>
					</article>
				</c:forEach> <article class="style0"> <span class="image"> <a
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
								<br> <a class="close" href="#INTEREST">&times;</a>
								<form method="get" action="Skill.do#INTEREST" >
									<select id="kind" name="kind">
										<c:forEach var="kind" items="${kinds}">
											<option value="${kind.kindCode}">${kind.kindName}</option>
										</c:forEach>
									</select> <select id="type" name="favorite">
										<option value="noType">請選擇類別</option>
									</select>
									<input type="hidden" value="3" name="mark">
									<input type="submit" style="position:absolute;bottom:10%;" class="btn_more r5" >
								</form>
								
							</div>
						</div>
				</div>
				</article> </section>
			</div>
		</div>

		
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

				out.println("if(kind.value == '" + type.getKindCode() + "')" + "type.innerHTML+='<option value=\""
						+ type.getTypeName() + "\">" + type.getTypeName() + "</option>'");

			}%>
		}

		window.addEventListener("load", start, false);
	</script>
</body>
</html>
