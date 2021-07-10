package com.rest.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {
    public void userSession(Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");
        String uname = (String) session.getAttribute("uname");
        String uphone = (String) session.getAttribute("uphone");
        String uemail = (String) session.getAttribute("uemail");
        String uaddress = (String) session.getAttribute("uaddress");
        String ugender = (String) session.getAttribute("ugender");

        model.addAttribute("home", "/");

        if (id != null) {

            model.addAttribute("id", id);
            model.addAttribute("uname", uname);
            model.addAttribute("uphone", uphone);
            model.addAttribute("uaddress", uaddress);
            model.addAttribute("uemail", uemail);
            model.addAttribute("ugender", ugender);

            model.addAttribute("status", "Logout");
            model.addAttribute("url", "/logout");
            model.addAttribute("mypage", "/mypage");
        } else {

            model.addAttribute("status", "Login");
            model.addAttribute("url", "/login");
            model.addAttribute("mypage", "/login");
        }
    }
    @RequestMapping("/")
    public ModelAndView in_page(ModelAndView mav, Model model, HttpSession session) {
        userSession(model, session);
        mav.setViewName("index"); //이동할 페이지 이름 (product_list.jsp 파일로 이동)
        return mav; //페이지 이동
    }

    @GetMapping("/index")
    public String indexpage(Model model, HttpSession session) {

        userSession(model, session);
        return "index";
    }

    @GetMapping("/login")
    public String loginpage(Model model, HttpSession session) {
        userSession(model, session);
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/register")
    public String registerpage(Model model, HttpSession session) {
        userSession(model, session);
        return "register";
    }

    @GetMapping("/mypage")
    public String mypagepage(Model model, HttpSession session) {

        userSession(model, session);
        return "mypage";
    }

    @GetMapping("/mp_delete")
    public String mp_deletepage(Model model, HttpSession session) {

        userSession(model, session);
        return "mp_delete";
    }
}
