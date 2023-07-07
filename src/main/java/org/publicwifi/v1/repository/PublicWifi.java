package org.publicwifi.v1.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicWifi {

    // 1. 관리번호 (PK)
    private String X_SWIFI_MGR_NO;
    // 2. 자치구
    private String X_SWIFI_WRDOFC;
    // 3. 와이파이명
    private String X_SWIFI_MAIN_NM;
    // 4. 도로명주소
    private String X_SWIFI_ADRES1;
    // 5. 상세주소
    private String X_SWIFI_ADRES2;
    // 6. 설치위치(층)
    private String X_SWIFI_INSTL_FLOOR;
    // 7. 설치유형
    private String X_SWIFI_INSTL_TY;
    // 8. 설치기관
    private String X_SWIFI_INSTL_MBY;
    // 9. 서비스구분
    private String X_SWIFI_SVC_SE;
    // 10. 망종류
    private String X_SWIFI_CMCWR;
    // 11. 설치년도
    private String X_SWIFI_CNSTC_YEAR;
    // 12. 실내외구분
    private String X_SWIFI_INOUT_DOOR;
    // 13. wifi 접속환경
    private String X_SWIFI_REMARS3;
    // 14. Y좌표
    private double LAT;
    // 15. X좌표
    private double LNT;
    // 16. 작업일자
    private String WORK_DTTM;
}
