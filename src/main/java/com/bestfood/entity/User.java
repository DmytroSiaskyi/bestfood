package com.bestfood.entity;

import com.bestfood.dto.UserDto;
import com.bestfood.models.EmailValidator;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(length = 64, nullable=false, unique = true)
    private String login;

    @Column(length = 64, nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role_link",
                joinColumns=@JoinColumn(name="user_id"),
                inverseJoinColumns=@JoinColumn(name="user_role_id"))
    private List<UserRole> roles;

    @Column(name="first_name", length = 128, nullable=false)
    private String firstName;

    @Column(name="last_name", length = 128, nullable=false)
    private String lastName;

    @Column(name="email", length = 64, nullable=false)
    private String email;

    @Column(name="enabled",nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String validate(){
        String error = "";
        if(login == null) {
            error = "Неправильно заповнене поле логін!";
        }
        if(password == null) {
            error = "Не введений пароль!";
        }else{
            if(password.length()>7){
            password = new ShaPasswordEncoder().encodePassword(password, null);} else{
                error = "Довжина паролю має бути більша 7!";
            }
        }
        if(firstName == null)
            error = "Введіть імя!";
        if(lastName == null)
            error = "Введіть прізвище!";
        if(email == null) {
            error = "Заповніть адресу електронної пошти!";
        }else{
            EmailValidator em = new EmailValidator();
            if(!em.validate(email)){
                error = "Не корректно введена адреса!";
            }
        }
        return error;
    }
    public List<UserRole> getRoles() {
        return roles;

    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public static User create(UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail((dto.getEmail()));
        user.setEnabled(dto.isEnabled());
        return user;
    }

    public UserDto toDto(){
        UserDto user = new UserDto();
        user.setId(this.id);
        user.setUsername(this.login);
        user.setPassword(this.password);
        user.setRoles(this.roles);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setEnabled(this.enabled);
        user.setCreated(this.created);
        return user;
    }
}
