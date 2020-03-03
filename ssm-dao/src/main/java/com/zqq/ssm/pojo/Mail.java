package com.zqq.ssm.pojo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zqq
 * @Date 2019/12/10  11:34
 */
public class Mail implements Serializable {
    @NotNull(message = "发件人为空")
    private String emailFrom;
    @NotNull(message = "收件人为空")
    private String toAddress;
    private String ccAddress;
    private String bccAddress;
    private String subject;
    private String context;
    private List<String> attachs = new ArrayList<>();

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getCcAddress() {
        return ccAddress;
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddress = ccAddress;
    }

    public String getBccAddress() {
        return bccAddress;
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public List<String> getAttachs() {
        return this.attachs;
    }

    public void addAttach(String attach) {
        this.attachs.add(attach);
    }
}
