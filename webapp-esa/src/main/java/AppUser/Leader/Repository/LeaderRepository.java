/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Leader.Repository;


import AppUser.Leader.DataModel.Leader;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


/**
 *
 * @author Viktoria Bock
 */
@Named
@Stateless
public class LeaderRepository implements LeaderRePoInterface{
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<Leader> doGetAllLeaders(){
        TypedQuery<Leader> query = em.createNamedQuery(Leader.findAll, Leader.class);
        List<Leader> leaders = query.getResultList();
        return leaders;
    }
    
    @Override
    public Leader doGetLeaderByEmail(String email){
         List<Leader> ll= em.createNamedQuery("Leader.findByEmail").setParameter("email", email).getResultList();
        if(ll.size()>0){
         return (Leader)ll.get(0);
        }
        return null;
    }
    
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public void doAddLeader(Leader leader){
         em.persist(leader);
    }
    
    @Override
    public Leader doGetLeaderByID(int id){
       List<Leader> ll= em.createNamedQuery("Leader.findById").setParameter("id", id).getResultList();
       if(ll.size()>0){
       return (Leader)ll.get(0);
       }
       return null;
    }
    
  
}