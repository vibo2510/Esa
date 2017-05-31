/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.services.ClubService;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class ListClubsOfParticipantController implements Serializable{
   @Inject private ClubServiceInterface clubService;
    /**
     * Creates a new instance of ListClubsOfParticipantController
     */
       private ArrayList<Club> ClubList;
       private Club clubToDischarge;
        private Participant participant= new Participant();
        @PostConstruct
        void init(){
            //Solange kein Login PArticipant hier erzeugen
            
            participant.setId(1);
            participant.setFirstname("Hans");
            participant.setLastname("Wurst");
            participant.setEmail("hw@web.de");
            //ClubList=clubService.getAllClubsOfParticipant(participant);
        }
        
        
        public ArrayList<Club> getClubList() {
            return ClubList;
        }
        
        public void doDischarge(Club club){
            this.clubToDischarge= club;
        }
        
        public void commitDischarge(){
            /*if(clubService.doDischarge(clubToDischarge, participant)){
                 ClubList=clubService.getAllClubsOfParticipant(participant);
            }else{
                System.out.println("Fehler beim Austragen aufgetreten");
            }*/
        }
   

    
}
