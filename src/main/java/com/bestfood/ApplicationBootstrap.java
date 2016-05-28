package com.bestfood;

import com.bestfood.entity.*;
import com.bestfood.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Dmitry Syaskiy
 */
public class ApplicationBootstrap implements InitializingBean {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Value("#{properties['db.hbm2ddl.auto']}")
    private String db;
    @Value("#{properties['cloud.login']}")
    private String login;
    @Value("#{properties['cloud.pass']}")
    private String pass;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PollService pollService;
    @Autowired
    private AnswerService answerService;

    @Transactional
    public void afterPropertiesSet() throws Exception {
        if (db.contains("create")){
            initUsers();
            initCategories();
            initPoll();
        }
    }
    private void initPoll(){
        Poll poll = new Poll();
        poll.setQuestion("Чи подобається вам наш сервіс?");
        List<Answer>answerList = new ArrayList<>();
        Answer answer = new Answer();
        answer.setSelected(0);
        answer.setText("Так, подобається.");
        answerList.add(answerService.update(answer));
        answer = new Answer();
        answer.setSelected(0);
        answer.setText("Так, але можна удосконалити.");
        answerList.add(answerService.update(answer));
        answer = new Answer();
        answer.setSelected(0);
        answer.setText("Ні,не подобається.");
        answerList.add(answerService.update(answer));
        poll.setAnswerList(answerList);
        pollService.add(poll);
    }
    private void initCategories(){
        Category category = new Category();
        category.setName("Рецепти");
        categoryService.add(category);
        category = new Category();
        category.setName("Мотивуючі статті");
        categoryService.add(category);
        category = new Category();
        category.setName("Не розподілені статті");
        categoryService.add(category);
        category = new Category();
        category.setName("Інформаційні статті");
        categoryService.add(category);

        Article article = new Article();
        article.setCreated(new Date());
        article.setOrderId(1);
        article.setPreview("preview1");
        article.setName("articleTest");
        article.setRoleState(2);
        article.setEnable(true);
        article.setAuthor("Admin");
        article.setTitle("Test Title");
        article.setContent("Content");
        article.setCategory(categoryService.find(4L));
        articleService.add(article);

        article = new Article();
        article.setCreated(new Date());
        article.setOrderId(1);
        article.setPreview("preview2");
        article.setName("articleTest2");
        article.setRoleState(2);
        article.setEnable(true);
        article.setAuthor("Admin");
        article.setTitle("Test Title2");
        article.setContent("Content2");
        article.setCategory(categoryService.find(4L));
        articleService.add(article);
    }
    private void initUsers() {
        List<User> users = userService.list();
        if (users.size()>0){
            return;
        }
        UserRole roleAdmin = new UserRole();
        roleAdmin.setName(Constants.admin);
        userRoleService.add(roleAdmin);

        UserRole roleUser = new UserRole();
        roleUser.setName(Constants.user);
        userRoleService.add(roleUser);

        ArrayList<UserRole> adminRoles = new ArrayList<UserRole>();
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(new ShaPasswordEncoder().encodePassword("admin", null));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setCreated(new Date());
        admin.setRoles(adminRoles);
        admin.setEmail("admin@mail.com");
        userService.add(admin);
    }
}
