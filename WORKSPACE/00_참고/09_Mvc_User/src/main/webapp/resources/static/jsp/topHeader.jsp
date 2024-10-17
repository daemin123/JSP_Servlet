<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="top-header layout-150">
				<div class="left"></div>
				<div class="right">
					<ul class=menu>
						<!-- 인증이후 -->
						<c:if test="${not empty sessionDto}">
							<li class=username><span>${sessionDto.username}</span></li>
							<li><a href="javascript:void(0)">나의정보</a></li>
							<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
						</c:if>
						<c:if test="${empty sessionDto}">
							<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
							<li><a href="${pageContext.request.contextPath}/user/add">회원가입</a></li>
						</c:if>
						<!-- 공통 -->
						<li><a href="javascript:void(0)">고객센터</a></li>
						<li><a href="javascript:void(0)">전체메뉴</a></li>
					</ul>
				</div>
</div>