<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: black !important;
        }
        .header {
            display: flex;
            justify-content: flex-end; 
            padding: 10px; 
        }
        .logo {
            max-height: 50px; 
        }
        .contain { 
            background-color: white;
            padding: 20px; 
            border-radius: 5px; 
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 1400px; 
            margin: 0 auto; 
            width: 90%; 
        } 
        h1 {
            color: #333;
        }
        .category-container {
            margin-bottom: 20px;
            color: #222222;
        }
        select {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        .clickable-row:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
        .write-button {
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
        .clickable-row {
            cursor: pointer; 
            transition: background-color 0.3s; 
        }
        .clickable-row:hover {
            background-color: #e9e9e9; 
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
            margin: 0 4px;
        }
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }
        .pagination a:hover:not(.active) {background-color: #ddd;}
        .board-type-buttons {
            margin-top: 10px;
        }
        .board-type-buttons button {
            margin-right: 5px;
            padding: 5px 10px;
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            cursor: pointer;
        }
        .board-type-buttons button.active {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<%@include file="../../includes/top.jsp" %>

<section class="upcoming-meetings">
    <div class="contain">
        <h1>게시판</h1>
        <p>영화에 대한 내용을 올려주세요</p>
        
        <div class="category-container">
            <a href="boardWrite.do" class="write-button">게시물 작성</a>
            <div class="board-type-buttons">
                <button onclick="loadPage(1, '')">전체</button>
                <button onclick="loadPage(1, '1')">공지</button>
                <button onclick="loadPage(1, '2')">문의</button>
                <button onclick="loadPage(1, '3')">일반</button>
            </div>
        </div>

        <table id="boardTable">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>게시판 유형</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <!-- AJAX로 데이터 로드 -->
            </tbody>
        </table>

        <div class="pagination">
            <!-- AJAX로 페이지네이션 업데이트 -->
        </div>
    </div>
</section>

<%@include file="../../includes/bottom.jsp" %>

<form id="detailForm" action="boardDetail.do" method="get">
    <input type="hidden" id="selectedBoardNo" name="boardNo" value="">
</form>

<script>
    var currentBoardType = '';

    $(document).ready(function() {
        loadPage(1, '');

        $(document).on('click', '.clickable-row', function() {
            var boardNo = $(this).data('board-no');
            $('#selectedBoardNo').val(boardNo);
            $('#detailForm').submit();
        });
    });

    function loadPage(page, boardType) {
        if(boardType !== undefined) {
            currentBoardType = boardType;
        }
        $.ajax({
            url: '${pageContext.request.contextPath}/board.do',
            method: 'GET',
            data: { 
                action: 'getList', 
                page: page,
                boardType: currentBoardType
            },
            dataType: 'json',
            success: function(data) {
                updateBoardTable(data.boardList);
                updatePagination(data.currentPage, data.totalPages);
                updateBoardTypeButtons(data.boardType);
            },
            error: function(xhr, status, error) {
                console.error("AJAX error: " + status + ": " + error);
                alert("데이터를 불러오는 데 실패했습니다. 잠시 후 다시 시도해주세요.");
            }
        });
    }

    function updateBoardTable(boardList) {
        var tbody = $('#boardTable tbody');
        tbody.empty();
        if (boardList && boardList.length > 0) {
            $.each(boardList, function(i, board) {
                var row = '<tr class="clickable-row" data-board-no="' + board.board_no + '">' +
                    '<td>' + board.board_no + '</td>' +
                    '<td>' + board.bcode_name + '</td>' + 
                    '<td>' + board.board_title + '</td>' +
                    '<td>' + (board.writer ? board.writer : 'guest') + '</td>' +
                    '<td>' + board.board_write_date + '</td>' +
                    '<td>' + board.board_cnt + '</td>' +
                    '</tr>';
                tbody.append(row);
            });
        } else {
            tbody.append('<tr><td colspan="6">데이터가 없습니다.</td></tr>');
        }
    }

    function updatePagination(currentPage, totalPages) {
        var pagination = $('.pagination');
        pagination.empty();

        var startPage = Math.max(1, currentPage - 2);
        var endPage = Math.min(totalPages, startPage + 4);

        if (currentPage > 1) {
            pagination.append('<a href="#" onclick="loadPage(' + (currentPage - 1) + ')">&laquo; 이전</a>');
        }

        for (var i = startPage; i <= endPage; i++) {
            if (i == currentPage) {
                pagination.append('<a class="active" href="#" onclick="loadPage(' + i + ')">' + i + '</a>');
            } else {
                pagination.append('<a href="#" onclick="loadPage(' + i + ')">' + i + '</a>');
            }
        }

        if (currentPage < totalPages) {
            pagination.append('<a href="#" onclick="loadPage(' + (currentPage + 1) + ')">다음 &raquo;</a>');
        }
    }

    function updateBoardTypeButtons(boardType) {
        $('.board-type-buttons button').removeClass('active');
        $('.board-type-buttons button[onclick="loadPage(1, \'' + boardType + '\')"]').addClass('active');
    }
</script>

</body>
</html>