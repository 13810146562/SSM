package com.zqq.ssm;

import com.zqq.ssm.pojo.Mail;
import com.zqq.ssm.service.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Author zqq
 * @Date 2019/12/10  16:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.service.xml")
public class MailTest {
    @Resource(name = "mailService")
    private IMailService mailService;

    @Test
    public void test(){
        Mail mail = new Mail();
        mail.setEmailFrom("zhangqq0516@163.com");
        mail.setToAddress("124829085@qq.com");
        mail.setSubject("图片广告");
        mail.setContext("<html><body><img src='cid:zqq'><p>你好，欢迎光临</p></body></html>");
        try {
            mailService.sendEmail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
