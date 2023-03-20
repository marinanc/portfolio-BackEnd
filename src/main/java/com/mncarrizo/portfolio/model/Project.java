package com.mncarrizo.portfolio.model;

/**
 *
 * @author Marina
 */
public class Project {
    private int id;
    private String name;
    private String description;
    private String link;

    public Project() {
    }

    public Project(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
}
