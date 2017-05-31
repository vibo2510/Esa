/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Viktoria Bock
 */
@Entity
public class Leader extends Participant implements Serializable{
    	
        
        private int id;
	private String firstname;
	private String lastname;
	private String email;
        @OneToMany(mappedBy = "leader")
        private List<Club> clubs;
        
    public Leader(){
        
    }
  
    
    public Leader(int id, String firstname, String lastname, String email,List<Club> clubs){
        super(id, firstname,lastname,email,clubs);
    }
    
    
}
