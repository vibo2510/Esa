/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.services.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;


import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.services.ClubServiceInterface;
import com.mycompany.webapp.esa.services.LeaderServiceInterface;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;

/**
 *
 * @author Viktoria Bock
 */
@Named
@ViewScoped
public class ListClubsController implements Serializable{

    @Inject private ClubProducer clubProducer;
    @Inject private ClubServiceInterface clubService;
    @Inject private AuthenticationManager authenticationManager;
    @Inject private LeaderServiceInterface leaderService;
  
   
    private Leader leader;
    private Club clubToDelete; 
    private List<Club> ClubList;
        
        @PostConstruct
        void init(){
            
             
            leader = (Leader) leaderService.getLeaderById(authenticationManager.getCurrentUserId());
            System.out.println("der leader"+leader +"******************************");
            ClubList = leader.getClubs();
            for(Club c:ClubList){
                System.out.println("Leader-Clubs: "+c.getTitle());
            }
            
            
        }

        public List<Club> getClubList() {
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
                clubService.deleteClub(clubToDelete);
                    updateClubList();
                
	}
        public  void updateClubList(){
            System.out.println("$$$$$$$ClubListWasUpdatet");
            leader= leaderService.getLeaderById(authenticationManager.getCurrentUserId());
            ClubList= leader.getClubs();
        }
}
