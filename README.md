# Public Wifi Service
## :mag_right: 프로젝트의 내용과 목적
사용자 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발<br>

본 프로젝트는 [서울시 공공와이파이 서비스 위치 정보(Open API)](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)
자원을 끌어와서 데이터를 마이그레이션 한 뒤,
사용자의 위치를 기반으로 데이터를 출력하고자 한다. <br>

본 프로젝트의 목적은 Java와 Web, DB의 개념과 이를 통한 응용을 통하여 Java, JSP, SQLite의 동작을 배우고자 한다. 뿐만 아니라 타 시스템 
호출(Open API)을 호출하는 문제 해결 능력을 키우고자 한다.<br>

- 프로젝트를 하면서 느낀것들: https://eod940.tistory.com/29

### 기간: 23년 6월 21일 ~ 23년 7월 10일

## :bulb: Stacks
<img alt="Static Badge Java8" src="https://img.shields.io/badge/java_8-5382A1?style=for-the-badge&logo=openjdk&logoColor=white"> <img alt="Static Badge Sqlite" src="https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=SQLite&logoColor=white"> <img alt="Static Badge Jsp" src="https://img.shields.io/badge/Jsp-F26522?style=for-the-badge&logo=openjdk&logoColor=white"> <img alt="Static Badge gradle" src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle">


```
version: 
    open jdk 1.8.0_371
    sqlite 3.40.1
    tomcat 9.0.22
    jsp 2.3
    gradle 8.0
```


## :baby: 역할
:thumbsup: 이대영
- [공공 API 불러오기](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/servlet/wifi/GetWifiServlet.java) 기능 ![progress bar](https://geps.dev/progress/100)
- [사용자 위치 관련](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/others/Distance.java) 기능 ![progress bar](https://geps.dev/progress/90?successColor=ff9900)
- [히스토리](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/HistoryDAO.java) 기능 ![progress bar](https://geps.dev/progress/100)
- [와이파이 상세보기](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/PublicWifiDAO.java) 기능 ![progress bar](https://geps.dev/progress/100)
- [북마크](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/BookmarkDAO.java) 기능 ![progress bar](https://geps.dev/progress/100)


## :+1: 요구사항 분석
- 서울시 공공 와이파이 정보를 포함하는 테이블을 설계 후, 각 조건에 맞는 기능을 제공한다.

1. 서울시 공공와이파이 서비스 API 요청
    - Java(okhttp3, gson 라이브러리)를 이용해 [API 요청 및 결과 테스트(:line 45)](https://github.com/eod940/PublicWifi/blob/master/src/test/java/JsonTest.java) 후 진행
2. 근처 Wifi 정보 보기 기능 실행
   - 위경도 좌표 기준으로 거리 계산 (km 기준)
   - 공공와이파이 위치와 [사용자의 위치](https://github.com/eod940/PublicWifi/blob/master/src/main/webapp/WEB-INF/static/custom.js)를 이용해 가장 가까운 위치를 기준으로 사용자에게 정보 제공
3. 북마크, 히스토리 기능
   - CRUD 기능 구현
4. 와이파이 기능
   - 상세보기 기능 구현

## :ballot_box_with_check: 코드 패키지 구조
- 앱 위치 `main/.../publicwifi/v1`

```shell
.
├── main
│   ├── generated
│   ├── java
│   │   └── org
│   │       ├── example
│   │       │   └── Main.java
│   │       └── publicwifi
│   │           └── v1
│   │               ├── communication
│   │               │   └── ApiCom.java
│   │               ├── dao
│   │               │   ├── BookmarkDAO.java
│   │               │   ├── HistoryDAO.java
│   │               │   └── PublicWifiDAO.java
│   │               ├── dto
│   │               │   ├── BookmarkDTO.java
│   │               │   ├── HistoryDTO.java
│   │               │   └── PublicWifiDTO.java
│   │               ├── others
│   │               │   ├── Distance.java
│   │               │   └── Pagenation.java
│   │               ├── repository
│   │               │   └── PublicWifi.java
│   │               └── servlet
│   │                   ├── MainServlet.java
│   │                   ├── ViewServlet.java
│   │                   ├── bookmark
│   │                   │   ├── AddToBookmarkServlet.java
│   │                   │   ├── CheckDelServlet.java
│   │                   │   ├── CheckEditServlet.java
│   │                   │   ├── CreateBookmarkServlet.java
│   │                   │   ├── DeleteBookmarkServlet.java
│   │                   │   ├── EditBookmarkServlet.java
│   │                   │   ├── GroupBookmarkServlet.java
│   │                   │   ├── InsertListBookmarkServlet.java
│   │                   │   ├── ListBookmarkServlet.java
│   │                   │   ├── MakeBookmarkServlet.java
│   │                   │   ├── SubmitBookmarkServlet.java
│   │                   │   └── ViewBookmarkGroupServlet.java
│   │                   ├── history
│   │                   │   ├── CreateHistoryServlet.java
│   │                   │   ├── DeleteHistoryServlet.java
│   │                   │   └── GetHistoryServlet.java
│   │                   └── wifi
│   │                       ├── CreateWifiServlet.java
│   │                       ├── DeleteWifiServlet.java
│   │                       ├── DetailWifiServlet.java
│   │                       ├── DropWifiServlet.java
│   │                       └── GetWifiServlet.java
│   ├── resources
│   └── webapp
│       ├── WEB-INF
│       │   ├── static
│       │   │   └── custom.js
│       │   ├── views
│       │   │   ├── bookmark-add-list-submit.jsp
│       │   │   ├── bookmark-add-submit.jsp
│       │   │   ├── bookmark-del-submit.jsp
│       │   │   ├── bookmark-edit-submit.jsp
│       │   │   ├── bookmark-group-add.jsp
│       │   │   ├── bookmark-group-del.jsp
│       │   │   ├── bookmark-group-edit.jsp
│       │   │   ├── bookmark-group.jsp
│       │   │   ├── bookmark-list.jsp
│       │   │   ├── detail.jsp
│       │   │   ├── history.jsp
│       │   │   ├── load-wifi.jsp
│       │   │   └── main.jsp
│       │   └── web.xml
│       └── index.jsp
└── test
    ├── generated_tests
    ├── java
    │   ├── DBTest.java
    │   ├── DMLTest.java
    │   ├── GetTest.java
    │   ├── InsertTest.java
    │   ├── JsonTest.java
    │   └── jsonTest
    │       ├── PagenationTest.java
    │       ├── Person.java
    │       └── Wifi.java
    └── resources
```

### 앱 설계 흐름도
```mermaid
flowchart TD
   A[fa:fa-computer Client] <-->|URL, foward| B

   subgraph ide1 [PublicWifi Server]
      B((Servlet)) <-.->|historyDTO| D[history DAO]
      B <-.->|publicWifiDTO| E[public_wifi DAO]
      B <-.->|bookmarkDTO| F[bookmark DAO]

      D <-.->|historyDTO| db[(fa:fa-database DB)]
      E <-.->|publicWifiDTO| db
      F <-.->|bookmarkDTO| db
   end

   B <-->|URL, JSON|G[서울시 공공와이파이]
```

### ERD

---

<img width="80%" alt="publicwifi_erd" src="https://github.com/eod940/PublicWifi/assets/51254234/cba09db5-5af6-4782-a80e-af9b5c83f171">

- [public_wifi](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/PublicWifiDAO.java), [history](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/HistoryDAO.java), [bookmark](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/BookmarkDAO.java) 3개의 테이블


### 서울시 공공와이파이 Api 가져오기

---

![api_가져오기](https://github.com/eod940/PublicWifi/assets/51254234/b15e7c8e-c44d-4e4d-9b6b-058a2f7e8463)

- [GetWifiServelt](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/servlet/wifi/GetWifiServlet.java) 에서 api 호출을 위한 url 생성 후 json 값을 받아줍니다.
- 받아온 json 값은 gson library를 이용해 public_wifi 테이블에 맞게 파싱합니다.
- 받아온 값은 [PublicWifiDTO](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dto/PublicWifiDTO.java)에 넣어진 뒤 DB 해당 테이블에 [insert](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/PublicWifiDAO.java)됩니다.


### 위치 가져오기

---

![location_가져오기](https://github.com/eod940/PublicWifi/assets/51254234/bc86e621-646f-46b3-b1e4-ead2cb8db930)

- `내 위치 가져오기` 버튼을 누르면 [javaScript](https://github.com/eod940/PublicWifi/blob/master/src/main/webapp/WEB-INF/static/custom.js)가 동작합니다.
- 스크립트 파일을 분리해서 관리하려 했지만 이미 많은 jsp 파일들을 복사해 놓은 상태였다. (시간도 없고 게을러서)
- css 파일도 마찬가지로 시간이 부족했다.


### History 보기

---

![history_보기](https://github.com/eod940/PublicWifi/assets/51254234/d805dad1-a06b-4db0-8de1-95e6d015c710)

- `근처 WIFI 정보 보기` 버튼을 누르면 [ViewServlet](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/servlet/ViewServlet.java)이 동작합니다.
  - Get 방식으로 입력된 좌표 쿼리를 날립니다. 
  - 받은 좌표 값을 기준으로 저장된 와이파이 정보를 Select하고, history 테이블에 좌표와 현재시간을 저장합니다.


### Wifi 상세정보 보기

---

![detail_가져오기](https://github.com/eod940/PublicWifi/assets/51254234/f30d308e-cb60-48f4-b9e9-b07a3775dc09)

- `와이파이 명` 컬럼의 데이터를 누르면 [DetailWifiServlet](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/servlet/wifi/DetailWifiServlet.java)이 작동합니다.
  - 북마크 정보와 와이파이 정보를 가져와 [/WEB-INF/views/detail.jsp](https://github.com/eod940/PublicWifi/blob/master/src/main/webapp/WEB-INF/views/detail.jsp)로 forward 해줍니다.


### Bookmark 보기

---

![bookmark_관리](https://github.com/eod940/PublicWifi/assets/51254234/2b35955f-c7b8-4e98-a7dc-79e855316b78)

![bookmark insert](https://github.com/eod940/PublicWifi/assets/51254234/1cf948ea-4700-4715-9222-5ada6fa2572b)

![bookmark_edit](https://github.com/eod940/PublicWifi/assets/51254234/a6ebcf06-1ebf-4b71-bbad-02fd37f82406)

- 북마크관련 기능을 누르면 각 기능에 맞는 [BookmarkServlet](https://github.com/eod940/PublicWifi/tree/master/src/main/java/org/publicwifi/v1/servlet/bookmark)으로 요청이 들어갑니다.
- 북마크는 [CRUD(생성, 읽기, 수정, 삭제)](https://github.com/eod940/PublicWifi/blob/master/src/main/java/org/publicwifi/v1/dao/BookmarkDAO.java) 기능이 모두 구현되어 있습니다.
  - 북마크 수정시 bookmark 테이블의 edited_at 컬럼에 현재시간이 추가됩니다.
