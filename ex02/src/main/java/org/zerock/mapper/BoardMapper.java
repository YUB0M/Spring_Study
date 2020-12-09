package org.zerock.mapper;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    //@Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    public void insert(BoardVO board);

    public Integer insertSelectKey(BoardVO board);

    public BoardVO read(long bno);
    //Mapper인터페이스 -> Mapper.xml -> 테스트(BoardMapperTests)

    public int delete(long bno);

    public int update(BoardVO board);

    public List<BoardVO> getList(Criteria cri);
    public int getTotalCount(Criteria cri);
}
