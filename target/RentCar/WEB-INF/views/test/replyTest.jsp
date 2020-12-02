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
</head>
<body>
<section class="content container-fluid">
    <div class="col-lg-12">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">댓글 작성</h3>
            </div>
            <div class="box-body">
                <div class="form-group">
                    <label for="newReplyText">댓글내용</label>
                    <input type="text" class="form-control" id="newReplyText" name="replyText" placeholder="댓글내용">
                </div>
                <div class="form-group">
                    <label for="newReplyText">댓글 작성자</label>
                    <input type="text" class="form-control" id="newReplyWriter" name="replyWriter" placeholder="댓글작성자">
                </div>
                <button type="button" class="btn btn-primary createBtn">등록</button>
            </div>

            <div class="box-footer">
                <ul id="replies"></ul>
            </div>

            <div class="box-footer">
                <div class="text-center">
                    <ul class="pagination pagination-sm no-margin"></ul>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="modifyModal" role="dialog">
        <div class="modal-dialog" style="background-color: white;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">댓글수정창</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="replyNo">댓글번호</label>
                    <input type="text" class="form-control" id="replyNo" name="replyNo" readonly>
                </div>
                <div class="form-group">
                    <label for="replyText">댓글내용</label>
                    <input type="text" class="form-control" id="replyText" name="replyText" placeholder="댓글 내용입력">
                </div>
                <div class="form-group">
                    <label for="replyWriter">댓글작성자</label>
                    <input type="text" class="form-control" id="replyWriter" name="replyWriter" readonly>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
                    <button type="button" class="btn btn-success modalModBtn">수정</button>
                    <button type="button" class="btn btn-danger modalDelBtn">삭제</button>
                </div>
            </div>


        </div>
    </div>

    <script>
        const bno = 10;

        repliesList();

        $(".createBtn").click(function () {

            const replyText = $("#newReplyText");
            const replyWriter = $("#newReplyWriter");

            const replyTextVal = replyText.val();
            const replyWriterVal = replyWriter.val();

            $.ajax({
                type: "post",
                url: "/replies",
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "POST"
                },
                dataType: "text",
                data: JSON.stringify({
                    bno: bno,
                    replyText: replyTextVal,
                    replyWriter: replyWriterVal
                }),
                success: function (result) {
                    if (result == "regSuccess") {
                        alert("등록성공");
                    }
                    repliesList();
                    replyText.val("");
                    replyWriter.val("");
                }
            });
        });

        function repliesList() {

            $.getJSON("${pageContext.request.contextPath}/replies/all/" + bno, function (data) {

                console.log(data);

                let str = "";

                $(data).each(function () {

                    str += "<li data-replyNo='" + this.replyNo + "' class='replyLi'>"
                        + "<p class='replyText'>" + this.replyText + "</p>"
                        + "<p class='replyWriter'>" + this.replyWriter + "</p>"
                        + "<button type='button' class='btn btn-xs btn-success' data-toggle='modal' data-target='#modifyModal'>댓글수정</button>"
                        + "</li>"
                        + "<hr/>";
                });

                $("#replies").html(str);
            });
        }

        $("#replies").on("click", ".replyLi button", function () {
            const reply = $(this).parent();

            const replyNo = reply.attr("data-replyNo");
            const replyWriter = reply.find(".replyWriter").text();
            const replyText = reply.find(".replyText").text();

            $("#replyNo").val(replyNo);
            $("#replyWriter").val(replyWriter);
            $("#replyText").val(replyText);
        });

        $(".modalDelBtn").on("click", function () {

            const replyNo = $(this).parent().parent().find("#replyNo").val();

            $.ajax({
                type: "delete",
                url: "/replies/" + replyNo,
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "DELETE"
                },
                dataType: "text",
                success: function (result) {
                    if (result == "delSuccess") {
                        alert("삭제성공");
                        $("#modifyModal").modal("hide");
                        repliesList();
                    }
                }
            });
        });

        $(".modalModBtn").on("click", function () {

            const replyNo = $(this).parent().parent().find("#replyNo").val();
            const replyText = $(this).parent().parent().find("#replyText").val();

            $.ajax({
                type: "put",
                url: "/replies/" + replyNo,
                headers: {
                    "Content-type": "application/json",
                    "X-HTTP-Method-Override": "PUT"
                },
                dataType: "text",
                data: JSON.stringify({
                    replyText: replyText
                }),
                success: function (result) {
                    if (result == "modSuccess") {
                        alert("수정되었습니다");
                        $("#modifyModal").modal("hide");
                        repliesList();
                    }
                }
            });
        });


    </script>
</section>
</body>
</html>