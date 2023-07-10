package org.publicwifi.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {

    // 관리번호 (PK)
    private int bookmark_id;
    // 북마크 이름
    private String bookmark_name;
    // 북마크 순서
    private int bookmark_num;
    // 작업일자
    private String created_at;
    // 수정일자
    private String edited_at;
    // FK
    private String marked_wifi_id;


    public BookmarkDTO(String bookmark_name, int bookmark_num) {
        this.bookmark_name = bookmark_name;
        this.bookmark_num = bookmark_num;
    }

    public BookmarkDTO(int bookmark_id, String bookmark_name, int bookmark_num) {
        this.bookmark_id = bookmark_id;
        this.bookmark_name = bookmark_name;
        this.bookmark_num = bookmark_num;
    }

    public BookmarkDTO(int bookmark_id, String marked_wifi_id) {
        this.bookmark_id = bookmark_id;
        this.marked_wifi_id = marked_wifi_id;
    }

    public BookmarkDTO(int bookmark_id, String bookmark_name, int bookmark_num, String created_at, String marked_wifi_id, String edited_at) {
        this.bookmark_id = bookmark_id;
        this.bookmark_name = bookmark_name;
        this.bookmark_num = bookmark_num;
        this.created_at = created_at;
        this.marked_wifi_id = marked_wifi_id;
        this.edited_at = edited_at;
    }

    public BookmarkDTO bookmarkDTO(String bookmark_name, int bookmark_num) {
        return new BookmarkDTO(bookmark_name, bookmark_num);
    }

    public BookmarkDTO bookmarkDTO(int bookmark_id, String marked_wifi_id) {
        return new BookmarkDTO(bookmark_id, marked_wifi_id);
    }

    public BookmarkDTO bookmarkDTO(int bookmark_id, String bookmark_name, int bookmark_num) {
        return new BookmarkDTO(bookmark_id, bookmark_name, bookmark_num);
    }

    public BookmarkDTO bookmarkDTO(int bookmark_id, String bookmark_name, int bookmark_num, String created_at, String marked_wifi_id, String edited_at) {
        return new BookmarkDTO(bookmark_id, bookmark_name, bookmark_num, created_at, marked_wifi_id, edited_at);
    }
}
