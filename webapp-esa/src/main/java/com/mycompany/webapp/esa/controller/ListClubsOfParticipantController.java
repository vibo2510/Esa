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
import com.mycompany.webapp.esa.services.ParticipantServiceInterface;
import java.io.Serializable;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class ListClubsOfParticipantController implements Serializable {

    @Inject
    private ClubServiceInterface clubService;
    @Inject
    private ParticipantServiceInterface participantService;
    /**
     * Creates a new instance of ListClubsOfParticipantController
     */
    private List<Club> ClubList;
    private Club clubToDischarge;
    private Participant participant = new Participant();

    @PostConstruct
    void init() {
        //Solange kein Login PArticipant hier erzeugen
        List<Participant> list = participantService.getAllParticipants();
        ClubList = list.get(0).getClubs();
        System.out.println(list.get(0).getFirstname());
        System.out.println("**********************************************************************");

    }

    public List<Club> getClubList() {
        return ClubList;
    }

    public void doDischarge(Club club) {
        this.clubToDischarge = club;
    }

    public void commitDischarge() {
        /*if(clubService.doDischarge(clubToDischarge, participant)){
                 ClubList=clubService.getAllClubsOfParticipant(participant);
            }else{
                System.out.println("Fehler beim Austragen aufgetreten");
            }*/
    }

}
