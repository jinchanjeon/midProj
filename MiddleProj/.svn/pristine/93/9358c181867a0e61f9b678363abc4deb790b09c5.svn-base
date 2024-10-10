<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var='contextPath' value='${pageContext.request.contextPath}'/>

<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="java.util.List"%>
<c:set var="member" value="${sessionScope.member}"></c:set>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title>${product.product_name} - 상세정보</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: black !important;
        }
        .product-detail {
            background-color: white;
            border-radius: 8px;
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
            box-shadow: 0 2px 15px rgba(0,0,0,0.1);
        }
        .product-image {
            width: 100%;
            max-height: 400px;
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .product-name {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 10px;
            color: #333;
        }
        .product-price {
            font-size: 24px;
            color: #e44d26;
            margin-bottom: 15px;
        }
        .product-description {
            margin-bottom: 20px;
            line-height: 1.6;
            color: #555;
        }
        .product-info {
            margin-bottom: 10px;
            color: #777;
        }
        .action-buttons {
            margin-top: 20px;
        }
        .action-button {
            padding: 12px 25px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.2s;
        }
        .buy-button {
            background-color: #e44d26;
            color: white;
        }
        .buy-button:hover {
            background-color: #c74320;
            transform: scale(1.05);
        }
        .cart-button {
            background-color: #333;
            color: white;
        }
        .cart-button:hover {
            background-color: #444;
            transform: scale(1.05);
        }
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #666;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s, transform 0.2s;
        }
        .back-button:hover {
            background-color: #555;
            transform: scale(1.05);
        }
    </style>
    <script>
    function addToCart() {
        console.log("장바구니 추가 함수 호출됨");
        
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "${pageContext.request.contextPath}/storeCart.do", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                console.log("서버 응답 상태: " + xhr.status);
                console.log("서버 응답 내용: " + xhr.responseText);
                
                if (xhr.status === 200) {
                    console.log("장바구니에 상품 추가 성공");
                    Swal.fire({
                        title: '성공!',
                        text: '상품이 장바구니에 추가되었습니다.\n장바구니로 이동할까요?',
                        icon: 'success',
                        showCancelButton: true,
                        confirmButtonText: '예',
                        cancelButtonText: '아니요'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "${pageContext.request.contextPath}/storeCart.do";
                        }
                    });
                } else {
                    console.log("장바구니에 상품 추가 실패");
                    Swal.fire({
                        title: '실패!',
                        text: '장바구니에 상품을 추가하는 데 실패했습니다.\n\n로그인 후 다시 시도하세요.',
                        icon: 'error',
                        confirmButtonText: '확인'
                    });
                }
            }
        };
        
        var data = "action=add&productId=${product.product_id}&quantity=1";
        xhr.send(data);
        console.log("AJAX 요청 전송 완료");
    }
    </script>
</head>
<body>
    <div class="product-detail">
        <img src="${pageContext.request.contextPath}/resources/store/assets/images/${product.product_id}.jpg" 
             alt="${product.product_name}" 
             onerror="this.src='${pageContext.request.contextPath}/resources/store/assets/images/default.png';" 
             class="product-image">
        <div class="product-name">${product.product_name}</div>
        <div class="product-price">￦${product.price}</div>
        <p class="product-description">${product.product_name}을(를) 즐겨보세요!</p>
        <div class="product-info">카테고리: ${product.category_name}</div>
        <div class="product-info">재고: ${product.stock}</div>
        <div class="action-buttons">
            <button onclick="mypayment()" class="action-button buy-button">구매하기</button>
            <button class="action-button cart-button" onclick="addToCart()">장바구니</button>
        </div>
        <a href="store.do" class="back-button">상품 목록으로 돌아가기</a>
    </div>
</body>
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    
    


<script>

function mypayment() {
    const amount = ${product.price}; // 상품 가격
    const productName = "${product.product_name}"; // 상품 이름
    const memberNo = "${member.memNo}"; // 회원 No
    const merchant_uid = "order_" + memberNo + "_" + new Date().getTime(); // 고유 거래 ID

    const IMP = window.IMP; // 생략 가능
    IMP.init("imp70045821"); // 가맹점 식별 코드
    console.log("결제 요청 함수 호출됨");

    IMP.request_pay({
        pg: 'kakaopay.TC0ONETIME',
        pay_method: 'card',
        merchant_uid: merchant_uid,
        name: productName, // 제품 이름
        amount: amount, // 금액
        buyer_name: "${member.memName}", // 구매자 이름
    }, function (rsp) {
        if (rsp.success) {
            console.log("결제 성공:", rsp);
            // 서버에 결제 정보 전송
            $.ajax({
                url: '${contextPath}/payment/complete'+ rsp.imp_uid,
                type: 'POST',
                data: {
                    imp_uid: rsp.imp_uid,
                    merchant_uid: rsp.merchant_uid,
                    amount:amount,
                    
                },
                success: function (data) {
                    alert('결제가 완료되었습니다!');
                },
                error: function () {
                    alert('결제 정보 저장에 실패했습니다.');
                }
            }).done(function(data) {
        		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
        		if ( everythings_fine ) {
        			var msg = '결제가 완료되었습니다.';
        			msg += '\n고유ID : ' + rsp.imp_uid;
        			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
        			msg += '\결제 금액 : ' + rsp.amount;
        			
        			alert(msg);
        		} else {
        			//[3] 아직 제대로 결제가 되지 않았습니다.
        			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
        		}
        	}) ;
        } else {
            console.log("결제 실패:", rsp.error_msg);
            alert('결제에 실패하였습니다. 에러내용: ' + rsp.error_msg);
        }
    });
}

</script>


    
  <!--   
<script>
function mypayment() {
    const amount = ${product.price}; // 상품 가격
    const productName = "${product.product_name}"; // 상품 이름
    const buyer_name = "${member.memName}"; // 구매자 이름
    const memberNo = "${member.memNo}"; // 회원 No
    const merchant_uid = "order_" + memberNo + "_" + new Date().getTime(); // 고유 거래 ID

    // 1. 주문 정보 저장
    $.ajax({
        url: '${contextPath}/buy.do',
        type: 'POST',
        data: {
            productId: ${product.product_id},
            quantity: 1,
            memberNo: memberNo,
            amount: amount,
            merchant_uid: merchant_uid // merchant_uid 추가
        },
        success: function() {
            // 2. 결제 요청
            const IMP = window.IMP;
            IMP.init("imp70045821"); // 가맹점 식별 코드
            IMP.request_pay({
                pg: 'kakaopay.TC0ONETIME',
                pay_method: 'card',
                merchant_uid: merchant_uid,
                name: productName, // 제품 이름
                amount: amount, // 금액
                buyer_name: buyer_name,
            }, function(rsp) {
                if (rsp.success) {
                    // 3. 결제 정보 저장
                    $.ajax({
                        url: '${contextPath}/payment/complete',
                        type: 'POST',
                        data: {
                            imp_uid: rsp.imp_uid,
                            merchant_uid: rsp.merchant_uid,
                            amount: amount
                        },
                        success: function(data) {
                            alert('결제가 완료되었습니다!');
                        },
                        error: function() {
                            alert('결제 정보 저장에 실패했습니다.');
                        }
                    });
                } else {
                    alert('결제에 실패하였습니다. 에러내용: ' + rsp.error_msg);
                }
            });
        },
        error: function() {
            alert('주문 정보를 저장하는 데 실패했습니다.');
        }
    });

}


</script>
   -->
 </html>