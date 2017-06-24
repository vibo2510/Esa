/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.data.LeaderRepository;
import com.mycompany.webapp.esa.model.Leader;
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
    private EntityManager entityManager;
    @Inject
    private LeaderRepository lr;        

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
