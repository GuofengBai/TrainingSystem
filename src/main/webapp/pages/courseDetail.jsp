<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 1:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${course.name}</title>
</head>
<body style="text-align: center">
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/institution/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/course">所有课程</a></li>
            <li><a href="<%=request.getContextPath()%>/course/add">新增课程</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/edit">修改信息</a></li>
        </ul>
    </nav>
</div>
<h1>课程信息</h1>
<div>
    <form action="<%=request.getContextPath()%>/course/edit" method="post">
        <table border="2">
            <tr>
                <td>机构id</td>
                <td><input type="text" name="institutionId" value="${course.institutionId}" readonly="readonly"></td>
            </tr>
            <tr>
                <td>课程id</td>
                <td><input type="text" name="courseId" value="${course.id}" readonly="readonly"></td>
            </tr>
            <tr>
                <td>课程名称</td>
                <td><input type="text" name="name" value="${course.name}"></td>
            </tr>
            <tr>
                <td>老师</td>
                <td><input type="text" name="teacher" value="${course.teacher}"></td>
            </tr>
            <tr>
                <td>开讲日期</td>
                <td><input type="date" name="startDate" value="${course.startTime}"></td>
            </tr>
            <tr>
                <td>结束日期</td>
                <td><input type="date" name="endDate" value="${course.endTime}"></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input type="text" name="price" value="${course.price}"></td>
            </tr>
        </table>
        <input type="submit" value="修改">
    </form>
</div>
<h1>成绩录入</h1>
<div>
    <form action="<%=request.getContextPath()%>/institution/grades" method="post">
        <table border="2">
            <tr>
                <td>学员id</td>
                <td><input type="text" name="studentId"></td>
            </tr>
            <tr>
                <td>成绩</td>
                <td><input type="text" name="grades"></td>
            </tr>
        </table>
        <input type="hidden" name="courseId" value="${course.id}">
        <input type="submit" value="录入成绩">
    </form>
</div>
<h1>成绩记录</h1>
<table border="2">
    <tr>
    <td>学生id</td>
    <td>学生姓名</td>
    <td>成绩</td>
    </tr>
    <c:forEach items="${grades}" var="item">
        <tr>
            <td>${item.studentId}</td>
            <td>${item.studentName}</td>
            <td>${item.grades}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
