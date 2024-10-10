<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var='contextPath' value='${pageContext.request.contextPath}'></c:set>
<%@page import="jakarta.servlet.ServletContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Sales Management</title>
</head>


<style>
body, html {
	background-color: black !important;
	color: white !important;
}
</style>

<body>
	<%@include file="../../includes/top.jsp"%>

	<section class="heading-page">
		<h2>Sales Management</h2>
		<div id="salesChart"></div>
	</section>
	
	
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script>
		const ctx = document.getElementById('salesChart').getContext('2d');
		const salesData = [/* Sales data goes here */];
		const salesChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ 'Daily', 'Weekly', 'Monthly' ],
				datasets : [ {
					label : 'Sales',
					data : salesData,
					borderColor : 'rgba(75, 192, 192, 1)',
					fill : false
				} ]
			},
			options : {
				responsive : true,
				scales : {
					y : {
						beginAtZero : true
					}
				}
			}
		});
	</script>
</body>
</html>
