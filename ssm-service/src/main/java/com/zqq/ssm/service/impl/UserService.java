package com.zqq.ssm.service.impl;

import com.zqq.ssm.exception.UserException;
import com.zqq.ssm.mapper.UserMapper;
import com.zqq.ssm.pojo.User;
import com.zqq.ssm.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author zqq
 * @Date 2019/10/22  15:13
 */
@Service("userService")
public class UserService implements IUserService {
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        User user = userMapper.findUserById(userId);
        if(user == null)
            throw new UserException("查不到此用户");
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
