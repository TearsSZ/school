package com.dlb.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.Data;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStream;

@Data
@SuppressWarnings("all")
public class SendEmail {
    //发送标题
    private String title = null;
    //邮件内容，可以是html标签 "<h2 style='color=red'>你好</h2>"
    private String emailContext = null;
    //默认开启HTML标签
    private Boolean flag = true;
    //附件名
    private String fileName = null;
    //附件路径 new File("E:\\Pixiv PE\\36933.png")
    private File filePath = null;
    //发送邮件的人
    private String sendName = "dlbcitrus@qq.com";

    //获取spring中的bean  JavaMailSenderImpl
    public JavaMailSenderImpl getJavaMailSenderImpl() throws MessagingException {
        AbstractApplicationContext as = new ClassPathXmlApplicationContext("applicationContext.xml");
        return as.getBean("javaMailSender", JavaMailSenderImpl.class);
    }

    /**
     * 发送前先准备上面的参数
     * @param recipients 收件人
     * @throws MessagingException 发送失败
     */
    public void sendEmailTo(String recipients) throws MessagingException {
        JavaMailSenderImpl javaMailSender = getJavaMailSenderImpl();
        //创建消息对象
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        //返回组装次对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setSubject(title);//发送标题
        helper.setText(emailContext,flag);//富文本
        //抄送
       // helper.setCc("");
        //密送
        //helper.setBcc("");
        //回复邮件
        //simpleMailMessage.setReplyTo();
        //设置发送日期
        //simpleMailMessage.setSentDate();
        //普通附件.bin文件
        //helper.addInline("myLogo", new File("E:\\Pixiv PE\\36933.png"));
        //附件
        if (fileName != null && filePath != null)
            helper.addAttachment(fileName,filePath);
        //发送
        helper.setTo(recipients);//接收人
        helper.setFrom("大萝北"+'<'+sendName+'>');//发送人
        javaMailSender.send(mimeMessage);
    }

    /**
     * 以上传的形式发送邮件
     * @param multipartFile  要上传的文件
     * @param recipients    接收人
     * @throws Exception    上传的文件或发送失败
     */
    public void uploadEmailTo(MultipartFile multipartFile, String recipients) throws Exception {
        InputStream inputStream = multipartFile.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);//读到内存
        JavaMailSenderImpl javaMailSender = getJavaMailSenderImpl();
        //创建消息对象
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        //返回组装次对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setSubject(title);//发送标题
        helper.setText(emailContext,flag);//富文本
        helper.addAttachment(multipartFile.getOriginalFilename(),new ByteArrayResource(buffer));
        helper.setTo(recipients);//接收人
        helper.setFrom("坑人网"+'<'+sendName+'>');//发送人
        javaMailSender.send(mimeMessage);
    }


    /**
     * 激活邮件用
     * @param recipients 接收人
     * @throws MessagingException 邮件发送失败
     */
    public void activateEmailTo(String recipients) throws MessagingException {
        JavaMailSenderImpl javaMailSender = getJavaMailSenderImpl();
        //创建消息对象
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        //返回组装次对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setSubject(title);//发送标题
        helper.setText(emailContext,flag);//富文本
        helper.setTo(recipients);//接收人
        helper.setFrom("坑人网"+'<'+sendName+'>');//发送人
        javaMailSender.send(mimeMessage);
    }



    /**
     * 获取模板字符串
     * @param templateName 模板名字
     * @param data 模板中要添加的数据
     * @return 模板的字符串 出异常（只会出获取模板异常）有返回空的字符串
     */
    public String setTemplateString(String templateName, java.util.Map map){
        Configuration configurer = new Configuration();
        try {
            //Template template = configurer.getTemplate(templateName, "utf-8");
            //使用FileTemplateLoader
            TemplateLoader templateLoader = new FileTemplateLoader(new File("C:\\Code\\Car_ssm\\"));
            String path = "web\\WEB-INF\\temp\\"+templateName;
            configurer.setTemplateLoader(templateLoader);
            Template template = configurer.getTemplate(path, "UTF-8");
            //写入
            String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            return s;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
