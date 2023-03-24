
package com.mncarrizo.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Marina
 */

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String name;
    
    @NotNull
    @Size(min = 1, max = 50, message = "No cumple con la longitud")
    private String lastname;
    
    @NotNull
    private String description;
    
    
    private String img;

    public Person() {
    }

    public Person(String name, String lastname, String description, String img) {
        this.name = name;
        this.lastname = lastname;
        this.description = description;
        this.img = img;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
