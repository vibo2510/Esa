/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;


import com.mycompany.webapp.esa.model.Leader;
import java.io.Serializable;
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
public class LeaderRepository implements Serializable{
    @Inject
    private EntityManager entityManager;
    
    public List<Leader> doGetAllLeaders(){
        TypedQuery<Leader> query = entityManager.createNamedQuery(Leader.findAll, Leader.class);
        List<Leader> leaders = query.getResultList();
        return leaders;
    }
    
    public Leader doGetLeaderByEmail(String email){
         List<Leader> ll= entityManager.createNamedQuery("Leader.findByEmail").setParameter("email", email).getResultList();
        return (Leader)ll.get(0);
    }
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void doAddLeader(Leader leader){
         entityManager.persist(leader);
    }
    
    public Leader doGetLeaderByID(int id){
       List<Leader> ll= entityManager.createNamedQuery("Leader.findById").setParameter("id", id).getResultList();
       return (Leader)ll.get(0);
    }
    
}
