/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.data.ClubRepository;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;



/**
 *
 * @author Viktoria Bock
 */

@Stateless
public class ClubService implements Serializable, ClubServiceInterface {
    @Inject ClubRepository clubRepository;
    @Inject EntityManager entityManager;
    @Inject AuthenticationManager am;
    @Inject ClubRepository cr;
    @Inject private LeaderServiceInterface leaderService;
    /**
     * Creates a new instance of ClubService
     */
    public ClubService() {
    }
    @Override
    public List<Club> getAllClubs(){
        
        return cr.doGetAllClubs();
        
    }
      @Override
    public List<Club> getAllClubsOfParticipant(Participant participant){
        return participant.getClubs();
        
    }
    
    @Override
    public void addClub(Club club) {
        
        Leader leader=(Leader) leaderService.getLeaderById(am.getCurrentUserId());
        cr.doAddClub(club, leader);
    }

    @Override
    public void deleteClub(Club club) {
        cr.doDeleteClub(club);
       
    }

    @Override
    public void updateClub(Club club) {
        cr.doUpdateClub(club);
    }

    @Override
    public Club getClubById(int id) {
         return cr.doGetClubByID(id);
    }


    

}
