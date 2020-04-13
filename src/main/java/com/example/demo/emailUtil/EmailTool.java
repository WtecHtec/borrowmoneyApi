package com.example.demo.emailUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Slf4j
@Component
public class EmailTool {

    @Autowired
    private JavaMailSender javaMailSender;

    // 单发
    public void sendOneEmail(String fromEmail,String toEmail,String title,String context){

        SimpleMailMessage message =  new SimpleMailMessage();

        message.setSubject(title);

        // 从哪里 发送
        message.setFrom(fromEmail);

        // 到哪里 可设置多个，实现群发
        message.setTo(toEmail);

        message.setSentDate(new Date());

        message.setText(context);
      try {
          javaMailSender.send(message);
          log.info("邮件发送成功");
      } catch (Exception  e){
          e.printStackTrace();
          log.error("邮件发送失败");
    }


    }

    // 发送附件

    public void sendFileEmail(String fromEmail,String toEmail,String title,String context,String fileName,String filePath) throws MessagingException {
        MimeMessage  mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(title);
        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSentDate(new Date());
        helper.setText(context);
        helper.addAttachment(fileName,new File(filePath));

        javaMailSender.send(mimeMessage);
    }



}
