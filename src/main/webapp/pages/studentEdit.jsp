<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息编辑</title>
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
            <li><a href="#">修改信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/charge">充值</a></li>
        </ul>
    </nav>
</div>
<div>
    <form action="<%=request.getContextPath()%>/student/edit" method="post">
        <table border="2">
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name" value="${student.name}"></td>
            </tr>
            <tr>
                <td>银行卡号</td>
                <td><input type="text" name="bankcard" value="${student.bankCard}"></td>
            </tr>
            <tr>
                <td>余额</td>
                <td><input type="text" readonly="readonly" value="${student.balance}"></td>
            </tr>
            <tr>
                <td>等级</td>
                <td><input type="text" readonly="readonly" value="${student.level}"></td>
            </tr>
            <tr>
                <td>点数</td>
                <td><input type="text" readonly="readonly" value="${student.point}"></td>
            </tr>
        </table>
        <input type="submit" value="更改">
    </form>
</div>
</body>
</html>
