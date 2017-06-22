/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.services.ParticipantServiceInterface;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import com.mycompany.webapp.esa.services.LeaderServiceInterface;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author roml
 */
@ViewScoped
@Named
public class SingleClubController implements Serializable{
    @Inject private ParticipantServiceInterface participantService;
    private List<Participant> listParticipants;
    private Club club;
    private Participant user;
    private boolean isEnroleable;
    @Inject
    private AuthenticationManager am;
    @Inject
    private ClubServiceInterface clubService;

    
    @PostConstruct
    void init(){
         Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         
         String clubid = params.get("club_id");
         int club_id = Integer.parseInt(clubid);
         System.out.println("parameter******:"+ am.getCurrentUserId());
         //System.out.println("parameter Int******:"+ club_id);
         setClub(clubService.getClubById(club_id));
         if(am.getCurrentUserId()!=null){
        user= (Participant) participantService.getParticipantById(am.getCurrentUserId());
         }
        isEnroleable=isEnabeldToEnrole();
    }
    
    public List<Participant> getParticipants(){
        return listParticipants;
    }
    public void setClub(Club club){
        this.club= club;
        listParticipants= club.getParticipants();
        
        
    }
    public Club getClub(){
        return club;
    }
    public String getTitle(){
        return club.getTitle();
    }
    public String getDescription(){
        return club.getDescription();
    }
    public String getDay(){
        return club.getDayOfWeek().toString();
    }
    public String getStartTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        return sdf.format(club.getStarttime()).toString();
    }
    public String getEndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        return sdf.format(club.getEndtime()).toString();
    }

    public boolean isIsEnroleable() {
        return isEnroleable;
    }

    public void setIsEnroleable(boolean isEnroleable) {
        this.isEnroleable = isEnroleable;
    }

    public Participant getUser() {
        return user;
    }

    public void setUser(Participant user) {
        this.user = user;
    }
    
    
    
    public void enroleToClub(){
        
        participantService.enroleToClub(user, this.club);
        listParticipants= this.club.getParticipants();
        System.out.println("Hinzufuegen geklappt");  
    }
    
    public boolean isEnabeldToEnrole(){
        
        if(am.isLoggedIn()){
            if(am.isLeader()==false && participantService.isEnroled(user, this.club)==false){
                return false;
            }
        }
        
        return true;
    }
        
        
    
    
}
