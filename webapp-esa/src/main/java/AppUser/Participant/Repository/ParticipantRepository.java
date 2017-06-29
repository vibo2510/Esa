/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Participant.Repository;


import AppUser.Participant.DataModel.Participant;
import Club.DataModel.Club;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


/**
 *
 * @author Viktoria Bock
 */

@Named
@Stateless
public class ParticipantRepository implements ParticipantRePoInterface{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
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
    @Override
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
    @Override
    public void doAddParticipant(Participant participant){
        System.err.println(participant+" wurde hinzugefügt");
        entityManager.persist(participant); 
    }
    
    @Override
    public void doDeleteParticipant(Participant participant){
        Participant dbParticipant = entityManager.find(Participant.class, participant.getId());
        entityManager.remove(dbParticipant);
    }
    
    @Override
    public void doUpdateParticipant(Participant participant){
        entityManager.merge(participant);
    }
    
    @Override
    public List<Participant> doGetAllParticipants(){
        TypedQuery<Participant> query = entityManager.createNamedQuery(Participant.findAll, Participant.class);
        List<Participant> participants = query.getResultList();
        return participants;
    }
    
    @Override
    public Participant doGetParticipantByEmail(String email){
       List<Participant> pl= entityManager.createNamedQuery("Participant.findByEmail").setParameter("email", email).getResultList();
       return (Participant)pl.get(0);
    }
    
    @Override
    public Participant doGetParticipantByID(int id){
       List<Participant> pl= entityManager.createNamedQuery("Participant.findById").setParameter("id", id).getResultList();
       return (Participant)pl.get(0);
    }
    
}
