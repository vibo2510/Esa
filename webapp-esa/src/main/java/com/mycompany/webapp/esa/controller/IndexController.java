/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.model.Participant;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import com.mycompany.webapp.esa.services.LeaderServiceInterface;
import com.mycompany.webapp.esa.services.ParticipantServiceInterface;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class IndexController implements Serializable {

    @Inject
    private ClubServiceInterface clubService;

    @Inject
    private LeaderServiceInterface leaderService;
    
     @Inject
    private ParticipantServiceInterface participantService;
     
     @Inject
     private AuthenticationManager am;

    private List<Club> ClubList;
    private List<String> images;

    @PostConstruct
    void init() {
        


        ClubList = clubService.getAllClubs();
        if(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()!=null && !am.isLoggedIn()){
            am.setCurrentUserId();
            
        }
    /*   
    Participant romina=new Participant();
    romina.setEmail("romina.herbst@hs-furtwangen.de");
    romina.setFirstname("Romina");
    romina.setLastname("Herbst");
 
    romina.setClubs(new ArrayList<Club>());
    participantService.addParticipant(romina);
        System.out.println("romina: "+romina);
   
    
    Leader viktoria= new Leader();
    viktoria.setFirstname("Viktoria");
    viktoria.setLastname("Bock");
    viktoria.setEmail("viktoria.bock@hs-furtwangen.de");
        viktoria.setClubs(new ArrayList<Club>());
    leaderService.addLeader(viktoria);
        System.out.println("loggedin: "+am.isLoggedIn());*/
        
        //Leader ll= leaderService.getLeaderByEmail("viktoria.bock@hs-furtwangen.de");
       
        //System.out.println("Vorname:"+ll.getLastname());
        
        
    
        

        images = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            images.add("slider" + i + ".png");
        }
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

    public List<String> getImages() {
        return images;
    }

}
