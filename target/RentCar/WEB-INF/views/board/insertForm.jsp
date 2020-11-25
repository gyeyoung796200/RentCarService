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
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form role="form" action="${pageContext.request.contextPath}/board/insert" method="post">
    <label for="join"><h3 style="text-align: center;">글작성</h3></label>
    <input type="text" class="form-control" name="title" placeholder="제목"/><br />
    <input type="text" class="form-control" name="writer" placeholder="작성자"/><br>
    <textarea class="form-control" name="content" placeholder="내용"></textarea>

    <input type="submit" class="btn btn-primary" value="확인"/>
    <input type="reset" class="btn btn-danger" value="취소"/>
    <input type="button" class="btn btn-default" onclick="history.go(-1)" value="목록"/>
</form>
</body>
</html>