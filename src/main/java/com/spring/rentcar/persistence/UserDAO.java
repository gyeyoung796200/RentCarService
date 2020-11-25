package com.spring.rentcar.persistence;

import com.spring.rentcar.domain.UserVO;

public interface UserDAO {


    //회원가입
    public void insertUser(UserVO userVO) throws Exception;

    //회원아이디로 조회
    public UserVO readUser(String userId) throws Exception;

    //회원 아이디, 비밀번호로 조회
    public UserVO readUserWithPw(String userId, String userPw) throws Exception;


}
