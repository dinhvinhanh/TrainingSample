package com.springboot.profiles.model;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@Table(name="user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(){};

    public User(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public User(String name){
        super();
        this.name = name;
    }

    @Column(name = "name")
    private String name;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

