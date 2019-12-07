package com.zqq.ssm.service;

import com.zqq.ssm.exception.UserException;
import com.zqq.ssm.pojo.User;

import java.util.List;

/**
 * @Author zqq
 * @Date 2019/10/22  14:14
 */
public interface IUserService {
    List<User> findAllUser();
    User findUserById(Integer userId) throws UserException;
    void updateUser(User user);
}
