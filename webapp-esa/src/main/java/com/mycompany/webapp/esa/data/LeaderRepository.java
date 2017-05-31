/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webapp.esa.data;


import com.mycompany.webapp.esa.model.Leader;
import java.io.Serializable;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author Viktoria Bock
 */
@Named
@SessionScoped
public class LeaderRepository implements Serializable{
    Connection conn;
    PreparedStatement prepstAdd;
    PreparedStatement prepstGet;
    

    public LeaderRepository()  {
        
        try {
            getConnection();
            this.prepstAdd = conn.prepareStatement("insert into leader(FIRST_NAME,LAST_NAME,EMAIL) values(?,?,?)");
            this.prepstGet= conn.prepareStatement("select * from leader where id=?");
        } catch (ClassNotFoundException |SQLException ex) {
            Logger.getLogger(LeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    public void getConnection() throws SQLException, ClassNotFoundException{
    Class.forName("org.apache.derby.jdbc.ClientDriver");
    String dbUrl = "jdbc:derby://localhost:1527/ClubOrgaDB";
    conn=DriverManager.getConnection(dbUrl,"rovi", "123");
    
    }
    
    public boolean addLeader(Leader leader){
        
        try {
   
            prepstAdd.setString(1, leader.getFirstname());
            prepstAdd.setString(2, leader.getLastname());
            prepstAdd.setString(3, leader.getEmail());
            prepstAdd.execute();
            return true;

        } catch (SQLException  ex) {
            Logger.getLogger(LeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
    }
    
    public Leader getLeaderById(int id){
        try {
            
            prepstGet.setInt(1,id);
        
        ResultSet rs= prepstGet.executeQuery();
        Leader leader =null;
        while(rs.next()){
            leader = new Leader();
            leader.setId(id);
            leader.setFirstname(rs.getString("FIRST_NAME"));
            leader.setLastname(rs.getString("LAST_NAME"));
            leader.setEmail(rs.getString("EMAIL"));
           
        }
        return leader;
        } catch (SQLException ex) {
            Logger.getLogger(LeaderRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
}
