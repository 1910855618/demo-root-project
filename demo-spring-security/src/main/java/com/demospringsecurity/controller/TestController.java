package com.demospringsecurity.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Log4j2
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello spring security!";
    }

    @GetMapping("/index")
    public Object index(Authentication authentication) {
//        return "this is index page!";
        log.info("login user: {}", SecurityContextHolder.getContext().getAuthentication());
        log.info("login user: {}", authentication);
        return authentication;
    }

    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String authenticationAdmin() {
        return "您拥有admin权限，可以查看";
    }

    @GetMapping("/auth/test")
    @PreAuthorize("hasAuthority('test')")
    public String authenticationTest() {
        return "您拥有test权限，可以查看";
    }

    @GetMapping("/auth/secured/test")
    @Secured("test")
    public String authenticationSecuredTest(){
        return "您拥有test权限，可以查看";
    }

    @GetMapping("/auths/secured/test")
    @Secured({"admin", "test"})
    public String authenticationsSecuredTest(){
        return "您拥有test或者admin权限，可以查看";
    }

    @GetMapping("/auth/rolesAllowed/test")
    @RolesAllowed("admin")
    public String authenticationRolesAllowedTest(){
        return "您拥有admin权限，可以查看";
    }
}
