/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;


import com.mycompany.webapp.esa.data.ParticipantRepository;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author roml
 */
@Named
@Stateless
public class ParticipantService implements ParticipantServiceInterface {

    @Inject
    ParticipantRepository participantRepository;
    

    public ParticipantService() {

    }

    @Override
    public List<Participant> getAllParticipantsOfClub(Club club) {
        return club.getParticipants();
    }

    @Override
    public void enroleToClub(Participant participant, Club club) {
        participantRepository.doEnroleToClub(participant, club);

    }

    @Override
    public void doDischarge(Club club, Participant participant) {

        participantRepository.doDischargeFromClub(participant, club);
    }

    @Override
    public void addParticipant(Participant participant) {
        participantRepository.doAddParticipant(participant);
    }



    @Override
    public void deleteParticipant(Participant participant) {
        participantRepository.doDeleteParticipant(participant);
    }

    @Override
    public void updateParticipant(Participant participant) {
        participantRepository.doUpdateParticipant(participant);
    }

    @Override
    public List<Participant> getAllParticipants() {
        return participantRepository.doGetAllParticipants();
    }

    @Override
    public Participant getParticipantByEmail(String email) {
       return participantRepository.doGetParticipantByEmail(email);
    }

    @Override
    public boolean isEnroled(Participant p, Club c) {
   
        System.out.println("Club1:"+c);
        for(Club clubOfP: p.getClubs()){
            if(clubOfP.getId()==c.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Participant getParticipantById(int id) {
       return participantRepository.doGetParticipantByID(id);
    }

}
