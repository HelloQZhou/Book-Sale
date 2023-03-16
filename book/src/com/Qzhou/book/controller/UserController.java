package com.Qzhou.book.controller;

import com.Qzhou.book.pojo.Cart;
import com.Qzhou.book.pojo.User;
import com.Qzhou.book.service.CartItemService;
import com.Qzhou.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;
    private CartItemService cartItemService;

        public String login(String uname, String pwd, HttpSession session){

        User user = userService.login(uname, pwd);

        if(user!=null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser",user);;
            return "redirect:book.do";
        }

        return "user/login";
    }

    public String regist(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse response) throws IOException {
        Object kaptchaCodeObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaCodeObj==null||!kaptchaCodeObj.equals(verifyCode)){

            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script language='javascript'>alert('验证码不正确!');</script>");
            return "user/regist";
        }
        else if(verifyCode.equals(kaptchaCodeObj)){
            userService.regist(new User(uname,pwd,0,email));
            return "user/login";
        }

        return "user/login";
    }
}
