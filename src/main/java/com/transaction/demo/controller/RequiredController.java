package com.transaction.demo.controller;

import com.transaction.demo.service.required.ClassA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 总结：required的事务控制范围为带事务方法(或者类)的上下文。
 * 例如：  如果A有事务，B没有事务并且A调用B那么AB都是事务
 *        如果A没有事务，B有事务并且A调用B那么A没有事务B有事务
 */
@RestController
public class RequiredController {

    @Autowired
    private ClassA classA;


    /**
     * @Description TODO
     * @Param: []
     * @Return: void
     * @Author: crelle
     * @Date: 2020/4/27 20:46
     *前提条件：类B.method1()会抛运行时异常，并且异常没有处理。
     *测试结果1：类A.method1()没有事务，类B.method1()没有事务。A,B都不回滚
     *测试结果2：类A.method1()有事务，类B.method1()没有事务。A,B都回滚
     *测试结果3：类A.method1()没有事务，类B.method1()有事务。A不回滚，B回滚
     */
    @GetMapping("/method1")
    public void method1() {
        classA.method1();
    }


    /*
     * @Description TODO
     * @Param: []
     * @Return: void
     * @Author: crelle
     * @Date: 2020/4/27 20:47
     * 前提条件：类B.method1()会抛运行时异常并捕获处理
     * 测试结果1：类A.method2()没有事务，类B.method1()没有事务。A,B都不回滚
     * 测试结果2：类A.method2()有事务，类B.method1()没有事务。A，B都不回滚
     * 测试结果3：类A.method2()没有事务，类B.method1()有事务。A不回滚，B回滚(虽然A捕获了B的异常，但是A没有事务控制B有事务控制，
     * B抛出异常就是代理类捕获到所以就回滚了事务
     */
    @GetMapping("/method2")
    public void method2() {
        classA.method2();
    }


     /*
      * @Description TODO
      * @Param: []
      * @Return: void
      * @Author: crelle
      * @Date: 2020/4/27 20:49
      * 前提条件：类B.method1()会抛运行时异常，并且异常在controller中处理
      * 测试结果1：类A.method1()没有事务，类B.method1()没有事务。A,B都不回滚
      * 测试结果2：类A.method1()有事务，类B.method1()没有事务。A,B都回滚
      * 测试结果3：类A.method1()没有事务，类B.method1()有事务。A不回滚，B回滚
      */
    @GetMapping("/method3")
    public void method3() {
        try {
            classA.method1();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("<========================="+e.getMessage()+"=========================================");
        }
    }

}
