/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Club.Service;

import Club.DataModel.Club;
import AppUser.Leader.DataModel.Leader;
import AppUser.Participant.DataModel.Participant;
import java.util.List;

/**
 *
 * @author roml
 */
public interface ClubServiceInterface {
    List<Club> getAllClubs();
    List<Club> getAllClubsOfParticipant(Participant participant);
    void addClub(Club club,int leader);
    void deleteClub(Club club);
    void updateClub(Club club);
    Club getClubById(int id);
  
}
