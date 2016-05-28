package com.bestfood.controller;

import com.bestfood.components.LoggedInUser;
import com.bestfood.dto.ArticleDto;
import com.bestfood.dto.CommentDto;
import com.bestfood.dto.ShortArticleDto;
import com.bestfood.dto.StatusDto;
import com.bestfood.entity.Article;
import com.bestfood.entity.Category;
import com.bestfood.entity.Comment;
import com.bestfood.entity.rss.XmlArticle;
import com.bestfood.services.ArticleService;
import com.bestfood.services.CategoryService;
import com.bestfood.services.CommentService;
import com.bestfood.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ArticlesController extends BaseController{
    @Autowired
    ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @Value("#{properties['host']}")
    private String host;

    @RequestMapping(value = {"/articles/list"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model,
                       LoggedInUser liu) {
//        List<ShortArticleDto> articleList = new ArrayList<>();
//        if (liu.isAdmin()){
//            articleList = articleService.getShortArticleList(0);
//        } else if (liu.isUser()) {
//            articleList = articleService.getShortArticleList(1);
//        } else {
//            articleList = articleService.getShortArticleList(2);
//        }
//
//        model.addAttribute("previewList", articleList);
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoriesList", categoryList);
        return "articles/list";
    }

    @RequestMapping(value = {"/articles/view/*"}, method = RequestMethod.GET)
    public String article(HttpServletRequest request,
                          HttpServletResponse response,
                          Model model,
                          LoggedInUser liu) {
        String url = request.getRequestURI();
        String[] urlSplit = url.split("/");
        String name = urlSplit[4];
        String[] nameSplit = name.split("\\.");
        name = nameSplit[0];
        Article article = articleService.findByName(name);
        if( article!=  null)
            model.addAttribute("article", article);
        List<CommentDto> commentDtos = commentService.getCommentDtoList(article.getId());
        model.addAttribute("comments", commentDtos);
        return "articles/article";
    }
    @RequestMapping(value = {"/articles/view/*.json"}, method = RequestMethod.GET)
    @ResponseBody
    public Article articleJSON(HttpServletRequest request,
                               HttpServletResponse response){

        String url = request.getRequestURI();
        String[] urlSplit = url.split("/");
        String name = urlSplit[4];
        String[] nameSplit = name.split("\\.");
        name = nameSplit[0];
        Article article = articleService.findByName(name);
        return article;
    }
    @RequestMapping(value = {"/articles/list/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    public List<ShortArticleDto> shortList(@PathVariable Long id, LoggedInUser liu){
        List<ShortArticleDto> articleList = new ArrayList<>();
        if (liu.isAdmin()){
            articleList = articleService.getShortArticleListCat(0, id);
        } else if (liu.isUser()) {
            articleList = articleService.getShortArticleListCat(1, id);
        } else {
            articleList = articleService.getShortArticleListCat(2, id);
        }
        return articleList;
    }
    @RequestMapping(value = {"/articles/view/*"}, method = RequestMethod.POST)
    @ResponseBody
    public ArticleDto articleJS(HttpServletRequest request,
                               HttpServletResponse response){
        String url = request.getRequestURI();
        String[] urlSplit = url.split("/");
        String name = urlSplit[4];
        String[] nameSplit = name.split("\\.");
        name = nameSplit[0];
        Article article = articleService.findByName(name);
        return article.toArticleDto();
    }
    @RequestMapping(value = {"/articles/view/*.xml"}, method = RequestMethod.GET)
    @ResponseBody
    public XmlArticle articleXml(HttpServletRequest request,
                                 HttpServletResponse response,
                                 LoggedInUser liu) throws UnsupportedEncodingException {
        String url = request.getRequestURI();
        String[] urlSplit = url.split("/");
        String name = urlSplit[4];
        String[] nameSplit = name.split("\\.");
        name = nameSplit[0];
        Article article = articleService.findByName(name);
        if(article != null) {
            XmlArticle xmlArticle = new XmlArticle();
            xmlArticle.setAuthor(article.getAuthor());
            xmlArticle.setTitle(article.getTitle());
            xmlArticle.setPreview(article.getPreview());
            xmlArticle.setName(article.getName());
            xmlArticle.setUrl(host + "articles/view/" + article.getName());
            xmlArticle.setXmlUrl(host + "articles/view/" + article.getName()+".xml");
            return xmlArticle;
        }
        return null;
    }
    @RequestMapping(value = {"/articles/{name}/comment"}, method = RequestMethod.POST)
    public String commentArticle(HttpServletRequest request,@PathVariable String name, Model model, LoggedInUser liu){
        Comment comment = new Comment();
        comment.setText((String)request.getParameter("text"));
        comment.setCreated(new Date());
        comment.setIP(request.getRemoteAddr());
        comment.setArticle(articleService.findByName(name));
        comment.setAuthor(userService.findByLogin(liu.getCurrentUser().getUsername()));
        String error = null;
        if(comment.getText().length()<6)
        {
            error="Текст коментаря має бути більше 6 символів";
            model.addAttribute("error", error);
            model.addAttribute("commentText", comment.getText());
        }else {
            commentService.update(comment);
        }
        return "redirect:/articles/view/"+name;
    }
    @RequestMapping(value = {"/articles/{name}/comment/delete"}, method = RequestMethod.GET)
    public String deleteComment(HttpServletRequest request,@PathVariable String name, @RequestParam(required = false) Long id){
        commentService.remove(id);
        return "redirect:/articles/view/"+name;
    }
    @RequestMapping(value = {"/articles/{name}/comment/dynamicDelete"}, method = RequestMethod.POST)
    @ResponseBody
    public StatusDto dynamicDeleteComment(@RequestParam(required = false) Long id, HttpServletRequest request) {
        StatusDto statusDto = new StatusDto("OK");
        try{
            commentService.remove(id);
        }catch (Exception e){
            statusDto.setMessage("ERROR");
        }
        return statusDto;
    }
    @RequestMapping(value = {"/articles/offer"}, method = RequestMethod.GET)
    public String offerArticle(HttpServletRequest request){
        return "articles/new";
    }
    @RequestMapping(value = {"/articles/offer"}, method = RequestMethod.POST)
    public String addNewArticle(HttpServletRequest request, Model model, LoggedInUser liu){
        Article article = new Article();
        article.setEnable(false);
        article.setCreated(new Date());
        article.setAuthor(liu.getCurrentUser().getFirstName() + " " + liu.getCurrentUser().getLastName());
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("text"));
        article.setName("article" + article.getId());
        article.setOrderId(100);
        article.setPreview("emptyPreview");
        article.setRoleState(2);
        article.setCategory(categoryService.find(6L));
        if(request.getParameter("submit")==null) {
            articleService.update(article);
            return "redirect:/";
        }else{
            model.addAttribute("article",article);
            return "articles/preview";
        }
    }
}
