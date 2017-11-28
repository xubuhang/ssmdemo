package com.truehang.controller;

import javax.servlet.http.HttpServletRequest;

import com.truehang.model.User;
import com.truehang.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.truehang.utils.SendMailUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }

    @RequestMapping("/sendmail.do")
    public void sendMail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String mail = request.getParameter("mail");
        try {
            SendMailUtils.sendMail(mail,"bh","惊喜有没有","这不仅仅是一个传说");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"stat\":\"fail\"}");
            response.getWriter().close();
            return;
        }

        response.getWriter().write("{\"stat\":\"success\"}");
        response.getWriter().close();
    }

}