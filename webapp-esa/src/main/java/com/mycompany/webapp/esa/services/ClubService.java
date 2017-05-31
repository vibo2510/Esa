/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.data.ClubRepository;
import com.mycompany.webapp.esa.data.ParticipantRepository;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Viktoria Bock
 */

@Stateless
public class ClubService implements Serializable, ClubServiceInterface {
    @Inject ClubRepository clubRepository;
    @Inject ParticipantRepository participantRepository;
    @Inject EntityManager entityManager;
    /**
     * Creates a new instance of ClubService
     */
    public ClubService() {
    }
    @Override
    public List<Club> getAllClubs(){
        
        TypedQuery<Club> query = entityManager.createNamedQuery(Club.findAll,Club.class);
        List<Club> clubs = query.getResultList();
        return clubs;
    }
      @Override
    public List<Club> getAllClubsOfParticipant(Participant participant){
        return participant.getClubs();
        
    }
    
    @Override
    public void addClub(Club club) {
        entityManager.persist(club);
    }

    @Override
    public void deleteClub(Club club) {
        Club managedClub = entityManager.find(Club.class, club.getId());
        entityManager.remove(managedClub);
    }

    @Override
    public void updateClub(Club club) {
        entityManager.merge(club);
    }
    
    
    
    
}
