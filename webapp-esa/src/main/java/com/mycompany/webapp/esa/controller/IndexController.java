/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import business.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;
import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Leader;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class IndexController implements Serializable {

    @Inject
    private ClubService clubService;
    @Inject
    private EntityManager em;

    private ArrayList<Club> ClubList;

    @PostConstruct
    void init() {
        ClubList = clubService.getAllClubs();
    }

    public ArrayList<Club> getClubList() {
        return ClubList;
    }

    public String updateClubList() {
        System.out.print("geupdatet");
        ClubList = clubService.getAllClubs();
        return "/" + Pages.INDEX;
    }
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void doTest(){
        Club club = new Club();
        club.setTitle("Bowling");
        java.util.Date start1= new java.util.Date();
        start1.setMinutes(30);
        start1.setHours(21);
        club.setStarttime(start1);
        club.setEndtime(start1);
        club.setDayOfWeek(Club.DayOfWeek.MONDAY);
        club.setDescription("svndjnsdvjjsdvn");
        club.setMaxParticipants(10);
        Leader l= new Leader();
        l.setId(1);
        l.setFirstname("Dieter");
        l.setLastname("Kramer");
        l.setEmail("ab@web.de");
        club.setLeader(l);
        club.setRoom("Aula");
        
        em.persist(club);
        TypedQuery<Club> query = em.createNamedQuery(Club.findAll,Club.class);
        List<Club> clubs = query.getResultList();
        System.out.println("Ultimativer Test von Entity*****************************************"+clubs.toString());
        
        
    }
            
        

}
