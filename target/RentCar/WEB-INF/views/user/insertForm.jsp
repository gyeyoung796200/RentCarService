<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form class="joinForm">
    <label for="join"><h3 style="text-align: center;">회원가입</h3></label>
    <input type="text" class="form-control" name="userId" placeholder="아이디"/><br />
    <input type="password" class="form-control" name="userPw" placeholder="비밀번호"/><br />
    <input type="text" class="form-control" name="userName" placeholder="이름"/><br />
    <input type="email" class="form-control" name="userEmail" placeholder="이메일"/><br />
    <input type="submit" class="form-control btn btn-primary" value="가입"/>
    <input type="reset" class="form-control btn btn-danger" value="취소"/>
</form>
</body>
</html>