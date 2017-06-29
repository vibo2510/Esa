/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import AppUser.AppUser;
import AppUser.Leader.DataModel.Leader;
import AppUser.Participant.DataModel.Participant;
import AppUser.Leader.Service.LeaderServiceInterface;
import AppUser.Participant.Service.ParticipantServiceInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.inject.Named;

import javax.servlet.http.HttpSession;

/**
 *
 * @author roml
 */
@Named
@SessionScoped
public class AuthenticationManager implements Serializable {

    private Integer currentUserId;
    @Inject
    LeaderServiceInterface leaderService;
    @Inject
    ParticipantServiceInterface participantService;

    public String logout() {
        System.out.println("User has been logged out");
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) ec.getSession(true);
        session.invalidate();
        currentUserId=null;
        return "";
    }

    public Integer getCurrentUserId() {
        System.out.println("currentUser" + currentUserId);
        return currentUserId;
    }

    public void setCurrentUserId() {
        System.out.println("*************AUTH***");
      
            String email = (String) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            System.out.println("email: " + email);
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Participant")) {
                currentUserId = participantService.getParticipantByEmail(email).getId();
                System.out.println("*************Participant***");
            } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Leader")) {
                currentUserId = leaderService.getLeaderByEmail(email).getId();
            }
            
            
           
        System.out.println("*************AUTH***");
    }

    public boolean isLoggedIn() {
        return currentUserId != null;
    }
    
    public boolean isLeader(){
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Leader");
    }

}
