<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>消费</title>
</head>
<body>
<div>
    <nav>
        <ul>
            <li><a href="<%=request.getContextPath()%>/student/home">基本信息</a></li>
            <li><a href="<%=request.getContextPath()%>/course/select">选课</a></li>
            <li><a href="<%=request.getContextPath()%>/course/drop">退课</a></li>
            <li><a href="<%=request.getContextPath()%>/student/consume">消费</a></li>
            <li><a href="<%=request.getContextPath()%>/student/analysis">统计信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/edit">修改信息</a></li>
            <li><a href="<%=request.getContextPath()%>/student/charge">充值</a></li>
        </ul>
    </nav>
</div>
<table border="2">
    <th>
    <td>消费记录id</td>
    <td>课程id</td>
    <td>机构id</td>
    <td>金额</td>
    <td>选择</td>
    </th>
    <c:forEach items="${toConsume}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.courseId}</td>
            <td>${item.institutionId}</td>
            <td>${item.amount}</td>
            <td><input type="checkbox" name="chk" value="${item.id}+':'+${item.studentId}+':'+
            ${item.courseId}+':'+${item.institutionId}+':'+${item.amount}+':'+${item.state}"/></td>
        </tr>
    </c:forEach>
</table>
<button id="actionBtn">支付</button>
<script src="../static/js/jquery.min.js"></script>
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

            $.ajax("/student/consume", {
                type: 'POST',
                data: {array: list},
                success: function (result) {
                    alert(result.msg);
                    location.reload(true);
                },
                error: function (result) {
                    alert(result.msg);
                    location.reload(true);
                }
            });
        });
    });
</script>
</body>
</html>
