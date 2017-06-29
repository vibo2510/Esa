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
import javax.transaction.Transactional;

/**
 *
 * @author roml
 */
public interface ParticipantRePoInterface extends Serializable {

    @Transactional(value = Transactional.TxType.REQUIRED)
    void doAddParticipant(Participant participant);

    void doDeleteParticipant(Participant participant);

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void doDischargeFromClub(Participant participant, Club club);

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void doEnroleToClub(Participant participant, Club club);

    List<Participant> doGetAllParticipants();

    Participant doGetParticipantByEmail(String email);

    Participant doGetParticipantByID(int id);

    void doUpdateParticipant(Participant participant);
    
}
