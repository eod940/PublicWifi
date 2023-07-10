<%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 2023/07/10
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<nav>
    <a href="${pageContext.request.contextPath}/">홈</a> |
    <a href="${pageContext.request.contextPath}/history">위치 히스토리 목록</a> |
    <a href="${pageContext.request.contextPath}/load-wifi">Open API 와이파이 정보 가져오기</a> |
    <a href="${pageContext.request.contextPath}/bookmark-list">즐겨찾기 보기</a> |
    <a href="${pageContext.request.contextPath}/bookmark-group">즐겨찾기 그룹 관리</a> |
</nav>

<main>
    <form action="/make-bookmark" method="get">
        <table>
            <tr>
                <th>북마크 이름</th>
                <th><input type="text" name="bookmark-name"/></th>
            </tr>
            <tr>
                <th>순서</th>
                <th><input type="text" name="bookmark-num"/></th>
            </tr>
            <tr colspan="2">
                <th><button type="submit">추가</button></th>
            </tr>
        </table>
    </form>
</main>
</body>
</html>
