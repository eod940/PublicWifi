package org.publicwifi.v1.others;

public class Pagenation {
    private final long totalCount;

    public Pagenation(long totalCount) {
        this.totalCount = totalCount;
    }

    public String html(long pageIndex) {
        // 보여지는 글의 목록이 10개 (한 페이지당 보여지는 글의 수)
        long postsPerPage = 20;
        // 페이지 블럭의 개수 10개 (페이지nav에서 보여주는 블럭 수)
        long pageBlocks = 20;

        // pageIndex가 속한 블럭 번호 (1~10) -> 0, (11~12) -> 1, ...
        long currentBlock = pageIndex / pageBlocks;
        if (pageIndex != 0 && pageIndex % pageBlocks == 0) {
            // 0 말고 10, 20, ... 끝번호로 끝나면 하나 빼준다.
            currentBlock--;
        }
        // currentBlock의 맨 앞 시작번호
        long start = currentBlock * pageBlocks + 1;
        // currentBlock의 맨 뒤 끝 번호
        long end = (currentBlock + 1) * pageBlocks;

        // totalCount와 pageBlocks가 딱 맞는지 검증
        long checkEnd = totalCount/pageBlocks + 1;
        if (totalCount % pageBlocks == 0) {
            // 1~10개: 1, 11~20개:2, ... 111~120: 12, 121~130: 13
            checkEnd--;
        }

        if (end > checkEnd) {
            // 맨 뒤 끝 번호가 페이지 블럭 이상으로 넘어가면 안된다.
            // 그러므로 만약 범위가 넘어가면 페이지 블럭 마지막으로 세팅해준다.
            end = checkEnd;
        }


        StringBuilder sb = new StringBuilder();

        // 처음
        sb.append("<a href='#'>[처음]</a>\n").append("<a href='#'>[이전]</a>\n\n");;

        // 현재 페이지와 블럭
        for (long i = start; i <= end; i++) {
            if (i == pageIndex) {
                sb.append("<a href='#' class='on'>");
                sb.append(i).append("</a>\n");
            } else {
                sb.append("<a href='#'>");
                sb.append(i).append("</a>\n");
            }
        }  // end of for

        // 마지막
        sb.append("\n<a href='#'>[다음]</a>\n").append("<a href='#'>[마지막]</a>\n");;
        return sb.toString();
    }
}
