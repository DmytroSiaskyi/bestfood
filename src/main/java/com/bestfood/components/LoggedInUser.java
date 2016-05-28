package com.bestfood.components;

import com.bestfood.dto.UserDto;
import com.bestfood.entity.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Dmitry Syaskiy
 */
@Component
public class LoggedInUser {
    @XmlTransient
    private final UserDto user;

    // Suppresses default constructor, ensuring non-instantiability.
    public LoggedInUser() {
        this.user = null;
    }
    // Constructor that innit field user.
    public LoggedInUser(UserDto user) {
        this.user = user;
    }
    @XmlTransient
    public String test(){
        return "TEST";
    }
    /**
     * This method have access to SecurityContextHolder and pick up auth principal.
     * If current session user will not contains any authorities under this level
     * method will return null value.
     *
     * @return this method return UserDto model that describe current session authorities.
     *
     * @see    UserDto
     */
    @XmlTransient
    public UserDto getCurrentUser() {
        if(user != null) {
            return user;
        }

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			Object principal = auth.getPrincipal();

		    if (principal instanceof UserDto) {
		    	return (UserDto) principal;
		    }
		}
	    return null;
	}

    /**
     * This method have access to SecurityContextHolder and pick up auth principal.
     * If current session user will not contains any authorities under this level
     * method will return null value.
     *
     * @return this method return UserDto model that describe current session authorities.
     *
     * @see    UserDto
     */
    public UserDto getUser(){
        UserDto dto = getCurrentUser();
        return dto;
    }

    /**
     * This method have check SecurityContextHolder and auth session current user.
     *
     * @return if result UserDto model will have auth principals method will return true.
     */
    @XmlTransient
    public boolean isLoggedIn(){
        return getCurrentUser() != null;
    }

    /**
     * This method have check security session on user role.
     *
     * @param role role user as String
     * @return if result UserDto model will have role will return true.
     */
    public boolean hasRole(String role){
        UserDto userDto = getCurrentUser();
        if(userDto ==  null){
            return false;
        }
        int index = userDto.getRoles().indexOf(new UserRole(role));
        return index >= 0;
    }

    public String getRole(){
        UserDto userDto = getCurrentUser();
        if(userDto ==  null){
            return "null";
        }
        String role = userDto.getStringRole();
        return role;
    }

    /**
     * Is current user have role ROLE_ADMIN will return true.
     *
     * @return if result UserDto model is admin will return true.
     */
    @XmlTransient
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }

    /**
     * Is current user have role ROLE_USER will return true.
     *
     * @return if result UserDto model is user will return true.
     */
    public boolean isUser() {
        return hasRole("ROLE_USER");
    }
}
