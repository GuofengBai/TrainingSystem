<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/institution/home">基本信息</a></li>
            <li><a href="#">所有课程</a></li>
            <li><a href="<%=request.getContextPath()%>/course/add">新增课程</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/edit">修改信息</a></li>
        </ul>
    </nav>
</div>
<table border="2">
    <th>
    <td>课程id</td>
    <td>课程名称</td>
    <td>老师</td>
    <td>开讲时间</td>
    <td>结束时间</td>
    <td>价格</td>
    </th>
    <c:forEach items="${course}" var="item">
        <tr>
            <td><a href="<%=request.getContextPath()%>/institution/course/${item.id}">${item.id}</a></td>
            <td>${item.name}</td>
            <td>${item.teacher}</td>
            <td>${item.startTime}</td>
            <td>${item.endTime}</td>
            <td>${item.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
