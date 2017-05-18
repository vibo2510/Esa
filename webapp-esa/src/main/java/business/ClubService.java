/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import com.mycompany.webapp.esa.data.ClubRepository;
import com.mycompany.webapp.esa.data.ParticipantRepository;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Viktoria Bock
 */
@Named
@RequestScoped
public class ClubService implements Serializable {
    @Inject ClubRepository clubRepository;
    @Inject ParticipantRepository participantRepository;

    /**
     * Creates a new instance of ClubService
     */
    public ClubService() {
    }
    
    public ArrayList<Club> getAllClubs(){
        return clubRepository.getAllClubs();
    }
    
    public boolean doDeleteClub(Club club){
        return clubRepository.deleteClub(club);
    }
    
    public boolean doEditClub(Club club){
        return clubRepository.updateClub(club);
    }
    
    public boolean  doAddClub(Club club){
        return clubRepository.addClub(club);
    }
    
    public ArrayList<Club> getAllClubsOfParticipant(Participant participant){
        return clubRepository.getParticipantClubs(participant);
        
    }
    
    public boolean doDischarge(Club club, Participant participant){
        return participantRepository.dischargeClub(club, participant);
        
    }
    
}
