package com.zqq.ssm.pojo;

import com.zqq.ssm.group.ValidGroup1;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author zqq
 * @Date 2019/10/22  15:10
 */
public class User implements Serializable {
    private Long userid;
    //校验名称在1--30字符之间
    @Size(min = 3, max = 30, message = "{user.username.length.error}",groups = {ValidGroup1.class})
    private String username;
    @NotNull(message = "{user.password.isnull}")
    private String password;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
