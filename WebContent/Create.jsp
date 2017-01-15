<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import = "exchange.model.skill.Type,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--
·   ···  ·  ·  ··
·   ·  · ·  · ·  ·
··· ···   ··   ··
-->
    <head>
        <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,minimal-ui" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <!--favicon图标-->
        <link rel="icon" href="_exchange/images/favicon.png" type="image/x-icon" />
        <link rel="shortcut icon" href="_exchange/images/favicon.png" type="image/x-icon" />

        <!--ios设备添加到主屏幕的图标-->
        <link rel="apple-touch-icon-precomposed" href="_exchange/images/apple-touch-icon-precomposed.png">

        <!--loading css-->
        <link href="_exchange/css/pace-theme-flash.css" rel="stylesheet" type="text/css">

        <!--全局定义css-->
        <link href="_exchange/css/style.css" rel="stylesheet">

        <!--自适应css-->
        <link href="_exchange/css/responsive.css" rel="stylesheet">

        <!--字体图标cdd-->
        <link href="_exchange/css/font.css" rel="stylesheet" type="text/css">

        <title>Profile</title>
    </head>
    <body>

        <!--print button-->
        <div class="main">

            <div class="content">
			<!--按鈕在這裡!!-->

                <!--header-->
                <div class="header clearfix">
                    <div class="avatar">
                        <a href="javascript:;" class="avatar_pic circle"><em class="circle doing">Justin</em></a>
                        <b class="doing r5">技能</b>
                    </div>
                	<div class="base">
                        <p class="desc">Hello my skill is</p>
						<form method="get" action="Skill.do" >
							<select id="kind"  name="kind" style="width:10%; text-align:center;" >
							  	<c:forEach var="kind" items="${kinds}">
										<option value="${kind.kindCode}">${kind.kindName}</option>
								</c:forEach>
							</select>
						
	                        <select id="type" name="type"  style="width:10%;">
							  <option value="volvo">請選擇類別</option>
	
							</select>
	                        <span>
	                            <p><i aria-hidden="true" data-icon="&#xe01d;"></i>在基隆市</p>
	                            <!-- <p><i aria-hidden="true" data-icon="&#xe031;"></i>評價</p> -->
	                            <br>
	                            <input type="hidden" value="0" name="mark">
	                            <input value="Submit" type="submit" class="btn_next r5" >
	                            <a href="Home.do#PROFILE" class="btn_exit r5" d>Cancel</a>
	                        </span>
                    </div>
                </div>

                <!--information-->
                <div class="information clearfix">

                    <!--left-->
                    <div class="info_area info_left info_content">

                        <h5>技能的簡介/<i aria-hidden="true" data-icon="&#x7d;"></i>經歷</h5>
						<textarea cols="50" rows="20" name="introExper"  placehoder = "輸入技能簡介及經歷..."></textarea>
                        <h5>我的證照/影片</h5>
                        輸入連結以嵌入影片 <input type="text"  name="video1"> <img src="_exchange/images/plus.png" style="margin-top:2px;"><br>
						輸入連結以嵌入圖片 <input type="text"  name="image1"> <img src="_exchange/images/plus.png" style="margin-top:2px;"><br>

                    </div>
					</form>
                    <!--right-->
                    <div class="info_area info_right info_timeline">
                        <h5>評價</h5>
                        <dl class="clearfix">
                            <dt>分數</dt>
                            <dd>
                              <p>
                                <div id="star"><img src="_exchange/images/star_.png"><img src="_exchange/images/star_.png"><img src="_exchange/images/star_.png"><img src="_exchange/images/star_.png"><img src="_exchange/images/star_.png"><div>
                              </p>
                            </dd>
                        </dl>
                        <div class="info_area info info_content">
                            <br>
                            <p class="desc s5">評語區</p>

                        </div>
                </div>

            </div>
        </div>
		<!--配對按鈕-->

        <div class="footer clearfix">
            <p style="float:right;">已為您搜尋到一張匹配的技能卡 OUO<p>
        </div>

        <!--个人基本信息-->
        <div class="tip_avatar">
            <a href="javascript:;" class="tip_close" aria-hidden="true" data-icon="&#x4d;"></a>
            <div class="tip_avatar_con">
                <div class="tip_right">
                    <p class="en mt">Hello my skill is</p>
                    <h2>電子競技</h2>
                    <p>League of Legends</p>
                    <p class="mt20">暱稱</p>
                    <p><i aria-hidden="true" data-icon="&#xe010;"></i>Faker</p>
                    <p><i aria-hidden="true" data-icon="&#xe00b;"></i>加好友之後才看得到</p>

                </div>
                <!--如果依然使用该头像请不要删掉()里的内容-->
                <!-- <p class="desc">Cartoon just like me (Design by Jameskelly)</p> -->
            </div>
        </div>
        <div class="tip_avatar_bg"></div>

        <!--作品内容-->
        <div class="tip_product sd">

            <!--关闭作品展示-->
            <a href="javascript:;" class="r5 tip_area_close" aria-hidden="true" data-icon="&#x4d;"></a>

            <div id="tip_carousel" class="carousel slide" data-ride="carousel">

                <!-- 作品区域 -->
                <div class="carousel-inner">

                    <!--作品1-->
                    <div class="item active">

                        <div class="banner product_h1">
                            <div class="logo r5"></div>
                            <div class="banner_bg"></div>
                        </div>
                        <div class="descprition">
                            <h3><i aria-hidden="true" data-icon="&#xe0e6;"></i>中路對線教學</h3>
                            <p>基礎篇 blablablabla</p>
                            <p class="pic">
                                <iframe width="560" height="315" src="https://www.youtube.com/embed/hjwR_S8WRjQ" frameborder="0" allowfullscreen></iframe>
                                <span>中路教學</span>
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
                            <h3><i aria-hidden="true" data-icon="&#xe013;"></i>S5世界冠軍</h3>
                            <p>用雷茲就贏啦RRR</p>
                            <p class="pic">
                                <img src="_exchange/images/win.png" alt="首页" />
                                <span>證書????</span>
                            </p>

                        </div>

                    </div>


                </div>

                <!-- 左右控制 -->
                <a class="carousel-control left" href="#tip_carousel" data-slide="prev">
                    <i aria-hidden="true" data-icon="&#x34;"></i>
                </a>
                <a class="carousel-control right" href="#tip_carousel" data-slide="next">
                    <i aria-hidden="true" data-icon="&#x35;"></i>
                </a>

            </div>


        </div>

        <!--Jquery 2.1 不支持ie6、7、8-->
        <script type="text/javascript" src="_exchange/js/jquery-2.1.1.min.js"></script>

        <!--jquery 局部打印-->
        <script type="text/javascript" src="_exchange/js/jQuery.print.js"></script>

        <!--jquery 动画-->
        <script type="text/javascript" src="_exchange/js/jquery.transit.min.js"></script>

        <!--jquery 轮播-基于bootstrap carousel-->
        <script type="text/javascript" src="_exchange/js/carousel.min.js"></script>

        <!--jquery annyang sound control-->
        <script type="text/javascript" src="_exchange/js/annyang.min.js"></script>

        <!--jquery loadding-->
        <script type="text/javascript" src="_exchange/js/pace.min.js"></script>

        <!--effect-->
        <script type="text/javascript" src="_exchange/js/common.js"></script>
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