package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // Mapper는 XML를 작성할 수 있지만 최소한의 코드를 작성하는 Mapper 인터페이스 사용
    // 그 후 RootConfig에서 @MapperScan을 이용해 처리

    // Unknown column 'sysdate' in 'field list'
    // 에러 이유 -> oracle : sysdate / Mysql : now()
    @Select("SELECT now() FROM dual")
    public String getTime();

    public String getTime2();
}
