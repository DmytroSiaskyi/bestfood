package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import com.bestfood.dao.MessageDao;
import com.bestfood.dto.MessageDto;
import com.bestfood.dto.ShortArticleDto;
import com.bestfood.dto.StatusDto;
import com.bestfood.entity.*;
import com.bestfood.services.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminController extends BaseController{

    @Autowired
    private ArticleService articleService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SlideService slideService;
    @Autowired
    private PollService pollService;
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = {"index"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        return "admin/index";
    }
    @RequestMapping(value = {"management/messages"}, method = RequestMethod.GET)
    public String messages(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        List<Message> messagesList = messageService.list();
        model.addAttribute("messageList",messagesList);
        return "admin/management/messages";
    }
    @RequestMapping(value = {"management/deleteMessage"}, method = RequestMethod.GET)
    public String deleteMessage(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model, @RequestParam(required = false) Integer id) {
        messageService.remove(new Long(id));
        return "redirect:/admin/management/messages";
    }
    @RequestMapping(value = {"management/answer"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusDto answerMessage(@RequestBody MessageDto messageDto, HttpServletRequest request,LoggedInUser liu) {
        Long id = messageDto.getId();
        Message message = messageService.find(id);
        message.setAnswer(messageDto.getComment());
        messageService.update(message);
        StatusDto statusDto = new StatusDto("OK");
        return statusDto;
    }
    @RequestMapping(value = {"management/pages"}, method = RequestMethod.GET)
    public String pages(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {
        List<ShortArticleDto> articleList = new ArrayList<>();
        articleList = articleService.getAllShortArticles();
        model.addAttribute("articleData", articleList);
        return "admin/management/pages";
    }
    @RequestMapping(value = {"management/category"}, method = RequestMethod.GET)
    public String category(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "admin/management/category";
    }
    @RequestMapping(value = {"management/edit"}, method = RequestMethod.GET)
    public String editArticle(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              @RequestParam(required = false) Integer page) {

        Article article = articleService.find(new Long(page));
        model.addAttribute("article", article);
        return "admin/management/edit";
    }
    @RequestMapping(value = {"management/edit"}, method = RequestMethod.POST)
    public String editArticle(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        String idd = request.getParameter("id");
        Long id = Long.parseLong(request.getParameter("id"));
        Article article = articleService.find(id);
        article.setTitle((String) request.getParameter("title"));
        article.setName((String) request.getParameter("name"));
        article.setContent((String) request.getParameter("text"));
        article.setAuthor((String) request.getParameter("author"));
        article.setPreview((String) request.getParameter("preview"));
        article.setOrderId(Integer.parseInt(request.getParameter("orderId")));
        String submit = request.getParameter("submit");
        if(submit==null) {
            articleService.update(article);
            return "redirect:/admin/management/pages";
        }
        Boolean flag = submit.equals("Попередній огляд");
        model.addAttribute("article",article);
        return "articles/preview";

    }
    @RequestMapping(value = {"management/delete"}, method = RequestMethod.GET)
    public String deleteArticle(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model,
                              @RequestParam(required = false) Integer page) {

        Long id = Long.parseLong(request.getParameter("id"));
        articleService.remove(id);
        return "redirect:/admin/management/pages";
    }
    @RequestMapping(value = {"management/access"}, method = RequestMethod.POST)
    public String accessArticle(HttpServletRequest request,
                                HttpServletResponse response,
                                Model model){

        String role = request.getParameter("role");
        String enableArt = request.getParameter("enable");
        Boolean flag = false;
        if(enableArt != null){
            flag = true;
        }
        Long id;
        String[] arr = role.split("%");
        id = new Long(Integer.parseInt(arr[1]));
        Article article = articleService.find(id);
        article.setEnable(flag);
        article.setRoleState(Integer.parseInt(arr[0]));
        articleService.update(article);
        return "redirect:/admin/management/pages";
    }
    @RequestMapping(value = {"management/category/rename"}, method = RequestMethod.POST)
    public String renameCategory(HttpServletRequest request,
                                 HttpServletResponse response) {
        Long id = Long.parseLong((String)request.getParameter("id"));
        String newName = (String)request.getParameter("text");
        Category category = categoryService.find(id);
        category.setName(newName);
        categoryService.update(category);
        return "redirect:/admin/management/category";
    }
    @RequestMapping(value = {"management/category/delete"}, method = RequestMethod.GET)
    public String deleteCategory(@RequestParam(required = false) Long id) {
        Category newCat = categoryService.findByName("Не розподілені статті");
        articleService.updateArticlesCategories(id, newCat);
        categoryService.remove(id);
        return "redirect:/admin/management/category";
    }
    @RequestMapping(value = {"management/category/add"}, method = RequestMethod.POST)
    public String addCategory(HttpServletRequest request) {
        String newName = (String)request.getParameter("name");
        Category category = new Category();
        category.setName(newName);
        categoryService.add(category);
        return "redirect:/admin/management/category";
    }
    @RequestMapping(value = {"management/category/manage"}, method = RequestMethod.GET)
    public String catManager(HttpServletRequest request,
                                HttpServletResponse response,
                                Model model,@RequestParam(required = false) Long id){
        List<ShortArticleDto> articleList = new ArrayList<>();
        articleList = articleService.getShortArticleListCat(0,id);
        model.addAttribute("articleData", articleList);
        Category category = categoryService.find(id);
        model.addAttribute("catName", category.getName());
        return "admin/management/categoryManage";
    }
    @RequestMapping(value = {"management/category/deleteArticle"}, method = RequestMethod.GET)
    public String deleteArtFromCat(HttpServletRequest request,
                             HttpServletResponse response,
                             Model model,@RequestParam(required = false) Long id){
        Category newCat = categoryService.find(6L);
        Article article = articleService.find(id);
        article.setCategory(newCat);
        articleService.update(article);
        return "redirect:/admin/management/category";
    }
    @RequestMapping(value = {"management/category/addArticles"}, method = RequestMethod.GET)
    public String showPosibleArticles(HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model,@RequestParam(required = false) Long id){
        Category category = categoryService.find(id);
        List<ShortArticleDto> articleList = new ArrayList<>();
        articleList = articleService.getShortArticleListCat(0,6L);
        model.addAttribute("articleData", articleList);
        model.addAttribute("catName", category.getName());
        model.addAttribute("category", category.getId());
        return "admin/management/addArticle";
    }
    @RequestMapping(value = {"management/category/addArticle"}, method = RequestMethod.GET)
    public String addArticle(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Model model,@RequestParam(required = false) Long articleId, @RequestParam(required = false) Long categoryId){
        Article article = articleService.find(articleId);
        Category category = categoryService.find(categoryId);
        article.setCategory(category);
        articleService.update(article);
        return "redirect:/admin/management/category";
    }
    @RequestMapping(value = {"management/slides"}, method = RequestMethod.GET)
     public String slides(HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
        List<Slide> slideList = slideService.list();
        model.addAttribute("slidesList", slideList);
        return "admin/management/slide";
    }
    @RequestMapping(value = {"management/slides/delete"}, method = RequestMethod.GET)
    public String slideDelete(HttpServletRequest request,@RequestParam(required = false) Long id,
                         HttpServletResponse response,
                         Model model) {
        slideService.remove(id);
        return "redirect:/admin/management/slides";
    }
    @RequestMapping(value = {"management/slides/add"}, method = RequestMethod.POST)
    public String addSlide(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        Slide slide = new Slide();
        slide.setText((String) request.getParameter("text"));
        slide.setLink((String) request.getParameter("link"));
        slide.setImageUrl((String) request.getParameter("image"));
        slide.setOrderId(Integer.parseInt((String)request.getParameter("order")));
        try {
            slide.setFrequency(slideService.list().get(0).getFrequency());
        }catch(Exception e) {
            slide.setFrequency(5000);
        }
        if(slide.getText().length()>6 && slide.getImageUrl().length()>0 & slide.getLink().length()>0){
            slideService.add(slide);
            return "redirect:/admin/management/slides";
        }else{
            model.addAttribute("slidesList", slideService.list());
            model.addAttribute("error", "ERROR");
            model.addAttribute("newSlide", slide);
            return "admin/management/slide";
        }

    }
    @RequestMapping(value = {"management/slides/setfrequency"}, method = RequestMethod.POST)
    public String setFreq(HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        Integer freq = Integer.parseInt((String)request.getParameter("freq"));
        slideService.setFrequency(freq);
        return "redirect:/admin/management/slides";
    }
    @RequestMapping(value = {"management/poll"}, method = RequestMethod.GET)
    public String adminPoll(HttpServletRequest request,
                          HttpServletResponse response,
                          Model model) {
        List<Poll> pollList = pollService.list();
        model.addAttribute("pollList", pollList);
        return "admin/management/poll";
    }
    @RequestMapping(value = {"management/poll/delete"}, method = RequestMethod.GET)
    public String pollDelete(HttpServletRequest request,@RequestParam(required = false) Long id,
                              HttpServletResponse response,
                              Model model) {
        pollService.remove(id);
        return "redirect:/admin/management/poll";
    }
    @RequestMapping(value = {"management/poll/add"}, method = RequestMethod.POST)
    public String addPoll(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model) {
        Poll poll = new Poll();
        poll.setQuestion((String)request.getParameter("question"));
        pollService.add(poll);
        return "redirect:/admin/management/poll";
    }
    @RequestMapping(value = {"management/poll/dynamic/add"}, method = RequestMethod.POST)
    @ResponseBody
    public Poll addDynamicPoll(@RequestBody String question, HttpServletRequest request,LoggedInUser liu) {
        Poll poll = new Poll();
        poll.setQuestion(question);
        Poll result = pollService.update(poll);
        return result;
    }
    @RequestMapping(value = {"management/poll/edit"}, method = RequestMethod.GET)
    public String editPoll(HttpServletRequest request, @RequestParam(required = false) Long id,
                          Model model) {
        Poll poll = pollService.find(id);
        model.addAttribute("poll", poll);
        return "admin/management/questions";
    }
    @RequestMapping(value = {"management/poll/add/answer"}, method = RequestMethod.POST)
    public String addAnswer(HttpServletRequest request,
                            @RequestParam(required = false) Long id,
                          Model model) {
        Poll poll = pollService.find(id);
        Answer answer = new Answer();
        answer.setText((String)request.getParameter("answer"));
        answer.setSelected(0);
        poll.addAnswerToList(answer);
        pollService.update(poll);
        return "redirect:/admin/management/poll/edit?id=" + id;
    }
    @RequestMapping(value = {"management/poll/dynamic/answer/add"}, method = RequestMethod.POST)
    @ResponseBody
    public Answer addDynamicAnswer(@RequestParam(required = false) Long id,@RequestBody String question, HttpServletRequest request,LoggedInUser liu) {
        Poll poll = pollService.find(id);
        Answer answer = new Answer();
        answer.setText(question);
        answer.setSelected(0);
        Answer newAnswer = answerService.update(answer);
        poll.addAnswerToList(newAnswer);
        pollService.update(poll);
        return newAnswer;
    }
    @RequestMapping(value = {"management/poll/delete/question"}, method = RequestMethod.GET)
    public String answerDelete(HttpServletRequest request,@RequestParam(required = false) Long id,@RequestParam(required = false) Long poll,
                             HttpServletResponse response,
                             Model model) {
        Poll currentPoll = pollService.find(poll);
        Answer answer = answerService.find(id);
        currentPoll.getAnswerList().remove(answer);
        pollService.update(currentPoll);
        answerService.remove(id);
        return "redirect:/admin/management/poll/edit?id=" + poll;
    }
}
