package com.zqq.ssm.service;


import com.zqq.ssm.pojo.Mail;

/**
 * @Author zqq
 * @Date 2019/12/10  11:22
 */
public interface IMailService {
    /**
     *  发送邮件
     * @return
     * @throws Exception
     */
    void sendEmail(Mail mail) throws Exception;
}
