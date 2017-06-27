/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import com.mycompany.webapp.esa.model.AppUser;
import com.mycompany.webapp.esa.services.LeaderServiceInterface;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Viktoria Bock
 */
@Named
@Stateless
public class LoginController implements Serializable{

    private AppUser currentUser;
    @Inject
    AuthenticationManager am;
    @Inject private LeaderServiceInterface leaderService;
    

    public void doLogin() {
       am.setCurrentUserId();
    }
    
    public AppUser getCurrentUser(){
        return currentUser;
    }
}
