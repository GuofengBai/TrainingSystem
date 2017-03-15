<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>机构注册</title>
</head>
<body>

<h1>机构注册</h1>
<form action="<%=request.getContextPath()%>/institution/register" method="POST">
    <table>
        <tr>
            <td>名称</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>银行卡</td>
            <td><input type="text" name="bankcard"/></td>
        </tr>
    </table>
    <input type="submit" value="注册"/>
</form>
<a href="<%=request.getContextPath()%>/login">去登陆</a>

</body>
</html>
