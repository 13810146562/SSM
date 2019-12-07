package com.zqq.ssm.controller;


import com.zqq.ssm.pojo.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zqq
 * @Date 2019/10/22  14:54
 */
public class UserController implements Controller {
//   @Resource(name = "userService")
//    private IUserService userService;
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
       // List<User> users = userService.findAllUser();
        User user = new User();
        user.setUserid(1L);
        user.setUsername("zqq");
        user.setPassword("123");
        List<User> users = new ArrayList<>();
        users.add(user);
        ModelAndView modelAndView = new ModelAndView();
        //相当于request.setAttribut
        modelAndView.addObject("users",users);
        //指定视图
        modelAndView.setViewName("WEB-INF/jsp/user/userList.jsp");
        return modelAndView;
    }
}
