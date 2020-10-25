package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { org.zerock.config.RootConfig.class })
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert() {
        BoardVO board = new BoardVO();

        board.setTitle("새로 작성하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("newbie");

        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey(){
        BoardVO board = new BoardVO();

        board.setTitle("새로 작성하는 글 SelectKey 테스트");
        board.setContent("새로 작성하는 내용 SelectKey 테스트");
        board.setWriter("newbie");

        mapper.insertSelectKey(board);
        log.info(board);
    }

    @Test
    public void testRead(){
        BoardVO board = mapper.read(20);
        // 이미 존재하는 게시글 번호로 테스트(예시와는 달리 int이므로 5L X)
        log.info(board);
    }

    @Test
    public void testDelete(){
        log.info("DELETE COUNT : " + mapper.delete(35));
    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        board.setBno(1L);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");
        board.setWriter("수정된 작성자");

        int count = mapper.update(board);
        log.info("UPDATE COUNT : " + count);
    }

}
