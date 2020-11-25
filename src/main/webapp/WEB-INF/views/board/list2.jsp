<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"/>
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <!--아이콘-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        let result = "${msg}";

        if(result === "regSuccess"){
            alert("등록완료");
        }
        else if(result === "modSuccess"){
            alert("수정완료");
        }
        else if(result === "delSuccess"){
            alert("삭제완료")
        }
    </script>
</head>
<body>
<table class="table table-dark table-hover" style="text-align: center;" id="listBoard">
    <a style="text-align: right;" href="${pageContext.request.contextPath}/board/insert"><button type="button" class="btn btn-primary">글쓰기</button></a>
    <thead style="text-align: center;">
    <th style="text-align: center;" width="10%">번호</th>
    <th style="text-align: center;" width="35%">제목</th>
    <th style="text-align: center;" width="15%">작성자</th>
    <th style="text-align: center;" width="30%">날짜</th>
    <th style="text-align: center;" width="10%">조회수</th>
    </thead>
    <tbody>
    <c:forEach items="${boardList}" var="list">
        <tr>
            <td>${list.bno}</td>
            <td><a href="${pageContext.request.contextPath}/board/read?bno=${list.bno}">${list.title}</a></td>
            <td>${list.writer}</td>
            <td><fmt:formatDate value="${list.regDate}" pattern="yy-MM-dd HH:mm"></fmt:formatDate></td>
            <td>${list.viewCnt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>