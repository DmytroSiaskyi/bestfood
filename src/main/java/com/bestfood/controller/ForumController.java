package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import com.bestfood.dto.MessageDto;
import com.bestfood.dto.StatusDto;
import com.bestfood.entity.Message;
import com.bestfood.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/forum/")
public class ForumController extends BaseController{
    @Autowired
    MessageService messageService;

    @RequestMapping(value = {"messages"}, method = RequestMethod.GET)
    public String messageList(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              @RequestParam(required = false) Integer page,
                              LoggedInUser liu) {
        List<Message> messagesList;
        int recordsPerPage = 10;
        int pageNumber = 1;
        try {
            pageNumber = page;
        }catch(NullPointerException e){
            pageNumber = 1;
        }
        messagesList = messageService.list((pageNumber - 1) * recordsPerPage + 1, recordsPerPage);
        int noOfRecords = (int) messageService.count();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("messagesList", messagesList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("pagePath", "/WEB-INF/view/messages.jsp");
        if(liu.isUser()){
            String login =  liu.getCurrentUser().getUsername();
            String email =  liu.getCurrentUser().getEmail();
            model.addAttribute("user", login);
            model.addAttribute("email",email);
        }
        return "forum/messages";
    }

    @RequestMapping(value = {"write"}, method = RequestMethod.GET)
    public String writeMessage(HttpServletRequest request,
                               HttpServletResponse response,
                               Model model,
                               LoggedInUser liu) {
        if(liu.isUser()){
            String login =  liu.getCurrentUser().getUsername();
            String email =  liu.getCurrentUser().getEmail();
            model.addAttribute("user", login);
            model.addAttribute("email",email);
        }
        return "forum/write";
    }

    @RequestMapping(value = {"write"}, method = RequestMethod.POST)
    public String postMessage(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              LoggedInUser liu) {
        String errorMessage = null;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String text = request.getParameter("text");
        Message message = new Message();
        message.setLogin(name);
        message.setEmail(email);
        message.setCreated(new Date());
        message.setComment(text);
        message.setIP(request.getRemoteAddr());

        errorMessage = message.validate();

        if(errorMessage == null) {
            model.addAttribute("user", name);
            messageService.add(message);
            return "forum/thanks";
        }
        model.addAttribute("user", name);
        model.addAttribute("email", email);
        model.addAttribute("text", text);
        model.addAttribute("error",errorMessage);
        return "forum/write";

    }
    @RequestMapping(value = {"dynamic/write"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusDto dynamicWriteMessage(@RequestBody MessageDto messageDto, HttpServletRequest request,LoggedInUser liu) {
        Message message = new Message();
        message.setLogin(messageDto.getLogin());
        message.setEmail(messageDto.getEmail());
        message.setComment(messageDto.getComment());
        message.setCreated(new Date());
        message.setIP(request.getRemoteAddr());
        StatusDto statusDto = new StatusDto("OK");
        String error = message.validate();
        if(error!=null) {
            statusDto.setMessage(error);
            statusDto.setStatus("ERROR");
        }else{
            message = messageService.update(message);
        }
        statusDto.setData(message);
        return statusDto;
    }
}
