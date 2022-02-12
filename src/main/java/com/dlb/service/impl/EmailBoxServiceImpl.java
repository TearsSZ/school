package com.dlb.service.impl;

import com.dlb.pojo.EmailBox;
import com.dlb.mapper.EmailBoxMapper;
import com.dlb.service.IEmailBoxService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 大萝卜
 * @since 2022-02-11
 */
@Service
@SuppressWarnings("all")
public class EmailBoxServiceImpl extends ServiceImpl<EmailBoxMapper, EmailBox> implements IEmailBoxService {

    @Resource
    private EmailBoxMapper emailBoxMapper;

    @Autowired
    private JavaMailSenderImpl mailSender;//邮件对象

    /**
     * @param title 邮件标题
     * @param emailContext  邮件内容
     * @param recipients 收件人
     */
    @Override
    public void sendEmailTo(String title,String emailContext,String recipients){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(emailContext);
        simpleMailMessage.setTo(recipients);//接收人
        simpleMailMessage.setFrom("dlbcitrus@qq.com");//发送人
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送带有附件的邮件
     * @param title 标题
     * @param emailContext  内容
     * @param multipartFile 附件
     * @param recipients   接收人
     * @throws Exception
     */
    @Override
    public void uploadEmailTo(String title, String emailContext, MultipartFile multipartFile, String recipients) throws Exception {
        String sendName = "dlbcitrus@qq.com";
        InputStream inputStream = multipartFile.getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);//读到内存
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setSubject(title);//发送标题
        helper.setText(emailContext,true);//富文本
        //附件
        //helper.addAttachment("2.jpg",new File("E:\\Pixiv PE\\36933.png"));
        helper.addAttachment(multipartFile.getOriginalFilename(),new ByteArrayResource(buffer));
        helper.setTo(recipients);//接收人
        helper.setFrom("坑人网"+'<'+sendName+'>');//发送人
        mailSender.send(mimeMessage);
    }

    /**
     * 激活邮件用
     * @param title 邮件标题
     * @param emailContext  邮件内容
     * @param recipients   邮件接收人
     * @throws MessagingException
     */
    @Override
    public void activateEmailTo(String title,String emailContext,String recipients) throws MessagingException {
        String sendName = "dlbcitrus@qq.com";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        //返回组装次对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        helper.setSubject(title);//发送标题
        helper.setText(emailContext,true);//富文本--开启HTML模式
        helper.setTo(recipients);//接收人
        helper.setFrom("坑人网"+'<'+sendName+'>');//发送人
        mailSender.send(mimeMessage);
    }

    @Override
    public void insertByOne(EmailBox emailBox) {
        baseMapper.insert(emailBox);
    }
    /**
     * 开启定时任务需要在开始类上面追加两个注解
     * EnableAsync//开启异步注解功能
     * EnableScheduling//开启定时功能的注解
     *
     * cron 表达式,这个要设置的时间 可以百度
     * 秒 分  时 日 月 周几(0-7 表示一周)
     * 30 58 10 * * ? 每天的10点58分30 执行一次
     * 30 0/5 10,12 * * ? 每天的10点和12点,每隔5分钟执行一次
     * 省略不写用*
     */
    @Scheduled(cron = "30 58 10 * * ?")
    public void a(){
        System.out.println("定时任务");
    }




    /**
     * 获取模板字符串
     * @param templateName 模板名字
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
