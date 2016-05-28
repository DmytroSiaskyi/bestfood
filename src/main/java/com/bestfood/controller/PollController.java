package com.bestfood.controller;

import com.bestfood.dto.StatusDto;
import com.bestfood.entity.Answer;
import com.bestfood.entity.Poll;
import com.bestfood.services.AnswerService;
import com.bestfood.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PollController extends BaseController{
    @Autowired
    private PollService pollService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = {"/poll"}, method = RequestMethod.GET)
     public String home(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        List<Poll> pollList = pollService.list();
        model.addAttribute("pollList", pollList);
        return "poll";
    }

    @RequestMapping(value = {"/poll"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusDto addAnswer(HttpServletRequest request,
                            @RequestParam(required = false) Long id,
                       HttpServletResponse response,
                       Model model) {
        Answer answer = answerService.find(id);
        int count = answer.getSelected();
        answer.setSelected(count + 1);
        answerService.update(answer);
        return new StatusDto("OK");
    }
}
