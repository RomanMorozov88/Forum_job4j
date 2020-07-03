<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <title>Пост</title>
</head>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Пост</title>
</head>
<body>
<div>

    <c:if test="${not empty errorMessage}">
        <div style="color:red; font-weight: bold; margin: 30px 0px;">
                ${errorMessage}
        </div>
    </c:if>

</div>
<div class="container mt-3">
    <div>
        <h4 scope="col">Тема</h4><br/>
        <p><c:out value="${currentPost.name}"/></p><br/>
        <th/>
        <h4 scope="col">Текст</h4><br/>
        <p><c:out value="${currentPost.description}"/><p/><br/>
        <th/>
        <h4 scope="col">Автор</h4><br/>
        <p><c:out value="${currentPost.author.name}"/><p/><br/>
        <th/>
        <h4 scope="col">Дата создания</h4><br/>
        <p><c:out value="${currentPost.created.getTime()}"/><p/><br/>
        <th/>

    </div>
</div>
<th/>
<div class="container mt-3">
    <c:if test="${editFlag}">
        <div>
            <form action="<c:url value="/edit"/>" method="GET">
                <input type="hidden" name="postid" value="${currentPost.id}"/>
                <input class="btn btn-secondary" type="submit" value="Edit"/>
            </form>
        </div>
    </c:if>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>