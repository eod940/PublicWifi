package org.publicwifi.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {

    // 관리번호 (PK)
    private int history_id;
    // X좌표
    private double LAT;
    // Y좌표
    private double LNT;
    // 작업일자
    private String searched_at;

    public HistoryDTO(double LAT, double LNT) {
        this.LAT = LAT;
        this.LNT = LNT;
    }

    public HistoryDTO(int history_id, double LAT, double LNT, String searched_at) {
        this.history_id = history_id;
        this.LAT = LAT;
        this.LNT = LNT;
        this.searched_at = searched_at;
    }

    public HistoryDTO historyDTO(double LAT, double LNT) {
        return new HistoryDTO(LAT, LNT);
    }

    public HistoryDTO historyDTO(int history_id, double LAT, double LNT, String searched_at) {
        return new HistoryDTO(history_id, LAT, LNT, searched_at);
    }
}
