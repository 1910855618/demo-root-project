package com.demoemail.controller;

import com.demoemail.model.vo.ResponseVO;
import com.demoemail.utils.EmailUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;

@Log4j2
@RestController
@RequestMapping("/email")
public class EmailController {
    @Resource
    private EmailUtil emailUtil;

    @GetMapping("/test")
    public ResponseVO test(@RequestParam("email") String email) {
        log.info("to email: {}", email);
        boolean isOk = emailUtil.sendTestEmail(email);
        if(!isOk) {
            return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(500).message("fail").build();
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }

    @GetMapping("/testHtml")
    public ResponseVO testHtml(@RequestParam("email") String email) {
        log.info("to email: {}", email);
        boolean isOk = emailUtil.sendHtmlTestEmail(email);
        if(!isOk) {
            return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(500).message("fail").build();
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }

    @GetMapping("/testAnnex")
    public ResponseVO testAnnex(@RequestParam("email") String email) {
        log.info("to email: {}", email);
        boolean isOk = emailUtil.sendAnnexTestEmail(email);
        if(!isOk) {
            return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(500).message("fail").build();
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }

    @GetMapping("/testStatic")
    public ResponseVO testStatic(@RequestParam("email") String email) {
        log.info("to email: {}", email);
        boolean isOk = emailUtil.sendStaticTestEmail(email);
        if(!isOk) {
            return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(500).message("fail").build();
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }

    @GetMapping("/testThymeleaf")
    public ResponseVO testThymeleaf(@RequestParam("email") String email) {
        log.info("to email: {}", email);
        boolean isOk = emailUtil.sendThymeleafTestEmail(email);
        if(!isOk) {
            return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(500).message("fail").build();
        }
        return ResponseVO.builder().timestamp(Instant.now().toEpochMilli()).code(200).message("success").build();
    }
}
