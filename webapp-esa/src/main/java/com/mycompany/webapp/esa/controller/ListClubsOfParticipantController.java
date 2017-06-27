/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.services.ClubService;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import com.mycompany.webapp.esa.services.ParticipantServiceInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class ListClubsOfParticipantController implements Serializable {

    @Inject
    private ClubServiceInterface clubService;
    @Inject
    private ParticipantServiceInterface participantService;
    @Inject
    private AuthenticationManager am;
    /**
     * Creates a new instance of ListClubsOfParticipantController
     */
    private List<Club> ClubList;
    private Club clubToDischarge;
    private Participant p;
    

    @PostConstruct
    void init() {
       
        p= (Participant) participantService.getParticipantById(am.getCurrentUserId());
        if (p!= null){
        ClubList = p.getClubs();
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message Title", "Teilnehmer konnte nicht gefunden werden");
           RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

    public List<Club> getClubList() {
        return ClubList;
    }

    public void doDischarge(Club club) {
        this.clubToDischarge = club;
    }
    
    public void commitDischarge() {
        
        participantService.doDischarge(clubToDischarge, p);
        ClubList=clubService.getAllClubsOfParticipant(p);
            
    }

}
