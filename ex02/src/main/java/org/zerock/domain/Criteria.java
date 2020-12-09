package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Criteria {
    // Criteria : 검색의 기준

    // 시작지점
    private int pageNum;
    // 크기
    private int amount;
    private int startIndex;

    public Criteria() {
        this(1, 20, 0);
    }

    public Criteria(int pageNum, int amount, int startIndex) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.startIndex = startIndex;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.startIndex = (this.pageNum -1 ) * this.amount;
    }
}
