<%--
  Created by IntelliJ IDEA.
  User: baiguofeng
  Date: 2017/3/13
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>登录</title>
    <style type="text/css">
        * {
            padding: 0;
            margin: 0;
        }

        button {
            background-color: beige;
            width: 80px;
            height: 30px;
        }

        .btn-active {
            background-color: deepskyblue;
            font-weight: bold;
            font-size: 14px;
        }

        div {
            width: 200px;
            height: 200px;
            font-size: 64px;
            background-color: ghostwhite;
            display: none;
            color: black;
        }

        .div-active {
            display: block;
        }
    </style>
</head>
<body>
<h2>欢迎登陆培训机构系统</h2>
<button class="btn-active">学生</button>
<button>机构</button>
<button>管理员</button>
<div class="div-active">
    <form action="<%=request.getContextPath()%>/login" method="POST">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" name="password"/></td>
            </tr>
        </table>
        <input type="hidden" name="type" value="student">
        <input type="submit" value="登录">
    </form>
</div>
<div>
    <form action="<%=request.getContextPath()%>/login" method="POST">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" name="password"/></td>
            </tr>
        </table>
        <input type="hidden" name="type" value="institution">
        <input type="submit" value="登录">
    </form>
</div>
<div>
    <form action="<%=request.getContextPath()%>/login" method="POST">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" name="password"/></td>
            </tr>
        </table>
        <input type="hidden" name="type" value="manager">
        <input type="submit" value="登录">
    </form>
</div>
<script type="text/javascript">
    //1.先获取元素
    var buttonList = document.getElementsByTagName("button");
    var divList = document.getElementsByTagName("div");
    //2.添加事件
    for (var i = 0; i < buttonList.length; i++) {
        buttonList[i].index = i;
        buttonList[i].onclick = function () {
            for (var j = 0; j < buttonList.length; j++) {
                buttonList[j].className = "";
                divList[j].className = "";
            }
            this.className = "btn-active";
            divList[this.index].className = "div-active";
        }
    }
</script>
<a href="<%=request.getContextPath()%>/student/register">学生注册</a>
<br>
<a href="<%=request.getContextPath()%>/institution/register">机构注册</a>
</body>
</html>
