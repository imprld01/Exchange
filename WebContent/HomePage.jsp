<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
								<a href="index.html" class="logo">
									<span class="symbol"><img src="_homePage/images/logo.svg" alt="" /></span><span class="title">Exchange</span>
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
							<li><a href="#ING">進行中交流</a></li>
							<li><a href="#YA">受到的邀請</a></li>
							<li><a href="#GET">送出的邀請</a></li>
							<li><a href="profile.html">個人頁面 ></a></li>
							<li><a href="../首頁/cnangePwd.html">帳戶管理 ></a></li>
							<li><a href="../首頁/index.html">登出 </a></li>
						</ul>
					</nav>

				<!-- Main -->
					<div id="main">
						<div class="inner">
							<header>
								<h1>交流列表</h1>
							</header>
							<hr><br>
							<h3 id="ING">進行中交流</h3>
							<section class="tiles">
                                <c:foreach var="exchange" item="${Exchanging}" varStatus="exchangeLoopCount">
                                <article class="style${exchangeLoopCount.count/5+1}">
									<span class="image">
										<a href="../配對頁面/skillfile_chinese.html">
											<img src="_homePage/images/pic04.jpg" alt="" />
										</a>
									</span>

                                    <h2 class = "special">${Exchanging.receiveSkill.typeName}</h2>

									<div>
										<h2 style="margin:1em;">以${Exchanging}技能交換
												<input src="_homePage/images/star.png" type="image" onclick="window.location.href='../配對頁面/skillfile_chinese.html#popup2'" style = "position: absolute; right:4%; bottom:6%;">
												<input src="_homePage/images/chat.png" type="image" onclick="window.location.href='../messaging/index.html'" style = "position: absolute; right:16%; bottom:5%;">
										</h2>
									</div>
								</article>
                                </c:foreach>
							</section>
							<hr><br>
							<h3 id="YA">受到的邀請</h3>
							<section class="tiles">
                                <c:foreach var="receiveInvitation" item="${ReceiveInvitation}">
                                    <article class="style5">
                                        <span class="image">
                                            <a href="generic.html">
                                                <img src="_homePage/images/pic04.jpg" alt="" />
                                            </a>
                                        </span>

                                        <h2 class = "special">${ReceiveInvitation.receiveSkill.typeName}</h2>

                                        <div>
                                            <h2 style="margin:1em;">${ReceiveInvitation}技能受到邀請
                                                    <input src="_homePage/images/no.png" type="image" onclick="window.location.href='#YA'" style = "position: absolute; right:4%; bottom:6%;">
                                                    <input src="_homePage/images/yes.png" type="image" onclick="window.location.href='#ING'" style = "position: absolute; right:16%; bottom:5%;">
                                            </h2>
                                        </div>
                                    </article>
                                </c:foreach>
							</section>
							<hr><br>
							<h3 id="INTEREST">興趣技能</h3>
							<section class="tiles" >
								
								<c:foreach var="favorite" item="${favorites}">
									<article class="style1">
										<span class="image">
											<a href="#INTEREST">
												<img src="images/pic04.jpg" alt="" />
											</a>
										</span>
	
										<h2 class = "special">${favorite.typeName}</h2>
	
										<div>
											<h2 style="margin:1em;">點選右側圖示以移除
													<input src="images/no.png" type="image" onclick="window.location.href='profile_0interest.html#INTEREST'" style = "position: absolute; right:4%; bottom:6%;">
											</h2>
										</div>
									</article>
								</c:foreach>
								
								<article class="style0">
									<span class="image">
										<a class="button_popup" href="#popup1">
											<img src="images/pic04.jpg" alt="" />
										</a>
									</span>

									<h2 class = "special">　＋</h2>

									<div>
										<h2 style="margin:1em;">點選以新增興趣技能

											<div id="popup1" class="overlay">
												<div class="popup" style="color:black;">

													<h2 >新增興趣技能</h2>
													<br>
													<a class="close" href="#">&times;</a>
													<form >
														<select id="kind" name="類別">
															<c:foreach var="kind" item="${types}">
																<option value="${kind.kindName}">${kind.kindCode}</option>
															</c:foreach>
														</select>
														<select id ="type" name="項目">
														　<option value="Taipei">吉他</option>
														　<option value="Taoyuan">小號</option>
														　<option value="Hsinchu">薩克斯風</option>
														　<option value="Miaoli">錫口笛</option>
														</select>
													</form>
													<a href="profile.html" class="btn_more r5" >新增</a>

												</div>
										</div>

									</div>
								</article>
							</section>
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
									<li><a href="#" class="icon style2 fa-twitter"><span class="label">Twitter</span></a></li>
									<li><a href="#" class="icon style2 fa-facebook"><span class="label">Facebook</span></a></li>
									<li><a href="#" class="icon style2 fa-instagram"><span class="label">Instagram</span></a></li>
									<li><a href="#" class="icon style2 fa-dribbble"><span class="label">Dribbble</span></a></li>
									<li><a href="#" class="icon style2 fa-github"><span class="label">GitHub</span></a></li>
									<li><a href="#" class="icon style2 fa-500px"><span class="label">500px</span></a></li>
									<li><a href="#" class="icon style2 fa-phone"><span class="label">Phone</span></a></li>
									<li><a href="#" class="icon style2 fa-envelope-o"><span class="label">Email</span></a></li>
								</ul>
							</section>
							<ul class="copyright">
								<li>&copy; Untitled. All rights reserved</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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
			
				var req;
			
				function start(){
					document.getElementById("kind").addEventListener("change", addActivityItem, false);
				}
	
				function addActivityItem(){
					var kind = document.getElementById("kind");
					var url = "/Exchange/response?kind=" + kind.value;
					
					if(window.XMLHttpRequest){
						req = new XMLHttpRequest();
					}
					else if(window.ActiveXObject){
						req = new ActiveXObject("Microsoft.XMLHTTP");
					}
					
					req.open("Get", url, true);
					req.onreadystatechange = callback;
					req.send(null);
				}
				
				function callback(){
					if(req.readyState == 4){
						if(req.status == 200){
							
						}
					}
				}
	
				window.addEventListener("load", start, false);
			
			</script>
	</body>
</html>
