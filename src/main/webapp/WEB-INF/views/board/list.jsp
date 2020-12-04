<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"/>
    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"/>
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <!--아이콘-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script>
        let result = "${msg}";

        if (result === "regSuccess") {
            alert("등록완료");
        } else if (result === "modSuccess") {
            alert("수정완료");
        } else if (result === "delSuccess") {
            alert("삭제완료")
        }
    </script>
</head>
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
    <c:forEach items="${boardList}" var="list" varStatus="status">
        <tr>
                <%--            <td>${list.bno}</td>--%>
            <td>${(pageMaker.totalCount - status.index) -((pageMaker.criteria.page - 1)* pageMaker.criteria.perPageNum)}</td>
            <td>
                <a href="${pageContext.request.contextPath}/board/read${pageMaker.makeSearch(pageMaker.criteria.page)}&bno=${list.bno}">${list.title}</a>
                <span class="badge bg-teal"><i class="fa fa-comment-o"></i>+${list.replyCnt}</span>
            </td>
            <td>${list.writer}</td>
            <td><fmt:formatDate value="${list.regDate}" pattern="yy-MM-dd HH:mm"></fmt:formatDate></td>
            <td>${list.viewCnt}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<nav>
    <ul class="pagination">

        <c:if test="${pageMaker.prev}">
            <%--            <li><a href="${pageContext.request.contextPath}/board/listPaging?page=${pageMaker.startPage -1}"--%>
            <li>
                <a href="${pageContext.request.contextPath}/board/list${pageMaker.makeSearch(pageMaker.startPage - 1)}"
                   aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        </c:if>


        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
            <li <c:out value="${pageMaker.criteria.page == idx ? 'class=active': ''}"/>>
                    <%--                <a href="${pageContext.request.contextPath}/board/listPaging?page=${idx}">${idx}</a>--%>
                <a href="${pageContext.request.contextPath}/board/list${pageMaker.makeSearch(idx)}">${idx}</a>
            </li>
        </c:forEach>

        <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
            <%--            <li><a href="${pageContext.request.contextPath}/board/listPaging?page=${pageMaker.endPage +1}"--%>
            <li><a href="${pageContext.request.contextPath}/board/list${pageMaker.makeSearch(pageMaker.endPage +1)}"
                   aria-label="Next"><span aria-hidden="true">&laquo;</span></a></li>
        </c:if>
    </ul>
</nav>

<ul>
    <li style="list-style: none;">
        <select name="searchType" id="searchType">
            <option value="n" <c:out value="${searchCriteria.searchType == null ? 'selected' : ''}"/> >선택</option>
            <option value="t" <c:out value="${searchCriteria.searchType == 't' ? 'selected' : ''}"/> >제목</option>
            <option value="c" <c:out value="${searchCriteria.searchType == 'c' ? 'selected' : ''}"/> >내용</option>
            <option value="w" <c:out value="${searchCriteria.searchType == 'w' ? 'selected' : ''}"/> >작성자</option>
            <option value="tc" <c:out value="${searchCriteria.searchType == 'tc' ? 'selected' : ''}"/> >제목+내용</option>
            <option value="cw" <c:out value="${searchCriteria.searchType == 'cw' ? 'selected' : ''}"/> >내용+작성자</option>
            <option value="tcw" <c:out value="${searchCriteria.searchType == 'tcw' ? 'selected' : ''}"/> >제목+내용+작성자</option>
        </select>

        <input type="text" name="keyWord" id="keyWordInput" value="${searchCriteria.keyWord}" placeholder="검색어">
        <span><button type="button" class="btn btn-primary btn-flat" id="searchBtn">검색</button></span>

        <a style="text-align: right;" href="${pageContext.request.contextPath}/board/insert">
            <button type="button" class="btn btn-primary">글쓰기</button>
        </a>
    </li>
</ul>

<script>
    $("#searchBtn").click(function(event){

        self.location = "${pageContext.request.contextPath}/board/list${pageMaker.makeQuery(1)}"
                        +"&searchType="+$("select option:selected").val()
                        +"&keyWord="+encodeURIComponent($("#keyWordInput").val());
    });
</script>
</body>
</html>