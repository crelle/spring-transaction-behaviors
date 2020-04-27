package com.transaction.demo.controller;

import com.transaction.demo.service.supports.SupportClassA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * crelle
 * SUPPORTS:Support a current transaction, execute non-transactionally if none exists.
 */
@RestController
@RequestMapping("/support")
public class SupportsController {

    @Autowired
    private SupportClassA supportClassA;


    @GetMapping("/method1")
    public void method1() {
        supportClassA.method1();
    }

    @GetMapping("/method2")
    public void method2() {
        supportClassA.method2();
    }

    @GetMapping("/method3")
    public void method3() {
        supportClassA.method3();
    }



}
