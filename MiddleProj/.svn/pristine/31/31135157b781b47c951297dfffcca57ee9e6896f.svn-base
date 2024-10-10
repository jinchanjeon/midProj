<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>결제하기</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>

    <script>
        function mypayment() {
            const amount = new URLSearchParams(window.location.search).get('amount'); // amount 가져오기
            const productName = new URLSearchParams(window.location.search).get('productName'); // productName 가져오기

            const IMP = window.IMP; // 생략 가능
            IMP.init("imp70045821"); // 가맹점 식별 코드
            IMP.request_pay({
                pg: 'kakaopay.TC0ONETIME',
                pay_method: 'card',
                merchant_uid: 'order_no_' + new Date().getTime(), // 고유 거래 ID
                name: productName, // 제품 이름
                amount: amount, // 금액
                buyer_email: 'test@example.com',
                buyer_name: '홍길동',
            }, function (rsp) {
                if (rsp.success) {
                    // 서버에 결제 정보 전송
                    $.ajax({
                        url: '/payment/complete',
                        type: 'POST',
                        data: {
                            imp_uid: rsp.imp_uid,
                            merchant_uid: rsp.merchant_uid,
                        },
                        success: function (data) {
                            alert('결제가 완료되었습니다!');
                        },
                        error: function () {
                            alert('결제 정보 저장에 실패했습니다.');
                        }
                    });
                } else {
                    alert('결제에 실패하였습니다. 에러내용: ' + rsp.error_msg);
                }
            });
        }
    </script>
</head>
<body>
    결제할 금액: 
    <input type="text" id="amount" value="${param.amount}" readonly /> 
    <button onclick="mypayment()">결제하기</button>
</body>
</html>
