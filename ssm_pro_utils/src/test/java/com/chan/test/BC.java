package com.chan.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class BC {
    @Test
    public void test1() {
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }

    @Test
    public void test2() {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    }
}
