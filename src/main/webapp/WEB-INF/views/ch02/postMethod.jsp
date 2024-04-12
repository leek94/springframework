<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<!-- BS5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- jQuery 외부 라이브러리 설정 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<!-- 사용자 정의 JavaScript -->
<script type="text/javascript">
	
	function handleBtn() {
		var mid = $("#mid").val();
		var mpassword = $("#mpassword").val();
		$.ajax({
			url: "postMethodAjax",
			type: "post",
			//data: "mid=" + mid + "&mpassword=" + mpassword,
			//data: {mid:mid, mpassword:mpassword},
			data: { mid, mpassword },			//속성명과 변수명이 같을 경우
			success: function(data) {
				//data: {"result":"success"}
				//data: {"result":"fail", "reason":"wrongMid"}
				//data: {"result":"fail", "reason":"wrongMpassword"}
				console.log(data);
				
				if(data.result === "success") {
					$("#ajaxResponseInclude").html("로그인 성공");
				} else {
					if(data.reason === "wrongMid"){
						$("#ajaxResponseInclude").html("아이디가 존재하지 않습니다.");
					} else {
						$("#ajaxResponseInclude").html("비밀번호가 틀립니다.");
					}
				}
			}
		});
	}
</script>
</head>

<div class="d-flex flex-column vh-100">
	<%-- include 지시자는 소스 복사 개념 --%>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div class="flex-grow-1 m-2">
		<div class="d-flex row">
			<div class="col-md-4">
				<%@ include file="/WEB-INF/views/common/menu.jsp"%>
			</div>
			<div class="col-md-8">
				<div class="card">
					<div class="card-header">챕터2 POST 방식</div>
					<form class="m-2" method="post" action="postMethod">
						<input type="hidden" name="chNum" value="ch02" />

						<div class="form-group mb-02">
							<label for="mid">아이디</label> <input type="text" class="form-control" id="mid" name="mid" value="spring">
						</div>

						<div class="form-group mb-02">
							<label for="mpassword">패스워드</label> <input type="password" class="form-control" id="mpassword" name="mpassword" value="12345">
						</div>

						<!-- 제출 버튼: 양식의 데이터를 서버로 보내겠다. -->
						<input type="submit" value="로그인" class="btn btn-info btn-sm" />
					</form>
					<hr />
					<button onclick="handleBtn()" type="button" class="btn btn-success btn-sm" style="width: 100px">JS로 요청</button>
					<div id="ajaxResponseInclude" class="mt-2"></div>
				</div>
			</div>
		</div>
	</div>
</div>
</html>