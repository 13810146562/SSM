package com.zqq.ssm.service.impl;

import com.zqq.ssm.pojo.Mail;
import com.zqq.ssm.service.IMailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @Author zqq
 * @Date 2019/12/10  14:17
 */
@Service("mailService")
public class MailServiceImpl implements IMailService {
    @Resource(name = "javaMailSender")
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setFrom(mail.getEmailFrom());//发件人
        if(null != mail.getToAddress()){
            String[] address = mail.getToAddress().split(";");
            mimeMessageHelper.setTo(address);//收件人
        }
        mimeMessageHelper.setBcc("124829085@qq.com");//抄送
        mimeMessageHelper.setCc("zhangqq0516@163.com");//暗送
        if (mail.getSubject() != null)
            mimeMessageHelper.setSubject(mail.getSubject());
        else
            mimeMessageHelper.setSubject("来自zqq的邮件");
        mimeMessageHelper.setText(mail.getContext(),true);
        //添加图片
        mimeMessageHelper.addInline("zqq",new File("E:\\zqq资料\\照片\\image.jpg"));
        //添加附件
        mimeMessageHelper.addAttachment("zqq1",new File("E:\\zqq资料\\学习记录\\Java\\IDEA.docx"));
        mimeMessageHelper.setSentDate(new Date());
        javaMailSender.send(mimeMessage);
    }
}