<%@ page import="java.util.LinkedList" %><%--
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
      <a href="${pageContext.request.contextPath}/bookmark-list">즐겨찾기 보기</a> |
      <a href="${pageContext.request.contextPath}/bookmark-group">즐겨찾기 그룹 관리</a> |
    </nav>

    <div>
      <form method="get" action="${pageContext.request.contextPath}/view">
        <label for="lat">LAT:</label>
        <input name="lat" type="text" id="lat" value="0.0">

        <label for="lnt">LNT:</label>
        <input name="lnt" type="text" id="lnt" value="0.0">

        <button type="button" onclick="clickBtn()">내 위치 가져오기</button>
        <button type="submit">근처 WIFI 정보 보기</button>
      </form>
    </div>

    <main>
      <table>
        <thead>
          <tr>
            <th>거리(Km)</th>
            <th>관리번호</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스 구분</th>
            <th>망 종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI 접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
          </tr>
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
