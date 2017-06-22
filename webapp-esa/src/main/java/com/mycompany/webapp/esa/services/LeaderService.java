/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.services;

import com.mycompany.webapp.esa.model.Leader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Viktoria Bock
 */
@Named
@Stateless
public class LeaderService implements Serializable, LeaderServiceInterface {
    @Inject
    private EntityManager entityManager;

    @Override
    public List<Leader> getAllLeaders() {
        TypedQuery<Leader> query = entityManager.createNamedQuery(Leader.findAll, Leader.class);
        List<Leader> leaders = query.getResultList();
        return leaders;
    }

    @Override
    public Leader getLeaderByEmail(String email) {
        List<Leader> ll= entityManager.createNamedQuery("Leader.findByEmail").setParameter("email", email).getResultList();
        return (Leader)ll.get(0);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void addLeader(Leader leader) {
        entityManager.persist(leader);
    }

    @Override
    public Leader getLeaderById(int id) {
        List<Leader> ll= entityManager.createNamedQuery("Leader.findById").setParameter("id", id).getResultList();
        return (Leader)ll.get(0);
    }
   

}
