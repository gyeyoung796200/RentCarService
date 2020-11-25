<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
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
</head>
<body>
<form role="form">
    <input type="hidden" name="bno" value="${board.bno}"/>
    <label for="join"><h3 style="text-align: center;">글정보</h3></label>
    <input type="text" class="form-control" name="title" value="${board.title}" disabled/><br />
    <input type="text" class="form-control" name="writer" value="${board.writer}" disabled/><br>
    <textarea class="form-control" name="content" disabled>${board.content}</textarea>

    <input type="button" class="btn btn-primary modBtn" value="수정"/>
    <input type="button" class="btn btn-danger delBtn" value="삭제"/>
    <input type="button" class="btn btn-default listBtn" value="목록"/>
</form>
<script>
     const formObj = $("form[role='form']");

     $(".modBtn").click(function(){
         formObj.attr("action", "${pageContext.request.contextPath}/board/modify");
         formObj.attr("method", "get");
         formObj.submit();
     });

     $(".delBtn").click(function(){
         formObj.attr("action", "${pageContext.request.contextPath}/board/delete");
         formObj.attr("method", "post");
         formObj.submit();
     });

     $(".listBtn").click(function(){
         self.location = "${pageContext.request.contextPath}/board/list2";
     });
</script>
</body>
</html>