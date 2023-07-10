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
    <%
      ArrayList<PublicWifiDTO> wifiDTOS = (ArrayList<PublicWifiDTO>) request.getAttribute("wifiDTOS");
    %>
    <main>
      <table>
        <tr>
          <th>관리번호</th>
          <%
            out.print("<th>");
            out.print(wifiDTOS.get(0).getX_SWIFI_MGR_NO());
            out.print("</th>");
          %>
        </tr>
        <tr>
          <th>자치구</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_WRDOFC() + "</th>");
          %>
        </tr>
        <tr>
          <th>와이파이명</th>
          <%
            out.print("  <th>");
            out.print("    <a href=\"/detail?mgrNo=" + wifiDTOS.get(0).getX_SWIFI_MGR_NO() + "\">");
            out.print(wifiDTOS.get(0).getX_SWIFI_MAIN_NM());
            out.print("    </a>");
            out.print("  </th>");
          %>
        </tr>
        <tr>
          <th>도로명주소</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_ADRES1() + "</th>");
          %>
        </tr>
        <tr>
          <th>상세주소</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_ADRES2() + "</th>");
          %>
        </tr>
        <tr>
          <th>설치위치(층)</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_INSTL_FLOOR() + "</th>");
          %>
        </tr>
        <tr>
          <th>설치유형</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_INSTL_TY() + "</th>");
          %>
        </tr>
        <tr>
          <th>설치기관</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_INSTL_MBY() + "</th>");
          %>
        </tr>
        <tr>
          <th>서비스 구분</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_SVC_SE() + "</th>");
          %>
        </tr>
        <tr>
          <th>망 종류</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_CMCWR() + "</th>");
          %>
        </tr>
        <tr>
          <th>설치년도</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_CNSTC_YEAR() + "</th>");
          %>
        </tr>
        <tr>
          <th>실내외구분</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_INOUT_DOOR() + "</th>");
          %>
        </tr>
        <tr>
          <th>WIFI 접속환경</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getX_SWIFI_REMARS3() + "</th>");
          %>
        </tr>
        <tr>
          <th>X좌표</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getLAT() + "</th>");
          %>
        </tr>
        <tr>
          <th>Y좌표</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getLNT() + "</th>");
          %>
        </tr>
        <tr>
          <th>작업일자</th>
          <%
            out.print("  <th>" + wifiDTOS.get(0).getWORK_DTTM() + "</th>");
          %>
        </tr>
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
