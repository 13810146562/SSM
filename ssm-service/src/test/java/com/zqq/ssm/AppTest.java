package com.zqq.ssm;

import static org.junit.Assert.assertTrue;

import com.zqq.ssm.pojo.User;
import com.zqq.ssm.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.service.xml")
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Resource(name = "userService")
    private IUserService userService;
    @Test
    public void test(){
        List<User> users = userService.findAllUser();
        users.forEach(System.out::println);
    }
}
