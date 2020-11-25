package com.spring.rentcar.persistence;

import com.spring.rentcar.domain.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.spring.rentcar.mappers.UserMapper";

    @Override
    public void insertUser(UserVO userVO) throws Exception {
        sqlSession.insert(NAMESPACE+".insertUser", userVO);
    }

    @Override
    public UserVO readUser(String userId) throws Exception {

        return sqlSession.selectOne(NAMESPACE+".readUser", userId);
    }

    @Override
    public UserVO readUserWithPw(String userId, String userPw) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("userPw", userPw);

        return sqlSession.selectOne(NAMESPACE+".readUserWithPw"+ map);

    }
}
