/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import java.util.List;
import java.util.Set;

/**
 *
 * @author roml
 */
public interface ParticipantServiceInterface {
    List<Participant> getAllParticipants();
    List<Participant> getAllParticipantsOfClub(Club club);
    void enroleToClub(Participant participant, Club club);
    void doDischarge(Club club, Participant participant);
    void addParticipant(Participant participant);
    void deleteParticipant(Participant participant);
    void updateParticipant(Participant participant);
    Participant getParticipantByEmail(String email);
    boolean isEnroled(Participant p, Club c);
    Participant getParticipantById(int id);
}
