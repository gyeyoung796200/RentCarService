import com.spring.rentcar.domain.UserVO;
import com.spring.rentcar.persistence.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml"})
public class UserDAOTest {

    @Inject
    private UserDAO userDAO;

    // 회원 가입 테스트
    @Test
    public void testInsertUser() throws Exception {
        UserVO userVO = new UserVO();
        userVO.setUserId("user4");
        userVO.setUserPw("user4");
        userVO.setUserName("유저4");
        userVO.setUserEmail("user4@test.com");

        userDAO.insertUser(userVO);
    }
}
