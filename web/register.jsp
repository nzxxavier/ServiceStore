<%--
  Created by IntelliJ IDEA.
  User: NZX
  Date: 2018/7/8
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎使用ServiceStore！</title>
    <meta name="keyword" content="跨界服务,建模工具">
    <meta name="description" content="注册">
    <meta name="author" content="聂之翔">
    <meta name="robot" content="index">
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
    <link rel="stylesheet" href="./WEB-INF/CSS/index.css" type="text/css">
    <script type="text/javascript">
        //刷新验证码
        function changeImg(obj,createTypeFlag){
            document.getElementById(obj.id).src="DrawCodeImage?drawCodeImage="+createTypeFlag+"&"+Math.random();
        }
    </script>
</head>

<body>
<form action="Register" class="content_box" method="post">
    <div class="std_input">
        <input type="text" name="name" placeholder="用户名">
    </div>
    <div class="std_input">
        <input type="password" name="password" placeholder="密码">
    </div>
    <div class="std_input">
        <input type="password" name="rePassword" placeholder="再次输入密码">
    </div>
    <div class="std_input">
        <input type="text" name="mail" placeholder="邮箱">
    </div>
    <div class="check_input">
        <input type="text" name="validateCode" placeholder="验证码">
        <img alt="验证码看不清，换一张" src="DrawCodeImage?createTypeFlag=nl" id="validateCodeImg1" onclick="changeImg(this,'nl')">
    </div>
    <button class="btn_submit">注册</button>
</form>
</body>
</html>
