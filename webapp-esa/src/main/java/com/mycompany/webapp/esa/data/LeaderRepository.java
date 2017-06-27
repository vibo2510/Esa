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
    private EntityManager em;
    
    public List<Leader> doGetAllLeaders(){
        TypedQuery<Leader> query = em.createNamedQuery(Leader.findAll, Leader.class);
        List<Leader> leaders = query.getResultList();
        return leaders;
    }
    
    public Leader doGetLeaderByEmail(String email){
         List<Leader> ll= em.createNamedQuery("Leader.findByEmail").setParameter("email", email).getResultList();
        if(ll.size()>0){
         return (Leader)ll.get(0);
        }
        return null;
    }
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void doAddLeader(Leader leader){
         em.persist(leader);
    }
    
    public Leader doGetLeaderByID(int id){
       List<Leader> ll= em.createNamedQuery("Leader.findById").setParameter("id", id).getResultList();
       if(ll.size()>0){
       return (Leader)ll.get(0);
       }
       return null;
    }
    
    public void doRefreshLeader(Leader leader){
        em.refresh(leader);
    }
}