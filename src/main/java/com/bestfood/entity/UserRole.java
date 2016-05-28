package com.bestfood.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority,Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @Column(length = 32, nullable=false)
    private String name;

    public UserRole(){

    }

    public UserRole(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserRole))
            return false;
        UserRole role = (UserRole)obj;
        return role.getName().equals(getName());
    }
}
