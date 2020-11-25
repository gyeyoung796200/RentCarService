import com.spring.rentcar.domain.BoardVO;
import com.spring.rentcar.persistence.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class BoardDAOTest {

    @Inject
    private BoardDAO boardDAO;

    @Test
    public void insertBoard() throws Exception{

        for(int i = 1; i < 201; i++){
            BoardVO boardVO = new BoardVO();
            boardVO.setTitle(i+"번 글제목");
            boardVO.setWriter(i+"번 작성자");
            boardVO.setContent(i+"번 내용");
            boardDAO.insertBoard(boardVO);
        }
    }
}
