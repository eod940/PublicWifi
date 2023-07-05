package org.publicwifi.v1.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicWifi {

    // PK
    private int public_wifi_id;
    // 관리번호
    private String X_SWIFI_MGR_NO;
    // 자치구
    private String X_SWIFI_WRDOFC;
    // 와이파이명
    private String X_SWIFI_MAIN_NM;
    // 도로명주소
    private String X_SWIFI_ADRES1;
    // 상세주소
    private String X_SWIFI_ADRES2;
    // 설치위치(층)
    private String X_SWIFI_INSTL_FLOOR;
    // 설치유형
    private String X_SWIFI_INSTL_TY;
    // 설치기관
    private String X_SWIFI_INSTL_MBY;
    // 서비스구분
    private String X_SWIFI_SVC_SE;
    // 망종류
    private String X_SWIFI_CMCWR;
    // 설치년도
    private String X_SWIFI_CNSTC_YEAR;
    // 실내외구분
    private String X_SWIFI_INOUT_DOOR;
    // wifi 접속환경
    private String X_SWIFI_REMARS3;
    // Y좌표
    private double LAT;
    // X좌표
    private double LNT;
    // 작업일자
    private String WORK_DTTM;
}
