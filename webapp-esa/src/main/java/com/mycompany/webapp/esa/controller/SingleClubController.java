/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.services.ParticipantServiceInterface;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

/**
 *
 * @author roml
 */
@SessionScoped
@Named
public class SingleClubController implements Serializable{
    @Inject private ParticipantServiceInterface participantService;
    private List<Participant> listParticipants;
    private Club club;
    
    
    
    public List<Participant> getParticipants(){
        return listParticipants;
    }
    public void setClub(Club club){
        this.club= club;
        listParticipants= participantService.getAllParticipantsOfClub(club);
        
        
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
    
    
    public void enroleToClub(){
        System.out.println("Hinzufuegen anfangen");
        Participant p= new  Participant();
        p.setFirstname("Harald");
        p.setLastname("Schlegel");
        p.setEmail("hs@web.de");
        LinkedList<Club> list = new LinkedList<Club>();
        p.setClubs(list);
        participantService.addParticipant(p);
        participantService.enroleToClub(p, this.club);
        listParticipants= participantService.getAllParticipantsOfClub(this.club);
        System.out.println("Hinzufuegen geklappt");
        
        
    }
    
}
