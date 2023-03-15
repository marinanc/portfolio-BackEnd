package com.mncarrizo.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Marina
 */
public class dtoExperience {
    @NotBlank
    private String name;
    
    @NotBlank
    private String description;

    public dtoExperience() {
    }

    public dtoExperience(String name, String description) {
        this.name = name;
        this.description = description;
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
    
    
}
