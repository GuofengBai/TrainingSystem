<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>充值</title>
</head>
<body style="text-align: center">
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/student/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/course/select">选课</a></li>
            <li><a href="<%=request.getContextPath()%>/course/drop">退课</a></li>
            <li><a href="<%=request.getContextPath()%>/student/consume">消费</a></li>
            <li><a href="<%=request.getContextPath()%>/student/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/edit">修改信息</a></li>
            <li><a href="#">充值</a></li>
        </ul>
    </nav>
</div>
<form action="<%=request.getContextPath()%>/student/charge" method="post">
    <table border="2">
        <tr>
            <td>余额</td>
            <td>${student.balance}</td>
        </tr>
        <tr>
            <td>等级</td>
            <td>${student.level}</td>
        </tr>
        <tr>
            <td>点数</td>
            <td>${student.point}</td>
        </tr>
        <tr>
            <td>当前可享折扣</td>
            <td>${student.discount()}</td>
        </tr>
        <tr>
            <td>上次充值日期</td>
            <td>${student.lastChargeDate}</td>
        </tr>
        <tr>
            <td>确定充值金额</td>
            <td><input type="text" name="amount"></td>
        </tr>
    </table>
    <input type="submit">
</form>
</body>
</html>
