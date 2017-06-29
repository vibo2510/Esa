/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Leader.Repository;

import AppUser.Leader.DataModel.Leader;
import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;

/**
 *
 * @author roml
 */
public interface LeaderRePoInterface extends Serializable {

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void doAddLeader(Leader leader);

    List<Leader> doGetAllLeaders();

    Leader doGetLeaderByEmail(String email);

    Leader doGetLeaderByID(int id);

    
}
