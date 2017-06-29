/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Club.Repository;

import Club.DataModel.Club;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author roml
 */
public interface ClubRePoInterface extends Serializable {

    void doAddClub(Club newClub, int leaderid);

    void doDeleteClub(Club club);

    List<Club> doGetAllClubs();

    Club doGetClubByID(int id);

    void doUpdateClub(Club club);
    
}
