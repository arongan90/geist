<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="/resources/css/login.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Geist</title>
</head>
<body class="wrap">
	<div class="header">
		<div class="logo">GEIST</div>
	</div>
	<div class="conteiner">
		<form id="loginForm">
			<div class="login-area">
				<div class="id-area">
					<span class="ps-box">
						<input type="text" id="id" name="emp_id" placeholder="아이디" maxlength="20" />
					</span>
					<span class="msg" id="id-error">아이디를 입력하세요.</span>
				</div>
				<br />
				<div class="pwd-area">
					<span class="ps-box">
						<input type="password" id="pwd" name="emp_pw" placeholder="비밀번호" maxlength="20" />
					</span>
					<span class="msg" id="pwd-error">비밀번호를 입력하세요.</span>
				</div>
			</div>
			<br /> <input type="submit" class="btn-login" value="로 그 인" /> <br />
			<br />
		</form>
		<br />
		<div class="find-info">
			<a href="#" target="_blank" class="idSearchLink">아이디 찾기</a> 
			<span> | </span> 
			<a href="#" target="_blank" class="pwSearchLink">비밀번호 찾기</a> 
			<span> | </span> <a href="#" target="_blank" class="registerLink">회원가입</a>
		</div>
		<br />
		<div class="footer">
			<div class="container-fluid">
				<div class="d-flex justify-content-end small">
					<div>
						회사소개&emsp;|&emsp;제휴제안&emsp;|&emsp;이용약관&emsp;|&emsp;개인정보처리방침&emsp;
						<br />Geist ™ 2020.mental.warr
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
	var loginService = (function() {
		function login(param, callback, error) {
			$.ajax({
				type : 'post',
				url : '/login/login',
				data : JSON.stringify(param),
				contentType : "application/json; charset=utf-8",
				success : function(result, status, xhr) {
					if(callback) {
						callback(result);
					}
				},
				error : function(xhr, status, err) {
					if(error) {
						error(err);
					}
				}
			});
		}
		
		return {
			login : login
		}
	});
	
	
	$(document).ready(function() {
		var loginForm = $("#loginForm");
		var loginBtn = $(".btn-login");
		var registerBtn = $(".registerLink");
		var idSearchBtn = $(".idSearchLink");
		var pwSearchBtn = $(".pwSearchBtn");
		
		$("#id-error").hide();
		$("#pwd-error").hide();
		
		loginBtn.on("click", function() {
			if(loginForm.find("input[name='emp_id']").val() === null) {
				$("#id_error").show();
				$("#id").focus();
				return false;
			} else {
				$("#id_error").hide();
			}
			
			if(loginForm.find("input[name='emp_pw']").val() === null) {
				$("#pwd_error").show();
				$("#pwd").focus();
				return false;
			} else {
				$("#pwd_error").hide();
			}
			
			var emp_id = $("input[name='emp_id']").val();
			var emp_pw = $("input[name='emp_pw']").val();
			
			loginService.login({
				emp_id : emp_id,
				emp_pw : emp_pw
			}, function(result) {
				if(result == 'success') {
					location.href = "/index";
				} else {
					alert("로그인 정보가 일치하지 않습니다.");
				}
			});
		});
		
		registerBtn.on("click", function() {
			location.href = "/register";
		});
		
		idSearchBtn.on("click", function() {
			location.href = "/idfind";
		});
		
		pwSearchBtn.on("click", function() {
			location.href = "/pwdfind";
		});
	});


</script>

</body>
</html>