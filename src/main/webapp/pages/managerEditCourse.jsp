<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/14
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>改课申请</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/manager/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/finance">财务状况</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/add">开课申请</a></li>
            <li><a href="#">改课申请</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/payment">付款结算</a></li>
            <li><a href="<%=request.getContextPath()%>/manager/refund">退款审核</a></li>
        </ul>
    </nav>
</div>
<h1>改课申请列表</h1>
<table border="2">
    <tr>
        <td>课程id</td>
        <td>课程名称</td>
        <td>机构id</td>
        <td>老师</td>
        <td>开讲时间</td>
        <td>结束时间</td>
        <td>价格</td>
    <tr>选择</tr>
    </th>
    <c:forEach items="${editList}" var="item">
        <tr>
            <td>${item.courseId}</td>
            <td>${item.name}</td>
            <td>${item.institutionId}</td>
            <td>${item.teacher}</td>
            <td>${item.startTime}</td>
            <td>${item.endTime}</td>
            <td>${item.price}</td>
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

            $.ajax("<%=request.getContextPath()%>/manager/edit", {
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
