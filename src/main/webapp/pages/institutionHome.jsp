<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>机构首页</title>
</head>
<body style="text-align: center">
<div>
    <nav>
        <ul>
            <li><a href="#">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/course">所有课程</a></li>
            <li><a href="<%=request.getContextPath()%>/course/add">新增课程</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/edit">修改信息</a></li>
        </ul>
    </nav>
</div>
<div>
    <table border="2">
        <tr>
            <td>机构id</td>
            <td>${institution.id}</td>
        </tr>
        <tr>
            <td>机构名称</td>
            <td>${institution.name}</td>
        </tr>
        <tr>
            <td>银行卡号</td>
            <td>${institution.bankcard}</td>
        </tr>
        <tr>
            <td>余额</td>
            <td>${institution.balance}</td>
        </tr>
    </table>
</div>
</body>
</html>
