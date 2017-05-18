/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.model;

/**
 *
 * @author Viktoria Bock
 */
public class Leader extends Participant{
    	private int id;
	private String firstname;
	private String lastname;
	private String email;
        
    public Leader(){
        
    }
  
    
    public Leader(int id, String firstname, String lastname, String email){
        super(id, firstname,lastname,email);
    }
    
    
}
