<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>统计信息</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/student/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/course/select">选课</a></li>
            <li><a href="<%=request.getContextPath()%>/course/drop">退课</a></li>
            <li><a href="<%=request.getContextPath()%>/student/consume">消费</a></li>
            <li><a href="#">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/edit">修改信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/charge">充值</a></li>
            <li><a href="<%=request.getContextPath()%>/student/logout">登出</a></li>
        </ul>
    </nav>
</div>
<h1>您已选择且未退出下列课程</h1>
<table border="2">
    <tr>
    <td>课程id</td>
    <td>课程名称</td>
    <td>机构id</td>
    <td>老师</td>
    <td>开讲时间</td>
    <td>结束时间</td>
    <td>价格</td>
    </tr>
    <c:forEach items="${enrolled}" var="eitem">
        <tr>
            <td>${eitem.id}</td>
            <td>${eitem.name}</td>
            <td>${eitem.institutionId}</td>
            <td>${eitem.teacher}</td>
            <td>${eitem.startTime}</td>
            <td>${eitem.endTime}</td>
            <td>${eitem.price}</td>
        </tr>
    </c:forEach>
</table>
<h1>您已退出下列课程</h1>
<table border="2">
    <tr>
    <td>课程id</td>
    <td>课程名称</td>
    <td>机构id</td>
    <td>老师</td>
    <td>开讲时间</td>
    <td>结束时间</td>
    <td>价格</td>
    </tr>
    <c:forEach items="${droped}" var="ditem">
        <tr>
            <td>${ditem.id}</td>
            <td>${ditem.name}</td>
            <td>${ditem.institutionId}</td>
            <td>${ditem.teacher}</td>
            <td>${ditem.startTime}</td>
            <td>${ditem.endTime}</td>
            <td>${ditem.price}</td>
        </tr>
    </c:forEach>
</table>
<h1>您已有下列消费记录</h1>
<table border="2">
    <tr>
    <td>消费记录id</td>
    <td>课程id</td>
    <td>机构id</td>
    <td>金额</td>
    </tr>
    <c:forEach items="${payment}" var="pitem">
        <tr>
            <td>${pitem.id}</td>
            <td>${pitem.courseId}</td>
            <td>${pitem.institutionId}</td>
            <td>${pitem.amount}</td>
        </tr>
    </c:forEach>
</table>
<h1>您已有下列退款记录</h1>
<table border="2">
    <tr>
    <td>退款记录id</td>
    <td>课程id</td>
    <td>机构id</td>
    <td>金额</td>
    </tr>
    <c:forEach items="${refund}" var="ritem">
        <tr>
            <td>${ritem.id}</td>
            <td>${ritem.courseId}</td>
            <td>${ritem.institutionId}</td>
            <td>${ritem.amount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
