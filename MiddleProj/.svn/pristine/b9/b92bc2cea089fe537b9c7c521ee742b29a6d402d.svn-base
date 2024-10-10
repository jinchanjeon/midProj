2<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
test
<table border="">
	<tr>
		<td>#{MovieName }</td>
		<td>sel.2</td>
		<td>sel.3</td>
	</tr>
	<tr>
		<td>
			<img src="../images/ala.jpg" width="100px" height="100px">
		</td>
		<td>
			<img src="../images/ala.jpg" width="100px" height="100px">
		</td>
		<td>
			<img src="../images/ala.jpg" width="100px" height="100px">
		</td>
	</tr>
	<tr>
		<td>
			<input type="checkbox" value="동의">동의합니다.
		</td>
		<td>
			<input type="checkbox" value="동2">동의합니다.
		</td>
		<td>
			<input type="checkbox" value="동3">동의합니다.
		</td>
	</tr>
	<tr aria-rowspan="3">
		<td>
			<input type="button" value="알림받기" onclick="f_chk()">
		</td>
	</tr>
	
</table>
<script>

function f_chk(){
	var v_inputs = document.querySelectorAll('input');
	var result = "";
	var count = 0;
	console.log();

	for(var i = 0; i < v_inputs.length; i++){
		if (v_inputs[i].checked){
			if(count > 0) result += ",";
			result += v_inputs[i].value;
			count++;
		}
	}
	alert("체크됨 " + result);
}

</script>
</body>
</html>