package com.bestfood.entity.rss;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "articles")
public class RssArticle {
    List<XmlArticle> list;

    public List<XmlArticle> getList() {
        return list;
    }
    public void setList(List<XmlArticle> list) {
        this.list = list;
    }
}
