<%--
  User: zqq
  Date: 2019/10/22
  Time: 16:28
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<table width="400px" border="1">
    <thead>
    <tr>
        <td>用户id</td>
        <td>用户名</td>
        <td>密码</td>
        <td>编辑</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td id="userId">${user.userid}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><a href="<c:url value="/user/findUserById.action?userid=${user.userid}"/>">修改</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<input type="button" value="json" id="jsons">
<input type="button" value="json1" id="jsons1">
<br>
<a href="<c:url value="/excel/downUserExcel.action"/>" onclick="return null;">导出用户信息</a>
<br>
<form action="<c:url value="/excel/addUserByExcel.action"/> " method="post" enctype="multipart/form-data">
    <input type="file" id="userExcel" name="userExcel" placeholder="请导入xls或xlsx格式文件"><br>
    <input type="submit" value="上传" onclick="return checkData()">
</form>
<script src="<c:url value="/jquery/jquery-3.4.1.min.js"/> "></script>
<script>
    $(function () {
        $("#jsons").click(function () {
            $.ajax({
                type: 'POST',
                url: '<c:url value="/user/jsonTest.action"/> ',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify({'username':'111','password':'111'}),
                success: function (user) {
                    alert(user.username + "," + user.password);
                },error: function (error) {
                  alert("出错了"+error);
                }
            });
        });
        $("#jsons1").click(function () {
            $.ajax({
               type:'POST',
               url:'<c:url value="/user/jsonTest1.action"/> ',
                data:"username=1111&password=1111",
                success:function (data) {
                    alert(data.username + "," + data.password);
                }
            });
        });


    });
function checkData() {
    const fileDir = $("#userExcel").val();
    const suffix = fileDir.substr(fileDir.lastIndexOf("."));
        if("" === fileDir){
            alert("请选择要导入的Excel文件");
            return false;
        }
        if(".xls" !== suffix && ".xlsx" !== suffix){
            alert("文件格式不正确");
            return false;
        }
        return true;
}
</script>
</body>
</html>
