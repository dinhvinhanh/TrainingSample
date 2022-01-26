package com.elcom.jpa.sample.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.io.Serializable;

@Entity

public class User implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    @Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
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
        return String.format("User{id=%d, name='%s'}", id, name);
    }
}
