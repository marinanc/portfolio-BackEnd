package com.mncarrizo.portfolio.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Marina
 */
public class dtoSkill {
    @NotBlank
    private String name;
    @NotBlank
    private int percentage;

    public dtoSkill() {
    }

    public dtoSkill(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
}
