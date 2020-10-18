package org.zerock.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {
    //DTO는 파라미터를 자동으로 수집하는 역할을 함
    //전달하는 데이터가 객체타입이고 여러개를 전달해야 한다면 리스트를 포함하는 클래스를 설계함.

    private List<SampleDTO> list;

    public SampleDTOList(){
        list = new ArrayList<>();
    }

}
