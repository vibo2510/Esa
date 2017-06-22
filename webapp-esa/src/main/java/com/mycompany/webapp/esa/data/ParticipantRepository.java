/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;


import com.mycompany.webapp.esa.model.Club;
import com.mycompany.webapp.esa.model.Participant;
import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Viktoria Bock
 */

@Named
@RequestScoped
public class ParticipantRepository implements Serializable{
    Connection conn;
    PreparedStatement prepstAdd;
    PreparedStatement prepstEnrole;
    PreparedStatement prepstDischarge;
    public ParticipantRepository() {
        try {
            getConnection();
            //this.prepstAdd = conn.prepareStatement("insert into participant(FIRSTNAME,LASTNAME,EMAIL) values(?,?,?)");
           // this.prepstEnrole= conn.prepareStatement("insert into participant_club values(?,?)");
            //this.prepstDischarge= conn.prepareStatement("delete from participant_club where participant_id=? and club_id=?");
        } catch (SQLException| ClassNotFoundException ex) {
            Logger.getLogger(ParticipantRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    public void getConnection() throws SQLException, ClassNotFoundException{
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String dbUrl = "jdbc:derby://localhost:1527/ClubOrgaDB";
    conn=DriverManager.getConnection(dbUrl,"rovi", "123");
    
    }
    
    public boolean addParticipant(Participant participant){
        
        try {
            prepstAdd.setString(1, participant.getFirstname());
            prepstAdd.setString(2, participant.getLastname());
            prepstAdd.setString(3, participant.getEmail());
            prepstAdd.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ParticipantRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
    }
    
    public boolean enroleToClub(Club club, Participant participant){
        try{
            prepstEnrole.setInt(1,club.getId());
            prepstEnrole.setInt(2,participant.getId() );
            prepstEnrole.execute();
            return true;
        }catch(SQLException ex){
            return false;
        }
        
    }
    
    public boolean dischargeClub(Club club,Participant participant){
        try {
            prepstDischarge.setInt(1, club.getId());
            prepstDischarge.setInt(2,participant.getId());
            prepstDischarge.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
}
