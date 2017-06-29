/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Leader.Service;

import AppUser.Leader.Repository.LeaderRePoInterface;
import AppUser.Leader.Repository.LeaderRepository;
import AppUser.Leader.DataModel.Leader;
import AppUser.Leader.DataModel.Leader;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;


/**
 *
 * @author Viktoria Bock
 */
@Named
@Stateless
public class LeaderService implements Serializable, LeaderServiceInterface {
    
    @Inject
    private LeaderRePoInterface lr;        

    @Override
    public List<Leader> getAllLeaders() {
        return lr.doGetAllLeaders();
    }

    @Override
    public Leader getLeaderByEmail(String email) {
            return lr.doGetLeaderByEmail(email);
       
       
    }

    @Override
    public void addLeader(Leader leader) {
       lr.doAddLeader(leader);
    }

    @Override
    public Leader getLeaderById(int id) {
       
            return lr.doGetLeaderByID(id);
       
    }
   

}
