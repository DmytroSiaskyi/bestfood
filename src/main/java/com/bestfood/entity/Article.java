package com.bestfood.entity;

import com.bestfood.dto.ArticleDto;
import com.bestfood.dto.ShortArticleDto;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "articles")
@NamedQueries({
        @NamedQuery(name = "findListArticleByRole", query = "SELECT a FROM Article a where a.roleState >= :roleState and a.enable = true ORDER BY a.orderId"),
        @NamedQuery(name = "findListByCat", query = "SELECT a FROM Article a where a.roleState >= :roleState and a.enable = true and a.category.id =:catId ORDER BY a.orderId ")
})
public class Article implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "orderId")
    private Integer orderId;

    @Column(name = "title", length = 124, nullable = false)
    private String title;

    @Column(name = "name", length = 124, nullable = false)
    private String name;

    @Column(name = "preview", length = 256, nullable = false)
    private String preview;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created")
    private Date created;

    @Column(name = "author", length = 64, nullable = false)
    private String author;

    @Column(name = "content", length = 8192, nullable = false)
    private String content;

    @Column(name = "enamble")
    private Boolean enable;

    @Column(nullable=false)
    private Integer roleState = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
    }

    public ShortArticleDto toshortArticleDto(){
        ShortArticleDto articleDto = new ShortArticleDto();
        articleDto.setId(this.id);
        articleDto.setCreated(this.created);
        articleDto.setPreviewText(this.getPreview());
        articleDto.setTitle(this.getTitle());
        articleDto.setAuthor(this.author);
        articleDto.setEnable(this.enable);
        articleDto.setOrderId(this.orderId);
        articleDto.setAccess(this.roleState);
        articleDto.setName(this.name);
        return articleDto;
    }
    public ArticleDto toArticleDto(){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        ArticleDto articleDto = new ArticleDto();
        articleDto.setAuthor(this.getAuthor());
        articleDto.setContent(this.getContent());
        articleDto.setTitle(this.getTitle());
        articleDto.setCreated(format.format(this.created));
        articleDto.setName(this.getName());
        return articleDto;
    }
}
