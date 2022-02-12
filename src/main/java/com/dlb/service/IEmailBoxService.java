package com.dlb.service;

import com.dlb.pojo.EmailBox;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
public interface IEmailBoxService extends IService<EmailBox> {

    void sendEmailTo(String title,String emailContext,String recipients);

    void uploadEmailTo(String title, String emailContext, MultipartFile multipartFile, String recipients) throws Exception;

    void activateEmailTo(String title,String emailContext,String recipients) throws MessagingException;


    void insertByOne(EmailBox emailBox);
}
