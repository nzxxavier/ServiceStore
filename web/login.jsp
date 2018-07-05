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
</head>
<body>
<form name="loginForm" class = "loginForm" id="loginForm" action="/Login" method="post">
    <div class="inputName">
        <input name="name" id="name" value="" class="txt" type="text" placeholder="用户名">
    </div>
    <div class="passwordInput">
        <input name="password" id="password" class="txt" type="password" placeholder="密码">
        <a href="forget">忘记密码</a>
    </div>
    <div class="login">
        <button id="btn_Login" class="btn_Login">立即登录</button>
    </div>
</form>
</body>
</html>
