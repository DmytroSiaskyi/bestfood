package com.bestfood.controller;

import com.bestfood.entity.Slide;
import com.bestfood.services.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AboutController extends BaseController{
    @Autowired
    private SlideService slideService;
    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        List<Slide> slideList = slideService.listOrdered();
        model.addAttribute("slideList", slideList);
        model.addAttribute("frequency", slideList.get(0).getFrequency());
        return "about";
    }
}
