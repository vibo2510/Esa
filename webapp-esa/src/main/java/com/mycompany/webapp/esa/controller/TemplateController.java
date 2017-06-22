/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import com.mycompany.webapp.esa.authentification.AuthenticationManager;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Viktoria Bock
 */
@Named
@Stateless
public class TemplateController {
@Inject
AuthenticationManager am;

    public String onAccountOpen() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null) {
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Participant")) {
                return "participant/listClubsOfParticipant.xhtml";
            } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Leader")) {
                return "leader/listClubs.xhtml";
            }
        }
        return "login.xhtml";
    }
    
    public void onLogOut(){
        am.logout();
    }
    public boolean isLoggedIn(){
        return am.isLoggedIn();
    }
}
