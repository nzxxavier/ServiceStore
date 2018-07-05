<%--
  Created by IntelliJ IDEA.
  User: NZX_Xavier
  Date: 2018/7/5
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="./WEB-INF/CSS/index.css" type="text/css">
    <script type="text/javascript">
        //刷新验证码
        function changeImg(obj,createTypeFlag){
            document.getElementById(obj.id).src="DrawCodeImage?drawCodeImage="+createTypeFlag+"&"+Math.random();
        }
    </script>
</head>
<body>
<form action="Login" method="post">
    <input type="text" name="name" placeholder="用户名">
    <br>
    <input type="password" name="password" placeholder="密码">
    <br>
    <input type="text" name="validateCode" placeholder="验证码">
    <br>
    <img alt="验证码看不清，换一张" src="DrawCodeImage?createTypeFlag=nl" id="validateCodeImg1" onclick="changeImg(this,'nl')">
    <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
