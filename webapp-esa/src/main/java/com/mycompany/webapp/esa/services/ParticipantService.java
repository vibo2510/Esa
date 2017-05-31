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
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author roml
 */
@Named
@RequestScoped
public class ParticipantService {
     @Inject ClubRepository clubRepository;
     @Inject ParticipantRepository participantRepository;
     
     public ParticipantService(){
         
     }
     public ArrayList<Participant> getAllParticipantsOfClub(Club club){
        return clubRepository.getParticiOfClub(club);
    }
     
     public boolean enroleToClub(Participant participant, Club club){
         return participantRepository.enroleToClub(club, participant);
     }
}
