/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Util.AuthenticationManager;
import AppUser.AppUser;
import AppUser.Leader.Service.LeaderServiceInterface;
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
public class LoginPModel implements Serializable{

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
