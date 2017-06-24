/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;


import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


/**
 *
 * @author Viktoria Bock
 */

@Named
@Stateless
public class ParticipantRepository implements Serializable{
    @Inject
    EntityManager entityManager;
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void doEnroleToClub(Participant participant,Club club){
        List<Participant> list = club.getParticipants();
        list.add(participant);
        club.setParticipants(list);

        List<Club> listclub = participant.getClubs();
        listclub.add(club);
        participant.setClubs(listclub);

        entityManager.merge(participant);
        entityManager.merge(club);
    }
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void doDischargeFromClub(Participant participant, Club club){
        List<Participant> list = club.getParticipants();
        list.remove(participant);
        club.setParticipants(list);
        List<Club> listclub = participant.getClubs();
        listclub.remove(club);
        participant.setClubs(listclub);
        System.out.println("Austragen");
        entityManager.merge(participant);
        entityManager.merge(club);
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void doAddParticipant(Participant participant){
        System.err.println(participant+" wurde hinzugefügt");
        entityManager.persist(participant); 
    }
    
    public void doDeleteParticipant(Participant participant){
        Participant dbParticipant = entityManager.find(Participant.class, participant.getId());
        entityManager.remove(dbParticipant);
    }
    
    public void doUpdateParticipant(Participant participant){
        entityManager.merge(participant);
    }
    
    public List<Participant> doGetAllParticipants(){
        TypedQuery<Participant> query = entityManager.createNamedQuery(Participant.findAll, Participant.class);
        List<Participant> participants = query.getResultList();
        return participants;
    }
    
    public Participant doGetParticipantByEmail(String email){
       List<Participant> pl= entityManager.createNamedQuery("Participant.findByEmail").setParameter("email", email).getResultList();
       return (Participant)pl.get(0);
    }
    
    public Participant doGetParticipantByID(int id){
       List<Participant> pl= entityManager.createNamedQuery("Participant.findById").setParameter("id", id).getResultList();
       return (Participant)pl.get(0);
    }
    
}
