/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import business.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;


import com.mycompany.webapp.esa.model.Club;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;

/**
 *
 * @author Viktoria Bock
 */
@SessionScoped
@Named
public class ListClubsController implements Serializable{

    @Inject private ClubProducer clubProducer;
    @Inject private ClubService clubService;
   
   
    
    private Club clubToDelete; 
    private ArrayList<Club> ClubList;
        
        @PostConstruct
        void init(){
            ClubList=clubService.getAllClubs();
        }

        public ArrayList<Club> getClubList() {
            return ClubList;
        }

    
        public String doAddClub() {
                
                clubProducer.prepareAddClub();
		System.out.println("Add Club");
		return Pages.EDIT_CLUB;
	}
	public String doEditClub(Club club) {
                clubProducer.prepareEditClub(club);
		System.out.println("Edit Club "+club);
		return Pages.EDIT_CLUB;
	}
        
	public String doListClubs(Club club) {
		System.out.println("List all my Clubs "+club);
		return Pages.LIST_CLUBS;
	}
        
        public String doListParticipants(Club club) {
		System.out.println("Participants of Club "+club);
		return Pages.LIST_PARTICIPANTS;
	}
        public void doDeleteClub(Club club) {
		this.clubToDelete = club;
		System.out.println("Club registered for deletion!");
	}

	public void commitDeleteClub() {
		System.out.println("Deletion not implemented, yet!");
                if(clubService.doDeleteClub(clubToDelete)){
                    updateClubList();
                }else{
                    System.out.println("Fehler beim Löschen des Referats "+clubToDelete.getTitle());
                }
	}
        public  void updateClubList(){
            ClubList= clubService.getAllClubs();
        }
}
