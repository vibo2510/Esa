/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import business.ClubService;
import com.mycompany.webapp.esa.data.ClubProducer;
import com.mycompany.webapp.esa.model.Club;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;


import javax.inject.Inject;

/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class IndexController implements Serializable {

    @Inject private ClubService clubService;

    private ArrayList<Club> ClubList;
        
        @PostConstruct
        void init(){
            ClubList=clubService.getAllClubs();
        }

        public ArrayList<Club> getClubList() {
            return ClubList;
        }
        
        public  String updateClubList(){
            System.out.print("geupdatet");
            ClubList= clubService.getAllClubs();
            return "/"+Pages.INDEX;
        }
        

}
