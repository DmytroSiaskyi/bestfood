package com.bestfood.controller;

import com.bestfood.dto.ShortArticleDto;
import com.bestfood.entity.News;
import com.bestfood.entity.rss.RssArticle;
import com.bestfood.entity.rss.XmlArticle;
import com.bestfood.services.ArticleService;
import com.bestfood.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController{

    @Autowired
    NewsService newsService;
    @Autowired
    ArticleService articleService;
    @Value("#{properties['host']}")
    private String host;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        List<News> newsList = newsService.list();
        model.addAttribute("newsList", newsList);
        return "index";
    }
    @RequestMapping(value = {"/rss.xml"}, method = RequestMethod.GET)
    @ResponseBody
    public RssArticle articles(HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {
        RssArticle rssArticle = new RssArticle();
        List<ShortArticleDto> listDto = articleService.getShortArticleList(0);
        List<XmlArticle> xmlArticleList = new ArrayList<>();
        for(ShortArticleDto a: listDto){
            XmlArticle temp = new XmlArticle();
            temp.setAuthor(a.getAuthor());
            temp.setName(a.getName());
            temp.setTitle(a.getTitle());
            temp.setPreview(a.getPreviewText());
            temp.setUrl(host + "articles/view/" + a.getName());
            temp.setXmlUrl(host + "articles/view/" + a.getName()+".xml");
            xmlArticleList.add(temp);
        }
        rssArticle.setList(xmlArticleList);
        return rssArticle;
    }
}
