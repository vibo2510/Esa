/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author Viktoria Bock
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Participant.findById", query = "SELECT p FROM Participant p WHERE p.id= :id"),
@NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p ORDER BY p.id"),
@NamedQuery(name="Participant.findByEmail", query ="SELECT p FROM Participant p WHERE p.email = :email")})
public class Participant extends AppUser implements Serializable {

    @ManyToMany(mappedBy = "participants")
    private List<Club> clubs;

    public static final String findAll = "Participant.findAll";
    public static final String findByEmail = "Participant.findByEmail";
    public static final String findById = "Participant.findById";

    public Participant() {

    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }
}
