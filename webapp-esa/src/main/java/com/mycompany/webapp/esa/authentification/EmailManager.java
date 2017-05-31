/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.authentification;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author roml
 */
@Named
@SessionScoped
public class EmailManager implements Serializable{
    public String loggedInUsername(){
        String email= (String) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        return email;
    }
}
