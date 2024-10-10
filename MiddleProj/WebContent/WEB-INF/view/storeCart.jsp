<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
    <style>
        :root {
            --primary-color: #222222;
            --secondary-color: #333333;
            --background-color: #111111;
            --text-color: #FFFFFF;
            --border-color: #444444;
        }

        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            background-color: var(--background-color);
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        .cart-table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 15px;
        }

        .cart-table th, .cart-table td {
            background-color: var(--secondary-color);
            padding: 15px;
            color: var(--text-color);
        }

        .cart-table th {
            background-color: var(--primary-color);
        }

        .cart-table tr {
            box-shadow: 0 2px 5px rgba(255, 255, 255, 0.1);
        }

        .cart-table td {
            text-align: center;
        }

        .quantity-control {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .quantity-btn, .remove-btn {
            background-color: var(--primary-color);
            color: var(--text-color);
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .quantity-btn:hover, .remove-btn:hover {
            background-color: #444444;
        }

        .quantity-input {
            width: 40px;
            text-align: center;
            margin: 0 10px;
            border: 1px solid var(--border-color);
            background-color: var(--secondary-color);
            color: var(--text-color);
            padding: 5px;
        }

        .remove-btn {
            background-color: #e74c3c;
        }

        .remove-btn:hover {
            background-color: #c0392b;
        }

        .total {
            text-align: right;
            font-size: 1.2em;
            margin-top: 20px;
            background-color: var(--secondary-color);
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(255, 255, 255, 0.1);
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
            display: inline-block;
            color: var(--text-color);
        }

        .btn-primary {
            background-color: var(--primary-color);
        }

        .btn-secondary {
            background-color: var(--secondary-color);
        }

        .btn:hover {
            opacity: 0.8;
        }

        @media (max-width: 768px) {
            .cart-table, .cart-table tbody, .cart-table tr, .cart-table td {
                display: block;
            }
            .cart-table thead {
                display: none;
            }
            .cart-table tr {
                margin-bottom: 20px;
            }
            .cart-table td {
                text-align: right;
                padding-left: 50%;
                position: relative;
            }
            .cart-table td::before {
                content: attr(data-label);
                position: absolute;
                left: 6px;
                width: 45%;
                padding-right: 10px;
                white-space: nowrap;
                font-weight: bold;
                text-align: left;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>
            <c:choose>
                <c:when test="${not empty mem_name}">
                    <input type="hidden" name="memId" value="${member.memId}">
                    ${mem_name}님의 장바구니
                </c:when>
                <c:otherwise>
                    장바구니
                </c:otherwise>
            </c:choose>
        </h1>

        <c:choose>
            <c:when test="${empty cartItems}">
                <p>장바구니가 비어있습니다.</p>
            </c:when>
            <c:otherwise>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>상품명</th>
                            <th>가격</th>
                            <th>수량</th>
                            <th>소계</th>
                            <th>작업</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr data-product-id="${item.product_id}" data-quantity="${item.quantity}" data-product-name="${item.product_name}" data-price="${item.price}">
                                <td data-label="상품명">${item.product_name}</td>
                                <td data-label="가격"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="₩" /></td>
                                <td data-label="수량">
                                    <div class="quantity-control">
                                        <button class="quantity-btn" onclick="updateQuantity('${item.product_id}', -1)">
                                            <i class="fas fa-minus"></i>
                                        </button>
                                        <input type="text" class="quantity-input" id="quantity_${item.product_id}" value="${item.quantity}" readonly>
                                        <button class="quantity-btn" onclick="updateQuantity('${item.product_id}', 1)">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                    </div>
                                </td>
                                <td data-label="소계"><fmt:formatNumber value="${item.price * item.quantity}" type="currency" currencySymbol="₩" /></td>
                                <td data-label="작업">
                                    <button class="remove-btn" onclick="removeItem('${item.product_id}')">
                                        <i class="fas fa-trash"></i> 삭제
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="total">
                    총 금액:
                    <fmt:formatNumber value="${cartItems.stream().map(item -> item.price * item.quantity).sum()}" type="currency" currencySymbol="₩" />
                </div>
            </c:otherwise>
        </c:choose>

        <div class="action-buttons">
            <a class="btn btn-primary payment"><i class="fas fa-shopping-cart"></i> 구매하기</a>
            <a href="${pageContext.request.contextPath}/store.do" class="btn btn-secondary"><i class="fas fa-arrow-left"></i> 상품 목록으로 돌아가기</a>
        </div>
    </div>

    <script>
        function updateQuantity(productId, change) {
            var quantityInput = document.getElementById('quantity_' + productId);
            var newQuantity = parseInt(quantityInput.value) + change;
            if (newQuantity > 0) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/storeCart.do',
                    method: 'POST',
                    data: {
                        action: 'update',
                        productId: productId, 
                        quantity: newQuantity
                    },
                    success: function(response) {
                        if (response.status === "success") {
                            location.reload();
                        } else {
                            alert(response.message);
                        }
                    },
                    error: function(xhr, status, error) {
                        alert("오류가 발생했습니다: " + error);
                    }
                });
            }
        }

        function removeItem(productId) {
            $.ajax({
                url: '${pageContext.request.contextPath}/storeCart.do',
                method: 'POST',
                data: {
                    action: 'remove',
                    productId: productId
                },
                success: function(response) {
                    location.reload();
                }
            });
        }
    </script>

   <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
    <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
    
    
    <script>
        $('.payment').on('click', function() {
            const items = []; // 상품 정보를 담을 배열
            let totalAmount = 0; // 총 금액 초기화
            const memberId = "${member.memId}"; // 회원 ID 가져오기

            // 각 상품의 정보 수집
            $('tr[data-product-id]').each(function() {
                const productId = $(this).data('product-id');
                const quantity = parseInt($(this).find('.quantity-input').val());
                const price = $(this).data('price');
                const amount = price * quantity;
                const productName= $(this).data('product-name');
                
                if (quantity > 0) {
                    items.push({ productId: productId, quantity: quantity, amount: amount, productName:productName });
                    totalAmount += amount; // 총 금액 업데이트
                }
            });

            if (items.length === 0) {
                alert("장바구니에 상품이 없습니다.");
                return;
            }

            const merchant_uid = "order_" + memberId + "_" + new Date().getTime();

            const IMP = window.IMP; // 생략 가능
            IMP.init("imp70045821"); // 가맹점 식별 코드
            console.log("결제 요청 함수 호출됨");

            IMP.request_pay({
                pg: 'kakaopay.TC0ONETIME',
                pay_method: 'card',
                merchant_uid: merchant_uid,
                name: items.map(item => item.productName).join(", "), // 상품명
                amount: totalAmount, // 총 금액
                buyer_name: "${member.memName}", // 구매자 이름
            }, function (rsp) {
                if (rsp.success) {
                    console.log("결제 성공:", rsp);
                    // AJAX 요청
                    $.ajax({
                        url: "${pageContext.request.contextPath}/buy.do", // 결제 준비 요청 URL
                        type: "POST",
                        data: {
                            items: JSON.stringify(items),
                            totalAmount: totalAmount,
                            memberId: memberId,
                            merchant_uid: merchant_uid
                        },
                        success: function(resp) {
                           console.log("결제 2번째 완료");
                            const paymentData = {
                                partner_order_id: merchant_uid,
                                partner_user_id: memberId,
                                item_name: "장바구니 상품", // 결제할 상품 이름
                                quantity: items.length,
                                total_amount: totalAmount,
                                tax_free_amount: 0,
                                approval_url: "${pageContext.request.contextPath}/payment/complete", // 결제 성공 후 URL
                            };

                            // KakaoPay API 호출
                            //Kakao.Pay.request(paymentData);
                            alert("결제완료됐습니다");
                        },
                        error: function() {
                            alert("결제 준비 실패했습니다.");
                        }
                    });
                } else {
                    alert("결제 실패: " + rsp.error_msg);
                }
            });
        });
    </script>
</body>
</html>
