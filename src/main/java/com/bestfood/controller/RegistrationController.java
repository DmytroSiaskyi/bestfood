package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import com.bestfood.entity.User;
import com.bestfood.entity.UserRole;
import com.bestfood.services.UserRoleService;
import com.bestfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RegistrationController extends BaseController{
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        return "registration";
    }
    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request,
                               HttpServletResponse response,
                               Model model, LoggedInUser user) {
        String error = "";

        User newUser = new User();
        newUser.setLogin(request.getParameter("login"));
        newUser.setFirstName(request.getParameter("firstname"));
        newUser.setLastName(request.getParameter("surname"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEnabled(true);
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(userRoleService.findByName("ROLE_USER"));
        newUser.setRoles(userRoleList);
        newUser.setCreated(new Date());

        error = newUser.validate();
        if(userService.findByLogin(newUser.getLogin())!=null){
            error = "Користувач з таким логіном вже зареєстрований!";
        }
        if(error.isEmpty()){
            userService.add(newUser);
            return "index";
        }
        model.addAttribute("error", error);
        model.addAttribute("user", newUser);
        return "registration";
    }
}

