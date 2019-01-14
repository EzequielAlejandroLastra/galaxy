package com.elastra.meli.Model;

import javax.persistence.*;

@Entity
public class weatherCondition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "DESC")
    private String description;

    @Column(name = "TYPE")
    private String type;

    public weatherCondition(String description, String type) {
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
