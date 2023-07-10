<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.publicwifi.v1.dto.PublicWifiDTO" %>
<%@ page import="org.publicwifi.v1.others.Distance" %>
<%@ page import="org.publicwifi.v1.dto.HistoryDTO" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="org.publicwifi.v1.dto.BookmarkDTO" %><%--
  Created by IntelliJ IDEA.
  User: leo
  Date: 2023/07/02
  Time: 12:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
  </head>
  <body>
    <h1>위치 히스토리 목록</h1>
    <nav>
      <a href="${pageContext.request.contextPath}/">홈</a> |
      <a href="${pageContext.request.contextPath}/history">위치 히스토리 목록</a> |
      <a href="${pageContext.request.contextPath}/load-wifi">Open API 와이파이 정보 가져오기</a> |
      <a href="${pageContext.request.contextPath}/bookmark-list">즐겨찾기 보기</a> |
      <a href="${pageContext.request.contextPath}/bookmark-group">즐겨찾기 그룹 관리</a> |
    </nav>
    <form action="${pageContext.request.contextPath}/bookmark-group-add">
      <button type="submit">북마크 그룹 이름 추가</button>
    </form>

    <main>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
          </tr>
        </thead>
        <tbody>
            <%
              ArrayList<BookmarkDTO> dtos = (ArrayList<BookmarkDTO>) request.getAttribute("bookmarkList");

              if (dtos.isEmpty()) {
                out.print("<td colspan=\"17\">정보가 존재하지 않습니다.</td>");
              } else {
                for (int i = 0; i < dtos.size(); i++) {
                  out.print("<tr>");
                  out.print("  <td>" + dtos.get(i).getBookmark_id() + "</td>");
                  out.print("  <td>" + dtos.get(i).getBookmark_name() + "</td>");
                  out.print("  <td>" + dtos.get(i).getBookmark_num() + "</td>");
                  out.print("  <td>" + dtos.get(i).getCreated_at() + "</td>");
                  if (dtos.get(i).getEdited_at() == null) {
                    out.print("  <td></td>");
                  } else {
                    out.print("  <td>" + dtos.get(i).getEdited_at() + "</td>");
                  }
                  out.print("  <td>");
                  out.print("    <form method=\"get\" action=\"/editing-bookmark\">");
                  out.print("      <input type=\"hidden\" id=\"bookmark_id\" name=\"bookmark_id\" value=\"" +
                          dtos.get(i).getBookmark_id() +
                          "\">");
                  out.print("      <button type=\"submit\">수정</button>");
                  out.print("    </form>");
                  out.print("    <form method=\"get\" action=\"/del-bookmark\">");
                  out.print("      <input type=\"hidden\" id=\"bookmark_id\" name=\"bookmark_id\" value=\"" +
                          dtos.get(i).getBookmark_id() +
                          "\">");
                  out.print("      <button type=\"submit\">삭제</button>");
                  out.print("    </form>");
                  out.print("</td>");
                  out.print("</tr>");
                }
              }
            %>
        </tbody>
      </table>
    </main>

    <script>
      function clickBtn() {
        window.navigator.geolocation.getCurrentPosition(function (position) { //OK
            let lat = position.coords.latitude;
            let lnt = position.coords.longitude;

            document.getElementById('lat').value = lat.toFixed(7);
            document.getElementById('lnt').value = lnt.toFixed(7);
            },

          function (error) { //error
            let str = "오류 검출";
            switch (error.code) {
              case error.PERMISSION_DENIED:
                str = "사용자 거부";
                break;
              case error.POSITION_UNAVAILABLE:
                str = "지리정보 없음";
                break;
              case error.TIMEOUT:
                str = "시간 초과";
                break;
              case error.UNKNOWN_ERROR:
                str = "알수없는 에러";
                break;
            }
            document.getElementById('lat').innerHTML = str;
          });
      }
    </script>
  </body>
</html>
