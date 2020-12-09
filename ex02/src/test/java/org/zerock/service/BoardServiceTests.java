package org.zerock.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_ = { @Autowired })
    private BoardService service;


    @Test
    public void testExist() {
        log.info(service);
        assertNotNull(service);
    }


    @Test
    public void testRegister() {

        BoardVO board = new BoardVO();

        board.setTitle("제목 제목 제목 제목 룰루 랄라 룰루 랄라");
        board.setContent("내용 내용 내용 내용 내용 내용 내용 ");
        board.setWriter("new user");

        service.register(board);

        log.info("new bno : " + board.getBno());

    }


    //여러개의 게시글 조회
    @Test
    public void testGetList() {
        // service.getList().forEach(board -> log.info(board));
        service.getList(new Criteria(2, 10, 0)).forEach(board -> log.info(board));
    }


    //하나의 게시글 조회
    @Test
    public void testGet() {
        log.info(service.get(28L));
    }
    //|28  |테스트 제목 |테스트 내용  |user00 |2020-10-23 02:54:05.0 |2020-10-23 02:54:05.0 |

    @Test
    public void testDelete() {
        // 우선 게시글의 번호를 확인하여 존재 여부를 체크하고 삭제
        log.info("REMOVE RESULT :" + service.remove(18L));
    }
    //231824.761 [INFO ] BoardServiceTests.testDelete():59 REMOVE RESULT :false -> 없다면 False
    //231720.342 [INFO ] BoardServiceTests.testDelete():59 REMOVE RESULT :true -> 있다면 True 후 삭제

    @Test
    public void testUpdate() {
        BoardVO board = service.get(50L);

        if (board == null) {
            return;
        } else {
            board.setTitle("제목을 수정합니다.");
            log.info("MODIFY RESULT : " + service.modify(board));
        }
        //232255.247 [INFO ] BoardServiceImpl.get():37 get.......50
        //232255.340 [INFO ] BoardServiceImpl.modify():45 modify........BoardVO(bno=50, title=제목을 수정합니다., content=새로 작성하는 내용 SelectKey 테스트, writer=newbie, regDate=Mon Oct 26 00:56:58 KST 2020, updateDate=Mon Oct 26 00:56:58 KST 2020)
    }

}
