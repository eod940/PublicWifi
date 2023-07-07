package org.publicwifi.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicWifiDTO {

    // 관리번호 (PK)
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

    public PublicWifiDTO(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1, String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY, String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR, String x_SWIFI_REMARS3, double LAT, double LNT, String WORK_DTTM) {
        X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
        X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
        X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
        X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
        X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
        X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
        X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
        X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
        X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
        X_SWIFI_CMCWR = x_SWIFI_CMCWR;
        X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
        X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
        X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
        this.LAT = LAT;
        this.LNT = LNT;
        this.WORK_DTTM = WORK_DTTM;
    }

    public PublicWifiDTO publicWifiDTO(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1, String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY, String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR, String x_SWIFI_REMARS3, double LAT, double LNT, String WORK_DTTM) {
        return new PublicWifiDTO(x_SWIFI_MGR_NO, x_SWIFI_WRDOFC, x_SWIFI_MAIN_NM, x_SWIFI_ADRES1, x_SWIFI_ADRES2, x_SWIFI_INSTL_FLOOR, x_SWIFI_INSTL_TY, x_SWIFI_INSTL_MBY, x_SWIFI_SVC_SE, x_SWIFI_CMCWR, x_SWIFI_CNSTC_YEAR, x_SWIFI_INOUT_DOOR, x_SWIFI_REMARS3, LAT, LNT, WORK_DTTM);
    }
}
