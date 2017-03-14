<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加课程</title>
</head>
<body style="text-align: center">
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/institution/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/course">所有课程</a></li>
            <li><a href="#">新增课程</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/edit">修改信息</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="<%=request.getContextPath()%>/course/add" method="post">
        <table border="2">
            <tr>
                <td>机构id</td>
                <td><input type="text" name="institutionId" value="${institutionId}" readonly="readonly"></td>
            </tr>
            <tr>
                <td>课程名称</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>老师</td>
                <td><input type="text" name="teacher"></td>
            </tr>
            <tr>
                <td>开讲日期</td>
                <td><input type="date" name="startDate"></td>
            </tr>
            <tr>
                <td>结束日期</td>
                <td><input type="date" name="endDate"></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input type="text" name="price"></td>
            </tr>
        </table>
        <input type="submit" value="开课">
    </form>
</div>
</body>
</html>
