package com.spring.rentcar.controller;

import com.spring.rentcar.domain.UserVO;
import com.spring.rentcar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @GetMapping("/insert")
    public ModelAndView insertGet() throws Exception{

        ModelAndView mav = new ModelAndView();
        mav.addObject("mainData", "user/insertForm.jsp");
        mav.setViewName("index");

        return mav;
    }

    @PostMapping("/insert")
    public String insertPost(UserVO userVO) throws Exception{

        userService.insertUser(userVO);

        return "index";
    }
}
