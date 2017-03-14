<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>统计信息</title>
</head>
<body style="text-align: center">
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/institution/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/course">所有课程</a></li>
            <li><a href="<%=request.getContextPath()%>/course/add">新增课程</a></li>
            <li><a href="#">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/edit">修改信息</a></li>
        </ul>
    </nav>
</div>
<h1>你的机构有如下选课记录</h1>
<table border="2">
    <tr>
    <td>课程id</td>
    <td>学生id</td>
    </tr>
    <c:forEach items="${enrolled}" var="eitem">
        <tr>
            <td>${eitem.courseId}</td>
            <td>${eitem.studentId}</td>
        </tr>
    </c:forEach>
</table>
<h1>你的机构有如下退课记录</h1>
<table border="2">
    <tr>
    <td>课程id</td>
    <td>学生id</td>
    </tr>
    <c:forEach items="${droped}" var="ditem">
        <tr>
            <td>${ditem.courseId}</td>
            <td>${ditem.studentId}</td>
        </tr>
    </c:forEach>
</table>
<h1>你的机构有如下付款记录</h1>
<table border="2">
    <th>
    <td>付款单id</td>
    <td>课程id</td>
    <td>学生id</td>
    <td>金额</td>
    </th>
    <c:forEach items="${payment}" var="pitem">
        <tr>
            <td>${pitem.id}</td>
            <td>${pitem.courseId}</td>
            <td>${pitem.studentId}</td>
            <td>${pitem.amount}</td>
        </tr>
    </c:forEach>
</table>
<h1>你的机构有如下退款记录</h1>
<table border="2">
    <th>
    <td>退款单id</td>
    <td>课程id</td>
    <td>学生id</td>
    <td>金额</td>
    </th>
    <c:forEach items="${refund}" var="ritem">
        <tr>
            <td>${ritem.id}</td>
            <td>${ritem.courseId}</td>
            <td>${ritem.studentId}</td>
            <td>${ritem.amount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
