package com.zqq.ssm.mapper;

import com.zqq.ssm.pojo.User;

import java.util.List;

/**
 * @Author zqq
 * @Date 2019/10/22  15:09
 */
public interface UserMapper {
    List<User> findAllUser();

    User findUserById(Integer userId);

    void updateUser(User user);
}
