<%@ page import="java.util.ArrayList" %>
<%@ page import="org.publicwifi.v1.dto.BookmarkDTO" %><%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 2023/07/10
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<BookmarkDTO> dtos = (ArrayList<BookmarkDTO>) request.getAttribute("bookmarkList");
    int id = Integer.parseInt(request.getParameter("bookmark_id"));
    String name = "";
    int num = 0;

    for (int i = 0; i < dtos.size(); i++) {
        if (dtos.get(i).getBookmark_id() == id) {
            name = dtos.get(i).getBookmark_name();
            num = dtos.get(i).getBookmark_num();
        }
    }
%>
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
    <form action="${pageContext.request.contextPath}/edit-bookmark" method="get">
        <table>
            <tr>
                <th>북마크 이름</th>
                <%
                    out.print("<th><input type=\"text\" name=\"bookmark_name\"" +
                            "value=\"" +
                            name +
                            "\"/></th>");
                %>
            </tr>
            <tr>
                <th>순서</th>
                <%
                    out.print("<th><input type=\"text\" name=\"bookmark_num\"" +
                            "value=\"" +
                            num +
                            "\"/></th>");
                    out.print("<input type=\"hidden\" name=\"bookmark_id\" value=\"" +
                            id +
                            "\"");
                %>
            </tr>
            <tr colspan="2">
                <th>
                    <a href="${pageContext.request.contextPath}/">돌아가기</a>
                    <button type="submit">수정</button>
                </th>
            </tr>
        </table>
    </form>
</main>
</body>
</html>
