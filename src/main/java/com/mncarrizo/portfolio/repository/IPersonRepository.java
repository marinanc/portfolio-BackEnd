/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mncarrizo.portfolio.repository;

import com.mncarrizo.portfolio.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marina
 */

@Repository
public interface IPersonRepository extends JpaRepository<Person,Long>{
    
}
