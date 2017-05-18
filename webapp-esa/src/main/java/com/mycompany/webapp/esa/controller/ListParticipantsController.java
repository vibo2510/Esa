/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
/**
 *
 * @author Viktoria Bock
 */
@SessionScoped
@Named
public class ListParticipantsController implements Serializable{
    private static final long serialVersionUID = 437878972432L;
    
    public String doOk() {
		return Pages.LIST_CLUBS;
	}
}
