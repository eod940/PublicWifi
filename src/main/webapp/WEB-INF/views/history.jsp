<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.publicwifi.v1.dto.PublicWifiDTO" %>
<%@ page import="org.publicwifi.v1.others.Distance" %>
<%@ page import="org.publicwifi.v1.dto.HistoryDTO" %>
<%@ page import="java.lang.reflect.Array" %><%--
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
    <h1>와이파이 정보 구하기</h1>
    <nav>
      <a href="${pageContext.request.contextPath}/">홈</a> |
      <a href="${pageContext.request.contextPath}/history">위치 히스토리 목록</a> |
      <a href="${pageContext.request.contextPath}/load-wifi">Open API 와이파이 정보 가져오기</a> |
      <a href="#">즐겨찾기 보기</a> |
      <a href="#">즐겨찾기 그룹 관리</a> |
    </nav>

    <main>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
          </tr>
        </thead>
        <tbody>
            <%
              ArrayList<HistoryDTO> dtos = (ArrayList<HistoryDTO>) request.getAttribute("historyList");

              if (dtos.isEmpty()) {
                out.print("<td colspan=\"17\">없음</td>");
              } else {
                for (int i = 0; i < dtos.size(); i++) {
                  out.print("<tr>");
                  out.print("  <td>" + dtos.get(i).getHistory_id() + "</td>");
                  out.print("  <td>" + dtos.get(i).getLAT() + "</td>");
                  out.print("  <td>" + dtos.get(i).getLNT() + "</td>");
                  out.print("  <td>" + dtos.get(i).getSearched_at() + "</td>");
                  out.print("  <td>");
                  out.print("    <form method=\"get\" action=\"/delete-history\">");
                  out.print("      <input type=\"hidden\" id=\"history_id\" name=\"history_id\" value=\"" +
                          dtos.get(i).getHistory_id() +
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
