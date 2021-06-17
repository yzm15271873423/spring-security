package com.yzm.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yzm
 * @Date: 2021/6/17 - 06 - 17 - 17:11
 */
public interface MyService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
