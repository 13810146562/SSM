package com.zqq.ssm.controller;

import com.zqq.ssm.group.ValidGroup1;
import com.zqq.ssm.pojo.User;
import com.zqq.ssm.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @Author zqq
 * @Date 2019/10/23  14:41
 */
@Controller("userAnnotation")
//对URL进行分类管理，定义根路径
@RequestMapping("/user")
public class UserAnnotation {
    @Resource(name = "userService")
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(UserAnnotation.class);

    @RequestMapping("/findAllUser")
    public ModelAndView findAllUser() {
        List<User> users = userService.findAllUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("user/userList");
        return modelAndView;
    }

    @RequestMapping("/findUserById")
    public String findUserById(@RequestParam(value = "userid") Integer userId, Model model) throws Exception {
        User user = userService.findUserById(userId);
        model.addAttribute(user);
        String imgName = "123.jpg";
        model.addAttribute("imgName", imgName);
        return "user/editUser";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("userImg") MultipartFile userImg, Model model,
                             @Validated(value = {ValidGroup1.class}) User user, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            allErrors.forEach(error -> logger.error(error.getDefaultMessage()));
            model.addAttribute("allErrors", allErrors);
            return "user/editUser";
        }
        if (userImg != null) {
            logger.debug(userImg.getOriginalFilename());
            String originalFilename = userImg.getOriginalFilename();
            if (originalFilename != null && !originalFilename.equals("")) {
                String img_path = "D:\\upload\\";
                String fileName = "123.jpg";
                String filePath = img_path + fileName;
                File file = new File(filePath);
                userImg.transferTo(file);
            }
        }
        userService.updateUser(user);
        return "redirect:/user/findAllUser.action";
    }

    @RequestMapping("/jsonTest")
    @ResponseBody
    public User jsonTest(@RequestBody User user) {
        logger.debug(user.getUsername() + "," + user.getPassword());
        return user;
    }

    @RequestMapping("/jsonTest1")
    @ResponseBody
    public User jsonTest1(User user) {
        logger.debug(user.getUsername() + "," + user.getPassword());
        return user;
    }
}
