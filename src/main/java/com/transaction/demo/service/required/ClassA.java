package com.transaction.demo.service.required;

import com.transaction.demo.model.User;
import com.transaction.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: 测试事务的传播性
 *
 * 1. Propagation.REQUIRED
 * @date: 2019-06-24 12:42
 * @author: 十一
 */
@Service
public class ClassA {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassB classB;


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void method1() {


        User user = new User();
        user.setName("十一");
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        classB.method1();
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public void method2() {
        User user = new User();
        user.setName("十一");
        user = userRepository.save(user);
        System.out.println("<====================== user: " + user.toString());
        try {
            classB.method1();
        }catch (Exception e) {

            System.out.println("<====================== 捕获异常： " + e.getCause());
            System.out.println("<====================== 捕获异常： " + e.getMessage());
        }
    }

}
