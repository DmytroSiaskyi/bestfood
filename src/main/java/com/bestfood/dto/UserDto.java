package com.bestfood.dto;

import com.bestfood.entity.User;
import com.bestfood.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class UserDto implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private List<UserRole> roles;

    private String newPassword;
    private String confirmPassword;
    private Boolean enabled = true;
    private Date created;

    private ConcurrentHashMap<String,Object> CACHE;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName==null?null:firstName.trim();
    }

    @XmlTransient
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName==null?null:lastName.trim();
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getStringRole(){
        for (UserRole r: roles){
            if (r.getName().equalsIgnoreCase("admin"))
                return "admin";
        }
        return "user";
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username==null?null:username.trim();
    }

    public void setPassword(String password) {
        this.password = password==null?null:password.trim();
    }

    @XmlTransient
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @XmlTransient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public static UserDto create(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(user.getRoles());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.isEnabled());
        userDto.setCreated(user.getCreated());
        return userDto;
    }
    public static List<UserDto> create(List<User> users){
        List<UserDto> dtoList = new ArrayList<UserDto>();
        for (User u:users){
            dtoList.add(UserDto.create(u));
        }
        return dtoList;
    }

    public boolean hasRole(String role){
        int index = this.getRoles().indexOf(new UserRole(role));
        return index >= 0;
    }

    public boolean isNewUser() {
        return this.password == null;
    }

    public boolean isValidNewPassword() {
        if (this.newPassword == null || this.newPassword.length() < 6
            || this.confirmPassword == null || this.confirmPassword.length() < 6
                || !this.newPassword.equals(this.confirmPassword)){
            return false;
        }
        return true;
    }

    public ConcurrentHashMap<String, Object> getCACHE() {
        return CACHE;
    }

    public void setCACHE(ConcurrentHashMap<String, Object> CACHE) {
        this.CACHE = CACHE;
    }
}
