package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import com.bestfood.dto.PrivateMessageDto;
import com.bestfood.dto.StatusDto;
import com.bestfood.entity.PrivateMessage;
import com.bestfood.services.PrivateMessageService;
import com.bestfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/private/")
public class PrivateController extends BaseController{

    @Autowired
    private UserService userService;
    @Autowired
    private PrivateMessageService privateMessageService;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model, LoggedInUser user) {
        List<PrivateMessageDto> messagesListIn = privateMessageService.getDtoListIn(user.getCurrentUser().getUsername());
        List<PrivateMessageDto> messagesListOut = privateMessageService.getDtoListOut(user.getCurrentUser().getUsername());
        model.addAttribute("messagesListIn", messagesListIn);
        model.addAttribute("messagesListOut", messagesListOut);
        return "private/messages";
    }
    @RequestMapping(value = {"write"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusDto write(@RequestBody PrivateMessageDto messageDto, HttpServletRequest request,
                       HttpServletResponse response,
                       Model model, LoggedInUser user) {
        PrivateMessage message = new PrivateMessage();
        message.setAddressee(userService.findByLogin(messageDto.getAdressee()));
        message.setText(messageDto.getText());
        message.setCreated(new Date());
        message.setRead(false);
        message.setAuthor(userService.findByLogin(user.getCurrentUser().getUsername()));
        StatusDto statusDto = new StatusDto("OK");
        String error = message.validate();
        if(message.getAddressee()==null){
            error = "Користувач з даним логіном не знайдено!";
        }
        if(error!=null) {
            statusDto.setMessage(error);
            statusDto.setStatus("ERROR");
        }else{
            message = privateMessageService.update(message);
        }
        statusDto.setData(messageDto);
        return statusDto;
    }
}
