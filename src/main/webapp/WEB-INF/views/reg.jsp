<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<div>
    <form name='reg' action="<c:url value='/reg'/>" method='POST'>
        <table>
            <tr>
                <td>UserName:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password'/></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td>
                    <select name='role'>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_USER">User</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>