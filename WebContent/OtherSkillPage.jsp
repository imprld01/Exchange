<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!--
·   ···  ·  ·  ··
·   ·  · ·  · ·  ·
··· ···   ··   ··
-->
<head>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=no,minimal-ui" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!--favicon??-->
<link rel="icon" href="_skill/images/favicon.png" type="image/x-icon" />
<link rel="shortcut icon" href="_skill/images/favicon.png"
	type="image/x-icon" />

<!--ios??添加到主屏幕的??-->
<link rel="apple-touch-icon-precomposed"
	href="_skill/images/apple-touch-icon-precomposed.png">

<!--loading css-->
<link href="_skill/css/pace-theme-flash.css" rel="stylesheet"
	type="text/css">

<!--全局定?css-->
<link href="_skill/css/style.css" rel="stylesheet">

<!--自适?css-->
<link href="_skill/css/responsive.css" rel="stylesheet">

<!--字体??cdd-->
<link href="_skill/css/font.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="_skill/css/evaluation.css">
<script language="JavaScript">
history.go(+1)
</script>
<title>配對頁面</title>
</head>
<body>

	<!--print button-->
	<div class="main">

		<div class="content">
			<!--按鈕在這裡!!-->

			<!--header-->
			<div class="header clearfix">
				<div class="avatar">
					<a href="javascript:;" class="avatar_pic circle"><em
						class="circle doing">尚未配對</em></a> <b class="doing r5">${skill.skillLevel}</b>
				</div>
				<div class="base">

					<p class="desc">Hello my skill is</p>
					<h2>${kindName}</h2>
					<p>${skill.type.typeName}</p>
					<span>
						<p>
							<i aria-hidden="true" data-icon="&#xe01d;"></i>在${region}
						</p> <!-- &#xe036 板手 --> <br>
						 <p><a class="button" href="#popup2"><i aria-hidden="true" data-icon="&#xe031;"></i>評價</a></p>
                              <div id="popup2" class="overlay">
                                <div class="popup">
                                  <p >評價</p>
                                  <a class="close" href="#">&times;</a>

                                  <ul class="meter" id ="test1">
                                    <p>教學態度</p>
                                    <li data-value="1"></li>
                                    <li data-value="2"></li>
                                    <li data-value="3"></li>
                                    <li data-value="4"></li>
                                    <li data-value="5"></li>
                                  </ul>
                                  <ul class="meter" id ="test2">
                                    <p>技能程度</p>
                                    <li data-value="1"></li>
                                    <li data-value="2"></li>
                                    <li data-value="3"></li>
                                    <li data-value="4"></li>
                                    <li data-value="5"></li>
                                  </ul>
                                  <ul class="meter" id ="test3">
                                    <p>教學技巧</p>
                                    <li data-value="1"></li>
                                    <li data-value="2"></li>
                                    <li data-value="3"></li>
                                    <li data-value="4"></li>
                                    <li data-value="5"></li>
                                  </ul>
                                  <ul class="meter" id ="test4">
                                    <p>教學頻率</p>
                                    <li data-value="1"></li>
                                    <li data-value="2"></li>
                                    <li data-value="3"></li>
                                    <li data-value="4"></li>
                                    <li data-value="5"></li>
                                  </ul>
                                  <ul class="meter" id ="test5">
                                    <p>整體滿意度</p>
                                    <li data-value="1"></li>
                                    <li data-value="2"></li>
                                    <li data-value="3"></li>
                                    <li data-value="4"></li>
                                    <li data-value="5"></li>
                                  </ul>
                                  <br>
	                                  <form method="post" action="Evaluation.do">
	                                  <textarea id="TextArea" name="comment" placeholder="請輸入評論" required></textarea>
	                                  <input type="hidden" value="1" name="atd" id="atd">
	                                  <input type="hidden" value="1" name="pfn" id="pfn">
	                                  <input type="hidden" value="1" name="tch" id="tch">
	                                  <input type="hidden" value="1" name="fqc" id="fqc">
	                                  <input type="hidden" value="1" name="sfn" id="sfn">
	                                  <input type="hidden" value="${myId}" name="my">
	                                  <input type="hidden" value="${skill.skillId}" name="other">
	                                 <input type="submit" style="position:absolute;bottom:10%;" class="btn_more r5" value="確認">
									</intput>
								</form>
                                </div>
                            </div>
					 		<a	href="Exchange.do#EXCHANGE" class="btn_exit r5" d>Back ></a>

					</span>

				</div>
			</div>

		<!--information-->
		<div class="information clearfix">

				<!--left-->
				<div class="info_area info_left info_content">

					<!-- 修改icon -->
					<h5>技能的簡介/經歷</h5>

					<p class="desc">${skill.intorExpr}</p>


					<h5>我的證照/影片</h5>
					<ul>
						<c:forEach var="video" items="${skill.video}" varStatus="vedioLoop">

							<li class="clearfix" style="position: relative; z-index: 5;">
								<div class="con">
									<h3>
										<i aria-hidden="true" data-icon="&#x5b;"></i>影片 - ${vedioLoop.getCount()}</em>
									</h3>
									<p></p>
								</div> <a href="javascript:;" class="btn_go btn_green product_1 r5">來看看<i
									aria-hidden="true" data-icon="&#x35;"></i></a>
							</li>

						</c:forEach>


						<c:forEach var="image" items="${skill.image}" varStatus="imageLoop">
							<li class="clearfix" style="position: relative; z-index: 1">
								<div class="con">
									<h3>
										<i aria-hidden="true" data-icon="&#x5b;"></i>證照/獎狀 - ${imageLoop.getCount()}</em>
									</h3>
									<p></p>
								</div> <a href="javascript:;" class="btn_go btn_green product_2 r5">來看看<i
									aria-hidden="true" data-icon="&#x35;"></i></a>
							</li>
						</c:forEach>
					</ul>

				</div>

				<!--right-->
				<div class="info_area info_right info_timeline">
					<h5>評價</h5>

					<dl class="clearfix">
						<dt>教學態度</dt>
						<dd>
							<p>
							<div id="star" style="float: right;">


							<c:forEach var="num" begin="1" end="5" step="1">
								<c:choose>
									<c:when test="${skill.times == 0}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num<=skill.score.attitude/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num>skill.score.attitude/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>									
								</c:choose>	
							</c:forEach>


								<div>

									</p>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>技能程度</dt>
						<dd>
							<p>
							<div id="star" style="float: right;">
							
							<c:forEach var="num" begin="1" end="5" step="1">
								<c:choose>
									<c:when test="${skill.times == 0}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num<=skill.score.profession/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num>skill.score.profession/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>									
								</c:choose>	
							</c:forEach>
									
									
								<div>
									</p>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>教學技巧</dt>
						<dd>
							<p>
							<div id="star" style="float: right;">
							
							<c:forEach var="num" begin="1" end="5" step="1">
								<c:choose>
									<c:when test="${skill.times == 0}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num<=skill.score.teaching/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num>skill.score.teaching/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>									
								</c:choose>	
							</c:forEach>
									
								<div>
									</p>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>教學頻率</dt>
						<dd>
							<p>
							<div id="star" style="float: right;">
							
							
							<c:forEach var="num" begin="1" end="5" step="1">
								<c:choose>
									<c:when test="${skill.times == 0}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num<=skill.score.frequency/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num>skill.score.frequency/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>									
								</c:choose>	
							</c:forEach>
									
									
									
								<div>
									</p>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>整體滿意度</dt>
						<dd>
							<p>
							<div id="star" style="float: right;">
							
							
							
							<c:forEach var="num" begin="1" end="5" step="1">
								<c:choose>
								
									<c:when test="${skill.times == 0}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num<=skill.score.satisfication/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>
									<c:when test="${num>skill.score.satisfication/skill.times}">
												<c:choose>
													<c:when test="${num == 5}">
														<img src="_skill/images/star_.png">
													</c:when>
													
													<c:when test="${num != 5}">
														<img src="_skill/images/star_.png">&nbsp&nbsp
													</c:when>
													
												</c:choose>
									</c:when>									
								</c:choose>	
							</c:forEach>
									
									
									
								<div>
									</p>
						</dd>
					</dl>
					
					
					<div class="info_area info info_content">
						<br>
						<c:forEach var="comment" items="${skill.comment}" varStatus="commentLoop">
							<p class="desc s5">${commentLoop.getCount()}. ${comment.comment} [${comment.date}]</p>
			
						</c:forEach>
						
					</div>
				</div>

			</div>
		</div>
		<!--配對按鈕-->

		<div class="footer clearfix">
			<!--   <p style="float:right;">已為您搜尋到一張匹配的技能卡 OUO<p>-->
		</div>

		<!--?人基本信息-->
		<div class="tip_avatar">
			<a href="javascript:;" class="tip_close" aria-hidden="true"
				data-icon="&#x4d;"></a>
			<div class="tip_avatar_con">
				<div class="tip_right">
					<p class="en mt">Hello my skill is</p>
					<h2>電子競技</h2>
					<p>League of Legends</p>
					<p class="mt20">暱稱</p>
					<p>
						<i aria-hidden="true" data-icon="&#xe010;"></i>Faker
					</p>
					<p>
						<i aria-hidden="true" data-icon="&#xe00b;"></i>加好友之後才看得到
					</p>

				</div>
				<!--如果依然使用??像?不要?掉()里的?容-->
				<!-- <p class="desc">Cartoon just like me (Design by Jameskelly)</p> -->
			</div>
		</div>
		<div class="tip_avatar_bg"></div>

		<!--列出證書-->
		<div class="tip_product sd">

			<!--??作品展示-->
			<a href="javascript:;" class="r5 tip_area_close" aria-hidden="true"
				data-icon="&#x4d;"></a>

			<div id="tip_carousel" class="carousel slide" data-ride="carousel">

				<!-- 作品?域 -->
				<div class="carousel-inner">

					<!--作品1-->
					<div class="item active">

						<div class="banner product_h1">
							<div class="logo r5"></div>
							<div class="banner_bg"></div>
						</div>
						<div class="descprition">
							<h3>
								<i aria-hidden="true" data-icon="&#xe0e6;"></i>影片
							</h3>
							
							<p class="pic">
								<iframe width="560" height="315"
									src="https://www.youtube.com/embed/hjwR_S8WRjQ" frameborder="0"
									allowfullscreen></iframe>
								<span></span>
							</p>
						</div>

					</div>

					<!--作品2-->
					<div class="item">

						<div class="banner product_h2">
							<div class="logo r5"></div>
							<div class="banner_bg"></div>
						</div>
						<div class="descprition">
							<h3>
								<i aria-hidden="true" data-icon="&#xe013;">證書/獎狀</i>
							</h3>
							<p></p>
							<p class="pic">
								<img src="_skill/images/win.png" alt="首?" /> <span></span>
							</p>

						</div>

					</div>


				</div>

				<!-- 左右控制 -->
				<a class="carousel-control left" href="#tip_carousel"
					data-slide="prev"> <i aria-hidden="true" data-icon="&#x34;"></i>
				</a> <a class="carousel-control right" href="#tip_carousel"
					data-slide="next"> <i aria-hidden="true" data-icon="&#x35;"></i>
				</a>

			</div>


		</div>



		<!--Jquery 2.1 不支持ie6、7、8-->
		<script type="text/javascript" src="_skill/js/jquery-2.1.1.min.js"></script>

		<!--jquery 局部打印-->
		<script type="text/javascript" src="_skill/js/jQuery.print.js"></script>

		<!--jquery ??-->
		<script type="text/javascript" src="_skill/js/jquery.transit.min.js"></script>

		<!--jquery ?播-基于bootstrap carousel-->
		<script type="text/javascript" src="_skill/js/carousel.min.js"></script>

		<!--jquery annyang sound control-->
		<script type="text/javascript" src="_skill/js/annyang.min.js"></script>

		<!--jquery loadding-->
		<script type="text/javascript" src="_skill/js/pace.min.js"></script>

		<!--effect-->
		<script type="text/javascript" src="_skill/js/common.js"></script>
		<script>
			
			$("#test1 li").on("click", function() {
				var meterItems = $("#test1 li");
				var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
				var arr = $.makeArray(meterItems); // Had to create array of <li> to loop through them

				meterItems.removeClass("active"); // Clears prior active states
				 $("#atd").val(targetValue);
				for (i = 0; i <= targetValue - 1; i++) {
					$(arr[i]).addClass("active");
				}

			});
			$("#test2 li").on("click", function() {
				var meterItems = $("#test2 li");
				var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
				var arr = $.makeArray(meterItems); // Had to create array of <li> to loop through them

				meterItems.removeClass("active"); // Clears prior active states
				$("#pfn").val(targetValue);
				for (i = 0; i <= targetValue - 1; i++) {
					$(arr[i]).addClass("active");
				}

			});
			$("#test3 li").on("click", function() {
				var meterItems = $("#test3 li");
				var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
				var arr = $.makeArray(meterItems); // Had to create array of <li> to loop through them

				meterItems.removeClass("active"); // Clears prior active states
				$("#tch").val(targetValue);
				for (i = 0; i <= targetValue - 1; i++) {
					$(arr[i]).addClass("active");
				}

			});
			$("#test4 li").on("click", function() {
				var meterItems = $("#test4 li");
				var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
				var arr = $.makeArray(meterItems); // Had to create array of <li> to loop through them

				meterItems.removeClass("active"); // Clears prior active states
				$("#fqc").val(targetValue);
				for (i = 0; i <= targetValue - 1; i++) {
					$(arr[i]).addClass("active");
				}

			});
			$("#test5 li").on("click", function() {
				var meterItems = $("#test5 li");
				var targetValue = $(this).data("value"); // Find value of target item. Used below as parameter in loop
				var arr = $.makeArray(meterItems); // Had to create array of <li> to loop through them

				meterItems.removeClass("active"); // Clears prior active states
				$("#sfn").val(targetValue);
				for (i = 0; i <= targetValue - 1; i++) {
					$(arr[i]).addClass("active");
				}

			});
		</script>
</body>
</html>
