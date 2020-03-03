<%--
  User: zqq
  Date: 2019/10/22
  Time: 16:28
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<c:if test="${!empty allErrors}">
    <c:forEach items="${allErrors}" var="error">
        <span style="color: red"><c:out value="${error.defaultMessage}"/></span>
    </c:forEach>
</c:if>

<form action="<c:url value="/user/updateUser.action"/> " enctype="multipart/form-data" method="post">
    <label for="userid">用户ID </label>
    <input type="text" id="userid" name="userid" value="${user.userid}" disabled="disabled">
    <input type="hidden" name="userid" id="userid1" value="${user.userid}">
    <br>
    <label for="username">用户名</label>
    <input type="text" id="username" name="username" value="${user.username}">
    <br>
    <label for="password">密码</label>
    <input type="text" id="password" name="password" value="${user.password}">
    <br>
    <c:if test="${!empty imgName}">
        <img src="/upload/${imgName}" style="height: 100px;width: 100px;" alt="图片加载失败">
    </c:if><br>
    <input type="file"  name="userImg">
    <br>
    <input type="submit" value="提交"> <input type="reset" value="重置">
</form>
</body>
</html>
