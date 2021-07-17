<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cpath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>KREAM</title>
	<link rel="stylesheet" href="${cpath }/resources/css/style.css?ver=2">
</head>
<body>
    <header>
        <div class="top">
            <nav>
                <a href="#">고객센터</a>
                <a href="#">관심상품</a>
                <a href="#">마이페이지</a>
                <a href="#">로그인</a>
                <!-- 로그인 세션 있을 경우 로그아웃으로 텍스트 변경 (로그인 기능 구현 후 작성必) -->
            </nav>
        </div>
        <div>
            <h1><a href="${cpath }">KREAM</a></h1>
            <nav>
                <a href="#">STYLE</a>
                <a href="${cpath }/search">SHOP</a>
                <a href="#">ABOUT</a>
                <div class="searchBtn"></div>
            </nav>
        </div>
    </header>
    <div class="guard"></div>
    <div class="searchWrap hidden">
        <div class="searchContainer">
            <div class="searchArea">
                <div></div>
                <form id="searchForm" method="POST" action="${cpath }/search">
	                <input id="inputSearch" type="text" name="keyword" placeholder="브랜드명, 모델명, 모델번호 등">
                </form>
                <div class="clearBtn hidden">×</div>
            </div>
            <button class="cancelBtn">취소</button>
        </div>
        <div class="recommendItem">
            <div onclick="location.href ='${cpath}/search?keyword=Jordan'"><div></div><p>Jordan 1</p></div>
            <div onclick="location.href ='${cpath}/search?keyword=지갑'"><div></div><p>지갑</p></div>
            <div onclick="location.href ='${cpath}/search?keyword=Dunk'"><div></div><p>Dunk</p></div>
            <div onclick="location.href ='${cpath}/search?keyword=Helinox'"><div></div><p>Helinox</p></div>
            <div onclick="location.href ='${cpath}/search?keyword=메종 키츠네'"><div></div><p>메종키츠네</p></div>
            <div onclick="location.href ='${cpath}/search?keyword=사카이'"><div></div><p>사카이</p></div>
        </div>
        
        <div class="searchResult hidden"></div>
        
    </div>
    <div class="overlay hidden"></div>
    
	<script src="${cpath }/resources/js/search.js?ver=1"/></script>
	
