<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>退款审核</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/manager/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/finance">财务状况</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/add">开课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/edit">改课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/payment">付款结算</a></li>
            <li><a href="#">退款审核</a></li>
        </ul>
    </nav>
</div>
<h1>学生退款单列表</h1>
<table border="2">
    <tr>
        <td>退款单id</td>
        <td>学生id</td>
        <td>课程id</td>
        <td>机构id</td>
        <td>金额</td>
        <td>选择</td>
    </tr>
    <c:forEach items="${refund}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.studentId}</td>
            <td>${item.courseId}</td>
            <td>${item.institutionId}</td>
            <td>${item.amount}</td>
            <td><input type="checkbox" name="chk" value="${item.id}"/></td>
        </tr>
    </c:forEach>
</table>
<button id="actionBtn">批准</button>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $('#actionBtn').bind("click", function () {
            var list = "";
            $('input[type="checkbox"][name="chk"]:checked').each(
                function () {
                    list = list + "," + $(this).val();
                }
            );
            list = list.substr(1);

            $.ajax("<%=request.getContextPath()%>/manager/refund", {
                type: 'POST',
                data: {array: list},
                datatype: "text",
                success: function (result) {
                    location.reload(true);
                },
                error: function (result) {
                    location.reload(true);
                }
            });
        });
    });
</script>
</body>
</html>
