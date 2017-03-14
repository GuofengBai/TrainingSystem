<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>财务状况</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/manager/analysis">统计信息</a></li>
            <li><a href="#">财务状况</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/add">开课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/edit">改课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/payment">付款结算</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/refund">退款审核</a></li>
        </ul>
    </nav>
</div>
<h1>各个机构当前财务状况如下</h1>
<table border="2">
    <tr>
        <td>机构id</td>
        <td>机构名称</td>
        <td>银行卡号</td>
        <td>余额</td>
    </tr>
    <c:forEach items="${ilist}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.bankcard}</td>
            <td>${item.balance}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
