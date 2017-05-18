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

public class Participant {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
        
        public Participant(){

        }
        
        public Participant(int id, String firstname, String lastname, String email ){
            this.id= id;
            this.firstname= firstname;
            this.lastname= lastname;
            this.email= email;
            
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
}

