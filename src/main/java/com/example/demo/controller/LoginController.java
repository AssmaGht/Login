package com.example.demo.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Login;
import com.example.demo.service.LoginService;



@Controller
public class LoginController {
    
    @Autowired
    private LoginService userService;

                                   
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
           
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("user") Login user, Model model) {
        Login oauthUser = userService.login(user.getUsername(), user.getPassword());
        System.out.println(oauthUser);  // Check if the user is authenticated properly

        if (Objects.nonNull(oauthUser)) {
            // After successful login, you might want to store the user in the session
            // For example: session.setAttribute("loggedInUser", oauthUser);
            return "redirect:/";  // Redirect to homepage or dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");  // Add error message to the model
            return "login";  // Return to the login page
        }
    }

    
    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
        
      
        return "redirect:/login";
    }

}