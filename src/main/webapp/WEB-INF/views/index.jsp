<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Форум job4j</title>
</head>
<body>
<div class="container text-right">
    <c:choose>
        <c:when test="${currentuser.username != null}">
            ${currentuser.username}
            <div class="btn-group">
                <form action="<c:url value='/logout'/>" method="GET">
                    <button class="btn btn-default btn-sm">Sign Out</button>
                </form>
                <form action="<c:url value='/edit?id=-1'/>" method="GET">
                    <button class="btn btn-default btn-sm">Create</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="btn-group">
                <form action="<c:url value='/login'/>" method="GET">
                    <button class="btn btn-default btn-sm">Sign In</button>
                </form>
                <form action="<c:url value='/reg'/>" method="GET">
                    <button class="btn btn-default btn-sm">Sign Up</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div class="container mt-3">
    <div class="container text-center">
        <h4>Форум job4j</h4>
    </div>
    <div class="row">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th style="width: 70%" scope="col">Тема</th>
                <th style="width: 30%" scope="col">Дата создания</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td><a href="<c:url value="/post?id=${post.id}"/>"><c:out value="${post.name}"/></a></td>
                    <td><c:out value="${post.created.getTime()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<th/>

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