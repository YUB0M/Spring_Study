package org.zerock.domain;

import lombok.Data;

@Data
public class SampleDTO {
    // @Data 어노테이션은 getter/setter/toString을 자동 생성해준다.

    private String name;
    private int age;

}
