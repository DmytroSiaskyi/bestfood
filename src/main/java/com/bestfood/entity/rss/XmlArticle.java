package com.bestfood.entity.rss;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "article")
public class XmlArticle {
    String name;
    String title;
    String preview;
    String url;
    String xmlUrl;
    String author;
    @XmlElement(name = "name")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    @XmlElement(name = "title")
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    @XmlElement(name = "preview")
    public String getPreview() {return preview;}
    public void setPreview(String preview) {this.preview = preview;}
    @XmlElement(name = "link")
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
    @XmlElement(name = "xmlLink")
    public String getXmlUrl() {return xmlUrl;}
    public void setXmlUrl(String xmlUrl) {this.xmlUrl = xmlUrl;}
    @XmlElement(name = "author")
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
}
