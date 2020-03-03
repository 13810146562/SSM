package com.zqq.ssm.converter;

import com.zqq.ssm.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 *
 * @Author zqq
 * @Date 2019/11/13  16:32
 */
public class ExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        UserException userException;
        logger.info("异常信息："+e.getMessage());
        //解析异常类型
        if(e instanceof UserException)
            userException = (UserException) e;
        else
            userException = new UserException("未知错误");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",userException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
