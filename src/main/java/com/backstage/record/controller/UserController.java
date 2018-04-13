package com.backstage.record.controller;

import com.backstage.record.domain.RestResult;
import com.backstage.record.domain.User;
import com.backstage.record.service.UserService;
import com.backstage.record.service.impl.UserServiceImpl;
import com.backstage.record.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 用户控制层
 * . @RestController 该类下所有返回值默认以json格式进行返回
 * . @RequestMapping 匹配url地址 /user
 * .
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;
    private ResultGenerator resultGenerator;

    @Autowired //自动装配
    public UserController(UserService userService, ResultGenerator resultGenerator) {
        this.userService = userService;
        this.resultGenerator = resultGenerator;
    }

    /**
     * 匹配 /user/register 地址
     *
     * bindingResult是对异常信息的包装，不过这里我们不用，而是交由异常处理器进行处理
     * @return 注册成功会将注册信息返回（！因为是demo所以没有考虑安全性）
     */
    @RequestMapping("/register")
    public RestResult register(User user, BindingResult bindingResult) {
        return resultGenerator.getSuccessResult("用户注册成功", userService.addUser(user));
    }

/*    *//**
     * 匹配 /user/login 地址 ,限定POST方法
     * 。@NotNull 在字段前添加注解代表验证该字段，如果为空则报异常
     * @return 登陆成功则返回相关信息，否则返回错误提示
     *//*
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RestResult login(@NotNull(message = "用户名不能为空") String name, @NotNull(message = "密码不能为空") String password, HttpSession session) {
        SysUser user = userService.checkLogin(name, password);
        if(user != null) {
            //储存到session中
            session.setAttribute("user",user);
            return generator.getSuccessResult("登陆成功",user);
        }
        return generator.getFailResult("用户名/密码错误");
    }*/

    @RequestMapping("/")
    public String index() {
        return "hahahahahhahahahaaaa";
    }

    @RequestMapping("/getall")
    public List<User> All() {
        return userService.getUserAll();
    }

    /**
     * 为参数验证添加异常处理器
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResult handleConstraintViolationException(ConstraintViolationException cve) {
        //这里简化处理了，cve.getConstraintViolations 会得到所有错误信息的迭代，可以酌情处理
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return resultGenerator.getFailResult(errorMessage);
    }

    /**
     * 主键/唯一约束违反异常
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public RestResult handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        //如果注册两个相同的用户名到报这个异常
        return resultGenerator.getFailResult("违反主键/唯一约束条件");
    }

}
