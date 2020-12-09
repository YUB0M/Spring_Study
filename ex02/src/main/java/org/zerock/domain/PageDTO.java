package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {

    private int startPage, endPage;
    private boolean prev, next;

    private int total;
    private Criteria cri;


    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        // 끝 페이지
        this.endPage = (int) (Math.ceil(cri.getPageNum() / 20.0)) * 10;
        // 시작 페이지
        this.startPage = this.endPage - 19;

        // 진짜 끝 페이지
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        if (realEnd <= this.endPage) {
            this.endPage = realEnd;
        }

        // 이전 페이지
        this.prev = this.startPage > 1;
        // 다음 페이지
        this.next = this.endPage < realEnd;
    }

}
