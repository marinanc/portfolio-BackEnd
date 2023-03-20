package com.mncarrizo.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Marina
 */
public class dtoProject {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String link;

    public dtoProject() {
    }

    public dtoProject(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
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
