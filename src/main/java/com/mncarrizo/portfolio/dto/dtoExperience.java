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
    private String dateFrom;
    
    private String dateTo;
    
    @NotBlank
    private String description;
    
    private String img;
    

    public dtoExperience() {
    }

    public dtoExperience(String name, String dateFrom, String dateTo, String description, String img) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.description = description;
        this.img = img;
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

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
}
