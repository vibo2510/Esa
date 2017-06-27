/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author roml
 *
 */
@Named
@ViewScoped
public class ExceptionController implements Serializable{

    public String getStatusCode() {
        
        String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.status_code"));
        return val;
       
    }

    public String getMessage() {
        try{
        String val = (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.message");
        return val;
        }catch(NullPointerException e){
            System.out.println(e);
            return "";
        }
        
    }

    public String getExceptionType() {
        try {
            String val = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("javax.servlet.error.exception_type").toString();
            return val;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return "";

        }
       
    }

    public String getException() {
        try{
        String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception")).toString();
        return val;
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            return "";
        }
        
       
    }

    public String getRequestURI() {
        try{
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
        }catch(NullPointerException e){
            System.out.println(e);
        }
        return "";
    }

    public String getServletName() {
        try{
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.servlet_name");
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
        return "";
    }

}
