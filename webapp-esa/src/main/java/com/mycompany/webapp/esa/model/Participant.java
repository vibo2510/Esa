/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Viktoria Bock
 */
@Entity
public class Participant {
        @Id
        @GeneratedValue
	private int id;
	private String firstname;
	private String lastname;
	private String email;
        @ManyToMany(cascade= CascadeType.PERSIST)
        private List<Club> clubs;
        
        public Participant(){

        }
        
        public Participant(int id, String firstname, String lastname, String email,List<Club> clubs){
            this.id= id;
            this.firstname= firstname;
            this.lastname= lastname;
            this.email= email;
            this.clubs = clubs;
            
        }


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
        public List<Club> getClubs(){
            return clubs;
        }
        public void setClubs(List<Club> clubs){
            this.clubs = clubs;
        }
}

