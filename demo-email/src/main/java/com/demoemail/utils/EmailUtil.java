package com.demoemail.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Random;

@Log4j2
@Component
public class EmailUtil {
    @Value("${spring.mail.username}")
    private String email;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    public boolean sendTestEmail(String toEmail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email); // 发件地址
            message.setTo(toEmail); // 接收地址
            message.setSubject("测试"); // 标题
            message.setText("这是一封测试邮件，请忽略。"); // 内容
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean sendHtmlTestEmail(String toEmail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(toEmail);
            helper.setSubject("测试");
            helper.setText("<p style='color:#6db33f'>这是一封测试邮件，请忽略。</p>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean sendAnnexTestEmail(String toEmail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(toEmail);
            helper.setSubject("测试");
            helper.setText("<p style='color: red'>详情查看附件。</p>", true);
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("demo-email/src/main/resources/static/annex/test.txt"));
            helper.addAttachment("test.txt", file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean sendStaticTestEmail(String toEmail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(toEmail);
            helper.setSubject("测试");
            helper.setText("<html><body><h1>测试</h1><img src='cid:img'/></body></html>", true);
            // 传入图片
            FileSystemResource file = new FileSystemResource(new File("demo-email/src/main/resources/static/image/test.jpg"));
            helper.addInline("img", file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
        return true;
    }

    public boolean sendThymeleafTestEmail(String toEmail) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(email);
            helper.setTo(toEmail);
            helper.setSubject("测试");
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", new Random().nextInt(9999 - 1000 + 1) + 1000);
            String template = templateEngine.process("test", context);
            helper.setText(template, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            log.error(e);
            return false;
        }
        return true;
    }
}
