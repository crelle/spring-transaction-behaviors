package com.transaction.demo.controller;

import com.transaction.demo.service.requiresnew.RequiresNewClassA;
import com.transaction.demo.service.supports.SupportClassA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * crelle
 * REQUIRES_NEW: Create a new transaction, and suspend the current transaction if one exists.
 */
@RestController
@RequestMapping("/rn")
public class RequiresNewController {

    @Autowired
    private RequiresNewClassA requiresNewClassA;


    /**
     * 前提条件：A抛出运行时异常并且不处理
     * 测试情况1：类A.method1()有事务，类B.method1()有事务。A回滚，B不回滚。
     * 测试情况2：类A.method1()有事务，类B.method1()没有事务。A,B都回滚。
     * 测试情况3：类A.method1()没有事务，类B.method1()有事务。A，B都不回滚。
     */
    @GetMapping("/method1")
    public void method1() {
        requiresNewClassA.method1();
    }

    /**
     * 前提条件：B抛出运行时异常并且不处理
     * 测试情况1：类A.method2()有事务，类B.method2()有事务。A，B都回滚
     * 测试情况2：类A.method2()有事务，类B.method2()没有事务。A,B都回滚。
     * 测试情况3：类A.method2()没有事务，类B.method2()有事务。A不回滚，B回滚。
     */
    @GetMapping("/method2")
    public void method2() {
        requiresNewClassA.method2();
    }

    /**
     * 前提条件：B抛出运行时异常并且A捕获处理异常
     * 测试情况1：类A.method3()有事务，类B.method3()有事务。A不回滚，B回滚
     * 测试情况2：类A.method3()有事务，类B.method3()没有事务。A,B都不回滚。
     * 测试情况3：类A.method3()没有事务，类B.method3()有事务。A不回滚，B回滚。
     */
    @GetMapping("/method3")
    public void method3() {
        requiresNewClassA.method3();
    }




}
