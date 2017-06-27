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
    @Inject 
    private EntityManager em;
    @Inject
    private LeaderRepository lr;
 
    
    
    public List<Club> doGetAllClubs(){
        TypedQuery<Club> query = em.createNamedQuery(Club.findAll,Club.class);
        List<Club> clubs = query.getResultList();
        return clubs;
    }
    public void doAddClub(Club newClub,int leaderid){
        
        newClub.setParticipants(new ArrayList<Participant>());
        Leader leader =lr.doGetLeaderByID(leaderid);
        newClub.setLeader(leader);
        //
        //em.merge(leader);
        //lr.doRefreshLeader(leader);
        em.refresh(leader);
        em.persist(newClub);
        
        
         
        
        
    }
    public void doDeleteClub(Club club){
        System.out.println("deleteClub:"+club);
        Club managedClub = em.find(Club.class, club.getId());
        em.remove(managedClub);
        em.refresh(managedClub.getLeader());
        
    }
     public void doUpdateClub(Club club){
         em.merge(club);
     }
     public Club doGetClubByID(int id){
        List<Club> cl= em.createNamedQuery("Club.findById").setParameter("id", id).getResultList();
        return (Club)cl.get(0);
     }
     

    }
