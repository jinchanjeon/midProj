<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: black !important;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            border-bottom: 2px solid #333;
            padding-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
            width: 20%;
        }
        .content {
            padding: 20px;
            border: 1px solid #ddd;
            min-height: 200px;
            margin-bottom: 20px;
        }
        .back-button {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }
        .comment-section {
            margin-top: 20px;
            border-top: 2px solid #333;
            padding-top: 20px;
        }
        .comment-input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px 5px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .button.delete {
            background-color: #f44336;
        }
        #commentList {
            margin-top: 20px;
        }
        .comment {
            border: 1px solid #ddd;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>게시글 상세</h1>
        <table>
            <tr>
                <th>제목</th>
                <td>${board.board_title}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.writer}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${board.board_write_date}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${board.board_cnt}</td>
            </tr>
        </table>
        
        <div class="content">
            ${board.board_content}
        </div>
        
        <c:if test="${not empty mem_code and (mem_code eq '2' or mem_code eq '3' or (not empty mem_no and mem_no eq board.mem_no))}">
            <a href="boardModify.do?boardNo=${board.board_no}" class="button">수정</a>
            <a href="#" onclick="confirmDelete(${board.board_no})" class="button delete">삭제</a>
        </c:if>
        <a href="board.do" class="button">목록으로</a>
        
        <%-- <div class="comment-section">
            <h2>댓글</h2>
            <form id="commentForm">
                <textarea id="commentContent" name="content" required></textarea>
                <input type="hidden" name="boardNo" value="${board.board_no}">
                <button type="submit">댓글 작성</button>
            </form>
            <div id="commentList">
            </div>
        </div>
    </div> --%>

    <script>
    function confirmDelete(boardNo) {
        if (confirm('정말로 이 게시글을 삭제하시겠습니까?')) {
            window.location.href = 'boardDelete.do?boardNo=' + boardNo;
        }
    }

    $(document).ready(function() {
        console.log('Document ready');

        $('#commentForm').submit(function(e) {
            e.preventDefault();
            console.log('Form submitted');
            
            var content = $('#commentContent').val();
            var boardNo = $('input[name="boardNo"]').val();
            
            console.log('Comment content:', content);
            console.log('Board number:', boardNo);

            $.ajax({
                url: '<%=request.getContextPath()%>/addComment.do',
                type: 'POST',
                data: {
                    content: content,
                    boardNo: boardNo
                },
                success: function(response) {
                    console.log('Ajax success:', response);
                    if(Array.isArray(response) && response.length > 0) {
                        $('#commentContent').val('');
                        updateCommentList(response);
                    } else {
                        console.log('Invalid response format');
                        alert('댓글 추가에 실패했습니다.');
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log('Ajax error:', textStatus, errorThrown);
                    alert('서버와의 통신 중 오류가 발생했습니다.');
                }
            });
        });

        function updateCommentList(comments) {
            console.log('Updating comment list:', comments);
            var commentListHtml = '';
            comments.forEach(function(comment) {
                commentListHtml += '<div class="comment">';
                commentListHtml += '<p><strong>작성자: ' + comment.mem_no + '</strong></p>';
                commentListHtml += '<p>' + comment.cmt_content + '</p>';
                commentListHtml += '<p><small>작성일: ' + comment.cmt_date + '</small></p>';
                commentListHtml += '</div>';
            });
            $('#commentList').html(commentListHtml);
        }
    });
    </script>
</body>
</html>