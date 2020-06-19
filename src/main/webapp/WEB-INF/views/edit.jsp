<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование</title>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px;">
            ${errorMessage}
    </div>
</c:if>
<div>
    <form name='edit' action="<c:url value='/edit'/>" method='POST'>
        <table>
            <tr>
                <td>Post Name:</td>
                <td><input type='text' name='name' value="${post.name}"/></td>
            </tr>
            <tr>
                <td>Post Description:</td>
                <td><input type='text' name='description' value="${post.description}"/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" class="btn btn-secondary btn-sm" value="submit"/></td>
            </tr>
        </table>
        <input type="hidden" name='id' value="${post.id}"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>
</body>
</html>