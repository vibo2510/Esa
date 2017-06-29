/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppUser.Leader.DataModel;

import Club.DataModel.Club;
import AppUser.AppUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author Viktoria Bock
 */
@Entity
@NamedQueries({
@NamedQuery(name = "Leader.findById", query = "SELECT l FROM Leader l WHERE l.id= :id"),
@NamedQuery(name = "Leader.findAll", query = "SELECT l FROM Leader l ORDER BY l.id"),
@NamedQuery(name="Leader.findByEmail", query ="SELECT l FROM Leader l WHERE l.email = :email")})
public class Leader extends AppUser implements Serializable {


 
    @OneToMany(mappedBy="leader" , cascade = CascadeType.PERSIST)
    private List<Club> clubs;
    
    public static final String findAll = "Leader.findAll";
    public static final String findByEmail = "Leader.findByEmail";
    public static final String findById = "Leader.findById";

    public Leader() {

    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }



}
