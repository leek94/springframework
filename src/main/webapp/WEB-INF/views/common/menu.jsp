<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="accordion" id="accordionExample">
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch01'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="${chNum=='ch01'}"
				aria-controls="collapseOne">Ch01 개발 환경 구축</button>
		</h2>
		<div id="collapseOne" class="accordion-collapse collapse ${chNum=='ch01'?'show':''}" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<ul>
					<li><a href="${pageContext.request.contextPath}/ch01/content?chNum=ch01">공통 레이아웃 구성</a></li>
					<!--  <li><a href="/springframework/ch01/content">공통 레이아웃 구성</a></li> -->
				</ul>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch02'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="${chNum=='ch02'}"
				aria-controls="collapseTwo">Ch02. 요청 매핑</button>
		</h2>
		<div id="collapseTwo" class="accordion-collapse collapse ${chNum=='ch02'?'show':''}" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<ul>
					<li><a href="${pageContext.request.contextPath}/ch02/getMethod?chNum=ch02">GET 방식</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/postMethod?chNum=ch02">POST 방식</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/modelAndViewReturn?chNum=ch02">Model&View 리턴</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/voidReturn?chNum=ch02">void 리턴</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/objectReturn?chNum=ch02">Object 리턴</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/testAuthInterceptor1?chNum=ch02">로그인X 내용</a></li>
					<li><a href="${pageContext.request.contextPath}/ch02/testAuthInterceptor2?chNum=ch02">로그인O 내용</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="accordion-item">
		<h2 class="accordion-header">
			<button class="accordion-button ${chNum=='ch03'?'':'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="${chNum=='ch03'}"
				aria-controls="collapseThree">Ch03</button>
		</h2>
		<div id="collapseThree" class="accordion-collapse collapse ${chNum=='ch03'?'show':''}" data-bs-parent="#accordionExample">
			<div class="accordion-body">
				<ul>
					<li>item01</li>
					<li>item02</li>
				</ul>
			</div>
		</div>
	</div>
</div>