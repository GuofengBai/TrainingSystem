<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生首页</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="#">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/course/select">选课</a></li>
            <li><a href="<%=request.getContextPath()%>/course/drop">退课</a></li>
            <li><a href="<%=request.getContextPath()%>/student/consume">消费</a></li>
            <li><a href="<%=request.getContextPath()%>/student/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/edit">修改信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/charge">充值</a></li>
            <li><a href="<%=request.getContextPath()%>/student/logout">登出</a></li>
        </ul>
    </nav>
</div>
<div>
    <table border="2">
        <tr>
            <td>学生id</td>
            <td>${student.id}</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>${student.name}</td>
        </tr>
        <tr>
            <td>银行卡号</td>
            <td>${student.bankCard}</td>
        </tr>
        <tr>
            <td>余额</td>
            <td>${student.balance}</td>
        </tr>
        <tr>
            <td>等级</td>
            <td>${student.level}</td>
        </tr>
        <tr>
            <td>积分</td>
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
            <td>可兑换点数</td>
            <td>${student.historyPoint}</td>
        </tr>
    </table>
</div>
</body>
</html>
