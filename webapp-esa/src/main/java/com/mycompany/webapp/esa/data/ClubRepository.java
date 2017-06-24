/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;

/**
 *
 * @author Viktoria Bock
 */

import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


@Named
@Stateless
public class ClubRepository implements Serializable{
    @Inject EntityManager entityManager;
 
    
    
    public List<Club> doGetAllClubs(){
        TypedQuery<Club> query = entityManager.createNamedQuery(Club.findAll,Club.class);
        List<Club> clubs = query.getResultList();
        return clubs;
    }
    public String doAddClub(Club newClub,Leader leader){
        
        newClub.setParticipants(new ArrayList<Participant>());
        newClub.setLeader(leader);
        entityManager.refresh(leader);
        entityManager.persist(newClub);
        return "";
    }
    public String doDeleteClub(Club club){
        System.out.println("deleteClub:"+club);
        Club managedClub = entityManager.find(Club.class, club.getId());
        entityManager.remove(managedClub);
        entityManager.refresh(managedClub.getLeader());
        return "";
    }
     public void doUpdateClub(Club club){
         entityManager.merge(club);
     }
     public Club doGetClubByID(int id){
        List<Club> cl= entityManager.createNamedQuery("Club.findById").setParameter("id", id).getResultList();
        return (Club)cl.get(0);
     }
     

    }
