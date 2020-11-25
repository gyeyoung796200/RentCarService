<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
    <table class="table table-dark table-hover" style="text-align: center;" id="listBoard">
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
                <td>${list.title}</td>
                <td>${list.writer}</td>
                <td><fmt:formatDate value="${list.regDate}"  pattern="yy-MM-dd HH:mm"></fmt:formatDate></td>
                <td>${list.viewCnt}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>