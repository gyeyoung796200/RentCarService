<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="/resources/css/style.css"/>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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
<!--contetn start-->
    <!-- header start-->
    <nav class="navbar">
        <ul class="navbar__logo">
            <li>
                <a href="/"><i class="fas fa-car fa-3x"></i></a>
            </li>
            <li><h4 class="current__time"></h4></li>
        </ul>

        <ul class="navbar__menu">
            <li><a href="">About</a></li>
            <li><a href="">RentService</a></li>
            <li><a href="${pageContext.request.contextPath}/board/list2">Board</a></li>
        </ul>

        <ul class="navbar__user">
            <li><a href="">Login</a></li>
            <li><a href="${pageContext.request.contextPath}/user/insert">Join</a></li>
        </ul>

        <a href="#" class="navbar__toggleBtn">
            <i class="fas fa-bars"></i>
        </a>
    </nav>
    <!--header end-->

    <!--conetent-->
    <div class="content">
        <c:if test="${mainData == null}">
            <img src="/resources/img/main.jpg" alt=""/>
        </c:if>
<%--    <img src="/resources/img/main.jpg" alt=""/>--%>
        <c:if test="${mainData != null}">
            <c:import url="${mainData}"></c:import>
        </c:if>
    </div>
    <!--content end-->

<script src="/resources/js/time.js"></script>
<script src="/resources/js/menuToggle.js"></script>
</body>
</html>
