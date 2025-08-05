package com.lilac.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("123456");
        System.out.println(encodedPassword); 
        // 每次运行结果都不同，但都与"123456"匹配，例如：$2a$10$xxxxxxxxxxxxxxxxxxxxxx
    }
}