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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author roml
 */
@Named
@Stateless
public class ParticipantService implements ParticipantServiceInterface {

    @Inject
    ClubRepository clubRepository;
    @Inject
    ParticipantRepository participantRepository;
    @Inject
    EntityManager entityManager;

    public ParticipantService() {

    }

    @Override
    public List<Participant> getAllParticipantsOfClub(Club club) {
        return club.getParticipants();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void enroleToClub(Participant participant, Club club) {
        List<Participant> list = club.getParticipants();
        list.add(participant);
        club.setParticipants(list);

        List<Club> listclub = participant.getClubs();
        listclub.add(club);
        participant.setClubs(listclub);

        entityManager.merge(participant);
        entityManager.merge(club);

    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)

    public void doDischarge(Club club, Participant participant) {

        List<Participant> list = club.getParticipants();
        list.remove(participant);
        club.setParticipants(list);

        List<Club> listclub = participant.getClubs();
        listclub.remove(club);
        participant.setClubs(listclub);

        entityManager.merge(participant);
        entityManager.merge(club);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void addParticipant(Participant participant) {
        entityManager.persist(participant);
    }



    @Override
    public void deleteParticipant(Participant participant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateParticipant(Participant participant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Participant> getAllParticipants() {
        TypedQuery<Participant> query = entityManager.createNamedQuery(Participant.findAll, Participant.class);
        List<Participant> participants = query.getResultList();
        return participants;
    }

}
