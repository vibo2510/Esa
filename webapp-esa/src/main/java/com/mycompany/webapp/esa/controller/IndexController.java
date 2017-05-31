/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.services.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class IndexController implements Serializable {

    @Inject
    private ClubServiceInterface clubService;
    

    private List<Club> ClubList;

    @PostConstruct
    void init() {
      
        ClubList = clubService.getAllClubs();
    }

    public List<Club> getClubList() {
        
       /* String email= (String) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
       System.out.println(email);
       System.out.println("**********************************************");*/
    
        
        return ClubList;
    }

    public String updateClubList() {
        System.out.print("geupdatet");
        ClubList = clubService.getAllClubs();
        return "/" + Pages.INDEX;
    }
   
            
        

}
