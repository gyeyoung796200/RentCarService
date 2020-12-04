<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<form role="form">
    <input type="hidden" name="bno" value="${board.bno}"/>
    <input type="hidden" name="page" value="${searchCriteria.page}"/>
    <input type="hidden" name="perPageNum" value="${searchCriteria.perPageNum}"/>
    <input type="hidden" name="searchType" value="${searchCriteria.searchType}"/>
    <input type="hidden" name="keyWord" value="${searchCriteria.keyWord}"/>

    <div class="col-lg-12">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">상세내용</h3>
            </div>

            <input type="text" class="form-control" name="title" value="${board.title}" disabled/><br/>
            <input type="text" class="form-control" name="writer" value="${board.writer}" disabled/><br>
            <textarea class="form-control" name="content" disabled>${board.content}</textarea>

            <br/>
            <fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
            <br/>
            <input type="button" class="btn btn-primary modBtn" value="수정"/>
            <input type="button" class="btn btn-danger delBtn" value="삭제"/>
            <input type="button" class="btn btn-default listBtn" value="목록"/>

        </div>
    </div>
</form>

<hr style="font-size: x-large;"/>

<div class="col-lg-12">
    <div class="box box-warning">
        <div class="box-header with-border">
            <a class="link-black text-lg"><i class="fa fa-pencil"></i> 댓글작성</a>
        </div>
        <div class="box-body">
            <form class="form-horizontal">
                <div class="form-group margin">
                    <div class="col-sm-10">
                        <textarea class="form-control" id="newReplyText" name="replyText" rows="3" placeholder="댓글내용..." style="resize: none"></textarea>
                    </div>
                    <div class="col-sm-2">
                        <input class="form-control" id="newReplyWriter" name="replyWriter" type="text" placeholder="댓글작성자...">
                    </div>
                    <hr/>
                    <div class="col-sm-2">
                        <button type="button" class="btn btn-primary btn-block createBtn"><i class="fa fa-save"></i> 저장</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="box box-success collapsed-box">
    <%--댓글 목록--%>
    <div class="box-body">
        <ul style="list-style: none;" id="replies"></ul>
    </div>
    <%--댓글 페이징--%>
    <div class="box-footer">
        <div class="text-center">
            <ul class="pagination pagination-sm no-margin">

            </ul>
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
    const formObj = $("form[role='form']");

    $(".modBtn").click(function () {
        formObj.attr("action", "${pageContext.request.contextPath}/board/modify");
        formObj.attr("method", "get");
        formObj.submit();
    });

    $(".delBtn").click(function () {
        formObj.attr("action", "${pageContext.request.contextPath}/board/delete");
        formObj.attr("method", "post");
        formObj.submit();
    });

    $(".listBtn").click(function () {
        formObj.attr("action", "${pageContext.request.contextPath}/board/list");
        formObj.attr("method", "get");
        formObj.submit();
    });
</script>

<script>
    let bno = ${board.bno};
    let page = ${searchCriteria.page};

    repliesListPaging(page);

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
                repliesListPaging(page);
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
                    repliesListPaging(page);
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
                    repliesListPaging(page);
                }
            }
        });
    });

    $(".pagination").on("click", "li a", function(event){

        event.preventDefault();

        page = $(this).attr("href");
        repliesListPaging(page);

    })

    function listPageNumber(pageMaker) {

        let str = "";

        //이전버튼
        if(pageMaker.prev){
            str += "<li><a href='"+pageMaker.startPage -1+"'>이전</a></li>";
        }

        //페이지번호
        for(let start = pageMaker.startPage, end = pageMaker.endPage; start < end; start++){
            let strClass = pageMaker.criteria.page == start ? 'class=active': '';
            str += "<li "+strClass+"><a href='"+start+"'>"+start+"</a></li>";
        }

        //다음버튼
        if(pageMaker.next){
            str += "<li><a href='"+pageMaker.endPage+1+"'>다음</a></li>";
        }

        $(".pagination-sm").html(str);

    }

    function repliesListPaging(page){

        $.getJSON("/replies/"+bno+"/"+page, function(data){

            console.log(data);
            console.log(data.replies);
            console.log(data.pageMaker);
            let str = "";

            $(data.replies).each(function(){

                str += "<li data-replyNo='"+this.replyNo+"' class =  'replyLi'>"
                    +   "<p class='replyText'>"+ this.replyText +"</p>"
                    +   "<p class='replyWriter'>"+ this.replyWriter +"</p>"
                    +   "<p class='regDate'>"+this.regDate+"</p>"
                    +   "<button type='button' class='btn btn-xs btn-success' data-toggle='modal' data-target='#modifyModal'>댓글수정</button>"
                    +   "</li>"
                    +   "<hr/>";
            });
            $("#replies").html(str);

            listPageNumber(data.pageMaker);

        });
    }



</script>
</body>
</html>