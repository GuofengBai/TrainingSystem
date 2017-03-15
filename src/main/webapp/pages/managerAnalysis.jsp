<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>统计信息</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="#">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/finance">财务状况</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/add">开课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/edit">改课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/payment">付款结算</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/refund">退款审核</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/logout">登出</a></li>
        </ul>
    </nav>
</div>
<h1>各个机构招生情况如下</h1>
<table border="2">
    <tr>
        <td>机构id</td>
        <td>机构名称</td>
        <td>学生数量</td>
    </tr>
    <c:forEach items="${scount}" var="citem">
        <tr>
            <td>${citem.institutionId}</td>
            <td>${citem.institutionName}</td>
            <td>${citem.studentCount}</td>
        </tr>
    </c:forEach>
</table>
<h1>所有学生学习情况如下</h1>
<table border="2">
    <tr>
        <td>学生id</td>
        <td>学生姓名</td>
        <td>课程id</td>
        <td>课程名称</td>
        <td>成绩</td>
    </tr>
    <c:forEach items="${sgrades}" var="gitem">
        <tr>
            <td>${gitem.studentId}</td>
            <td>${gitem.studentName}</td>
            <td>${gitem.courseId}</td>
            <td>${gitem.courseName}</td>
            <td>${gitem.grades}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
