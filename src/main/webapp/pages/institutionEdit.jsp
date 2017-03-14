<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/institution/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/course">所有课程</a></li>
            <li><a href="<%=request.getContextPath()%>/course/add">新增课程</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/analysis">统计信息</a></li>
            <li><a href="#">修改信息</a></li>
            <li><a href="<%=request.getContextPath()%>/institution/logout">登出</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="<%=request.getContextPath()%>/institution/edit" method="post">
        <table border="2">
            <tr>
                <td>机构id</td>
                <td><input type="text" value="${institution.id}" readonly="readonly"></td>
            </tr>
            <tr>
                <td>机构名称</td>
                <td><input type="text" name="name" value="${institution.name}"></td>
            </tr>
            <tr>
                <td>银行卡号</td>
                <td><input type="text" name="bankcard" value="${institution.bankcard}"></td>
            </tr>
            <tr>
                <td>余额</td>
                <td><input type="text" value="${institution.balance}" readonly="readonly"></td>
            </tr>
        </table>
        <input type="submit" value="更改">
    </form>
</div>
</body>
</html>
