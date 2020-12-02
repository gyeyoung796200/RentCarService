import com.spring.rentcar.domain.ReplyVO;
import com.spring.rentcar.persistence.ReplyDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class ReplyDAOTest {

    @Inject
    private ReplyDAO replyDAO;

    @Test
    public void insert() throws Exception{

        for(int i = 0 ; i < 100; i++){

            ReplyVO replyVO = new ReplyVO();
            replyVO.setBno(10);
            replyVO.setReplyWriter("작성자"+i);
            replyVO.setReplyText("내용"+i);

            replyDAO.insert(replyVO);
        }
    }

}
